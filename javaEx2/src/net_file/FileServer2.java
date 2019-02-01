package net_file;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer2 implements Runnable {
	private ServerSocket ss = null;
	private Socket sc=null;
	private int port=7777;
	
	// 스레드로 구현하지 않아도 됨
	public void serverStart() {
		try {
			ss=new ServerSocket(port);
			System.out.println("서버 시작 !!!\n");
			
			sc=ss.accept();
			
			// 스레드 시작
			Thread t=new Thread(this);
			t.start();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public static void main(String[] args) {
		new FileServer2().serverStart();
	}

	@Override
	public void run() {
		ObjectInputStream ois = null;
		FileOutputStream fos=null;
		int len=0;
		
		try {
			// 소켓에서 보낸 정보를 받을 입력 스트림을 구한다.
			ois = new ObjectInputStream(sc.getInputStream());
			
			String pathname=null;
			
			// 보내온 파일 내용을 파일에 저장
			System.out.println("파일 다운로드 시작 !!!");
			int n=0;
			while(true) {
				Object ob = ois.readObject();
				if(ob instanceof UserMsg) {
					UserMsg user=(UserMsg)ob;
					
					if(user.getCode()==100) {
						pathname=new String(user.getData(), 
								0, user.getLen());
						fos = new FileOutputStream(pathname);
					} else if(user.getCode()==200) {
						len=user.getLen();
					} else if(user.getCode()==300) {
						fos.write(user.getData(), 0, user.getLen());
						
						System.out.println(user.getLen() + " bytes 다운로드 !!!");
						
						n+=user.getLen();
						if(n==len) {
							fos.flush();
							fos.close();
							System.out.println("파일 다운로드 끝 !!!");
							break;
						}
					}
				}
			}
			
			sc.close();
			ss.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}

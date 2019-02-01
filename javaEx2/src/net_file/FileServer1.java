package net_file;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer1 implements Runnable {
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
		new FileServer1().serverStart();
	}

	@Override
	public void run() {
		InputStream is=null;
		FileOutputStream fos=null;
		
		try {
			// 소켓에서 보낸 정보를 받을 입력 스트림을 구한다.
			is = sc.getInputStream();
			
			String pathname="sam.txt";
			// 저장할 파일출력스트림 객체 생성
			fos = new FileOutputStream(pathname);
			
			// 보내온 파일 내용을 파일에 저장
			byte []buffer = new byte[512];
			int len;

			System.out.println("파일 다운로드 시작 !!!");
			while((len=is.read(buffer, 0, 512))>0) {
				fos.write(buffer, 0, len);
				System.out.println(len + " bytes 다운로드 !!!");
			}
			fos.flush();
			System.out.println("파일 다운로드 끝 !!!");
			
			fos.close();
			sc.close();
			ss.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}

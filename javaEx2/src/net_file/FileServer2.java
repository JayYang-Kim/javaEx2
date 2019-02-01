package net_file;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer2 implements Runnable {
	private ServerSocket ss = null;
	private Socket sc=null;
	private int port=7777;
	
	// ������� �������� �ʾƵ� ��
	public void serverStart() {
		try {
			ss=new ServerSocket(port);
			System.out.println("���� ���� !!!\n");
			
			sc=ss.accept();
			
			// ������ ����
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
			// ���Ͽ��� ���� ������ ���� �Է� ��Ʈ���� ���Ѵ�.
			ois = new ObjectInputStream(sc.getInputStream());
			
			String pathname=null;
			
			// ������ ���� ������ ���Ͽ� ����
			System.out.println("���� �ٿ�ε� ���� !!!");
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
						
						System.out.println(user.getLen() + " bytes �ٿ�ε� !!!");
						
						n+=user.getLen();
						if(n==len) {
							fos.flush();
							fos.close();
							System.out.println("���� �ٿ�ε� �� !!!");
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

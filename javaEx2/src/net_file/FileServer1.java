package net_file;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer1 implements Runnable {
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
		new FileServer1().serverStart();
	}

	@Override
	public void run() {
		InputStream is=null;
		FileOutputStream fos=null;
		
		try {
			// ���Ͽ��� ���� ������ ���� �Է� ��Ʈ���� ���Ѵ�.
			is = sc.getInputStream();
			
			String pathname="sam.txt";
			// ������ ������½�Ʈ�� ��ü ����
			fos = new FileOutputStream(pathname);
			
			// ������ ���� ������ ���Ͽ� ����
			byte []buffer = new byte[512];
			int len;

			System.out.println("���� �ٿ�ε� ���� !!!");
			while((len=is.read(buffer, 0, 512))>0) {
				fos.write(buffer, 0, len);
				System.out.println(len + " bytes �ٿ�ε� !!!");
			}
			fos.flush();
			System.out.println("���� �ٿ�ε� �� !!!");
			
			fos.close();
			sc.close();
			ss.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}

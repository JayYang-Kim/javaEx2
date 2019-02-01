package net_file;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class FileClient1 { // ������ ����Ʈ��Ʈ������ ������.
	private int port=7777;
	
	private String host="127.0.0.1";
	private Socket sc=null;
	private Scanner scan=new Scanner(System.in);
	
	public void send() {
		String pathname;
		OutputStream os=null;
		FileInputStream fis=null;
		
		System.out.print("������ ���ϸ� ? ");
		pathname=scan.next();
		
		File f=new File(pathname);
		if(! f.exists() || ! f.isFile()) {
			System.out.println(pathname+" ������ �������� �ʽ��ϴ�.");
			return;
		}
		
		try {
			sc=new Socket(host, port);
			
			// ���Ͽ��� ���� ��� ��Ʈ���� ���Ѵ�.
			os = sc.getOutputStream();
			
			// ���� ������ �Է� ��Ʈ�� ��ü ����
			fis = new FileInputStream(pathname);
			
			// ������ ������ ������
			byte []buffer = new byte[512];
			int len;
			
			System.out.println("���� ������ ���� !!!");
			while((len=fis.read(buffer, 0, 512))>0) { 
				os.write(buffer, 0, len); // ����Ʈ��Ʈ������ ������.
				System.out.println(len + "bytes ���� !!!");
			}
			os.flush();
			System.out.println("���� ������ �� !!!");
			
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				if(sc!=null)
					sc.close();
				
				if(fis!=null) 
					fis.close();
				
			} catch (Exception e2) {
			}
		}
	}
	
	public static void main(String[] args) {
		FileClient1 fc=new FileClient1();
		fc.send();
	}
}

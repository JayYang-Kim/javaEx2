package net_file;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class FileClient2 {
	private int port=7777;
	
	private String host="127.0.0.1";
	private Socket sc=null;
	private Scanner scan=new Scanner(System.in);
	private ObjectOutputStream oos = null;
	
	public void send() {
		String pathname;
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
			oos = new ObjectOutputStream(sc.getOutputStream());
			oos.flush();
			
			// ���ϸ� ������
			UserMsg user=new UserMsg();
			user.setCode(100);
			user.setData(f.getName().getBytes());
			user.setLen(f.getName().getBytes().length);
			oos.writeObject(user);
			
			// ���ϱ��� ������
			user=new UserMsg();
			user.setCode(200);
			user.setLen((int)f.length());
			oos.writeObject(user);
			
			// ���� ������ �Է� ��Ʈ�� ��ü ����
			fis = new FileInputStream(pathname);
			
			// ������ ������ ������
			byte []buffer = new byte[512];
			int len;
			
			System.out.println("���� ������ ���� !!!");
			while((len=fis.read(buffer, 0, 512))>0) {
				user=new UserMsg();
				user.setCode(300);
				user.setData(buffer);
				user.setLen(len);
				oos.writeObject(user);

				System.out.println(len + "bytes ���� !!!");
			}
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
		FileClient2 fc=new FileClient2();
		fc.send();
	}
}

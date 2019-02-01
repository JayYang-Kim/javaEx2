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
		
		System.out.print("전송할 파일명 ? ");
		pathname=scan.next();
		
		File f=new File(pathname);
		if(! f.exists() || ! f.isFile()) {
			System.out.println(pathname+" 파일은 존재하지 않습니다.");
			return;
		}
		
		try {
			sc=new Socket(host, port);
			
			// 소켓에서 보낼 출력 스트림을 구한다.
			oos = new ObjectOutputStream(sc.getOutputStream());
			oos.flush();
			
			// 파일명 보내기
			UserMsg user=new UserMsg();
			user.setCode(100);
			user.setData(f.getName().getBytes());
			user.setLen(f.getName().getBytes().length);
			oos.writeObject(user);
			
			// 파일길이 보내기
			user=new UserMsg();
			user.setCode(200);
			user.setLen((int)f.length());
			oos.writeObject(user);
			
			// 보낼 파일의 입력 스트림 객체 생성
			fis = new FileInputStream(pathname);
			
			// 파일의 내용을 보낸다
			byte []buffer = new byte[512];
			int len;
			
			System.out.println("파일 보내기 시작 !!!");
			while((len=fis.read(buffer, 0, 512))>0) {
				user=new UserMsg();
				user.setCode(300);
				user.setData(buffer);
				user.setLen(len);
				oos.writeObject(user);

				System.out.println(len + "bytes 보냄 !!!");
			}
			System.out.println("파일 보내기 끝 !!!");
			
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

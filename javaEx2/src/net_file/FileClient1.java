package net_file;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class FileClient1 { // 파일은 바이트스트림으로 보낸다.
	private int port=7777;
	
	private String host="127.0.0.1";
	private Socket sc=null;
	private Scanner scan=new Scanner(System.in);
	
	public void send() {
		String pathname;
		OutputStream os=null;
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
			os = sc.getOutputStream();
			
			// 보낼 파일의 입력 스트림 객체 생성
			fis = new FileInputStream(pathname);
			
			// 파일의 내용을 보낸다
			byte []buffer = new byte[512];
			int len;
			
			System.out.println("파일 보내기 시작 !!!");
			while((len=fis.read(buffer, 0, 512))>0) { 
				os.write(buffer, 0, len); // 바이트스트림으로 보낸다.
				System.out.println(len + "bytes 보냄 !!!");
			}
			os.flush();
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
		FileClient1 fc=new FileClient1();
		fc.send();
	}
}

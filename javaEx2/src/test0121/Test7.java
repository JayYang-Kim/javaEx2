package test0121;

import java.io.FileOutputStream;
import java.io.IOException;

public class Test7 {

	public static void main(String[] args) {
		// File 저장
		FileOutputStream fos = null;
		String pathname = "test.txt"; // 파일명
		int data;
		
		try {
			// 파일이 없으면 만들고, 있으면 지우고 만듬 (덮어쓰기)
			fos = new FileOutputStream(pathname);
			
			System.out.println("문자열 입력 [종료 : ctrl + z]");
			
			while ((data = System.in.read()) != -1) {
				fos.write(data); 
			}
			
			// fos.close() 하면 flush()를 호출하지 않아도 출력 버퍼 내용을 스트림으로 보내
			fos.flush();
			
			System.out.println("파일 생성 완료");
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (Exception e2) {

			}
		}

	}

}

package test0122;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Test3 {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String pathname = "ex.txt";
		int data;
		
		// 괄호안에 객체 생성 시 자동 close 처리
		// true : apped용 (기존 내용에 추가)
		try(FileOutputStream fos = new FileOutputStream(pathname, true)) {
			System.out.println("문자열 입력 [종료 : ctrl + z]");
			
			while((data = System.in.read()) != -1) {
				fos.write(data);
			}
			
			fos.flush();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}

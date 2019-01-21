package test0121;

import java.io.IOException;

public class Test1 {

	public static void main(String[] args) {
		// 바이트 입력 스트림
		int data;
		char ch;
		String text;
		
		try {
			// System.in : 표준 입력, InputStream
			// InputStream read() : 1byte 문자를 입력받아 ASCII 코드 값을 리턴한다.
			// 입력 : ABC(Enter) => 코드값 : 65 66 67 13 10
			// 입력 : 대한(Enter) => 코드값 : 180 235 199 209 13 10
			// 코드값 : 13 10 => Enter
			System.out.println("문자열 입력 [종료 : ctrl + z]");
			while((data = System.in.read()) != -1) {
				//System.out.println(data);
				
				ch = (char)data;
				//System.out.println(ch); // 한글은 깨짐 (2byte 문자를 1byte만 읽은 후 문자로 출력했기 때문)
				
				System.out.write(ch); // 하위 1byte 출력
			}
		} catch (IOException e) {
			System.out.println(e);
		}

	}

}

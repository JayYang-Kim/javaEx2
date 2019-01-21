package test0121;

import java.io.InputStreamReader;
import java.io.Reader;

public class Test5 {

	public static void main(String[] args) {
		// InputStreamReader : byte 스트림을 문자 스트림으로 변환
		Reader rd = new InputStreamReader(System.in);
		
		int data;
		char ch;
		
		System.out.println("문자열 [종료 : ctrl + z]");
		
		// ABC(Enter) => 코드값 : 65, 66, 67 13, 10
		// 대한(Enter) => 코드값 : 45824 54620 13 10
		try {
			while ((data = rd.read()) != -1) {
				//System.out.println(data);
				
				ch = (char)data;
				
				System.out.write(ch); // 바이트 스트림, 한글깨짐. 2byte를 1byte만 출력해서
				//System.out.print(ch);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

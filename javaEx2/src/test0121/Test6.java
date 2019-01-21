package test0121;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class Test6 {

	public static void main(String[] args) {
		// InputStreamReader, OutputStreamWriter : byte 스트림을 문자 스트림으로 변환
		Reader rd = new InputStreamReader(System.in);
		Writer wt = new OutputStreamWriter(System.out);
		
		int data;
		char ch;
		
		System.out.println("문자열 [종료 : ctrl + z]");
		
		// ABC(Enter) => 코드값 : 65, 66, 67 13, 10
		// 대한(Enter) => 코드값 : 45824 54620 13 10
		try {
			while ((data = rd.read()) != -1) {
				//System.out.println(data);
				
				ch = (char)data;
				
				wt.write(ch); // 문자 스트림
			}
			
			wt.flush();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

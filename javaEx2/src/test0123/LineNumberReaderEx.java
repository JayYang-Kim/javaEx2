package test0123;

import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class LineNumberReaderEx {

	public static void main(String[] args) {
		LineNumberReader br = new LineNumberReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		String s;
		
		try {
			System.out.println("문자열 입력 [ctrl + z]");
			
			while ((s = br.readLine()) != null) {
				sb.append(br.getLineNumber() + ":" + s + "\n");
			}
			
			System.out.println(sb.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

}

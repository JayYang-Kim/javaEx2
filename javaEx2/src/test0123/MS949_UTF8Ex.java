package test0123;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class MS949_UTF8Ex {

	public static void main(String[] args) {
		// MS949 : euc-kr
		
		try {
			int ch;
			Reader rd = new InputStreamReader(System.in, "MS949");
			// MS949 인코딩으로 파일에 저장
			Writer wt = new OutputStreamWriter(new FileOutputStream("ex1.txt"), "MS949");
			
			Writer wt2 = new OutputStreamWriter(new FileOutputStream("ex2.txt"), "UTF-8");
			
			System.out.println("문자열 입력 [ctrl + z]");
			
			while ((ch = rd.read()) != -1) {
				wt.write(ch);
				wt2.write(ch);
			}
			
			wt.flush();
			wt.close();
			
			wt2.flush();
			wt2.close();
			
			System.out.println("파일 내용");
			
			Reader rd2 = new InputStreamReader(new FileInputStream("ex2.txt"), "UTF-8");
			Writer wt3 = new OutputStreamWriter(System.out, "UTF-8");
			
			while ((ch = rd2.read()) != -1) {
				wt3.write(ch);
			}
			
			wt3.flush();
			rd2.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

}

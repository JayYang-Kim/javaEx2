package test0129;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

//  자바 안에서 다른 자바를 컴파일
public class ProcessEx8 {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			String s;

			System.out.print("DOS 명령 ? "); // 자바에서 도스명령 실행하도록
			s = br.readLine();

			String[] ss = new String[3];
			ss[0] = "cmd.exe";
			ss[1] = "/C"; // 프로그램 종료 후 cmd창 종료
			ss[2] = s;

			Runtime rt = Runtime.getRuntime();
			Process p = rt.exec(ss);

			InputStream is = p.getInputStream();
			BufferedReader br2 = new BufferedReader(new InputStreamReader(is));

			while ((s = br2.readLine()) != null) {
				System.out.println(s);
			}
		} catch (Exception e) {

		}
	}
}

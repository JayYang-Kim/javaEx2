package test0129;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

//  �ڹ� �ȿ��� �ٸ� �ڹٸ� ������
public class ProcessEx8 {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			String s;

			System.out.print("DOS ��� ? "); // �ڹٿ��� ������� �����ϵ���
			s = br.readLine();

			String[] ss = new String[3];
			ss[0] = "cmd.exe";
			ss[1] = "/C"; // ���α׷� ���� �� cmdâ ����
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

package test0121;

import java.io.InputStreamReader;
import java.io.Reader;

public class Test5 {

	public static void main(String[] args) {
		// InputStreamReader : byte ��Ʈ���� ���� ��Ʈ������ ��ȯ
		Reader rd = new InputStreamReader(System.in);
		
		int data;
		char ch;
		
		System.out.println("���ڿ� [���� : ctrl + z]");
		
		// ABC(Enter) => �ڵ尪 : 65, 66, 67 13, 10
		// ����(Enter) => �ڵ尪 : 45824 54620 13 10
		try {
			while ((data = rd.read()) != -1) {
				//System.out.println(data);
				
				ch = (char)data;
				
				System.out.write(ch); // ����Ʈ ��Ʈ��, �ѱ۱���. 2byte�� 1byte�� ����ؼ�
				//System.out.print(ch);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

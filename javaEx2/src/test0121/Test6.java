package test0121;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class Test6 {

	public static void main(String[] args) {
		// InputStreamReader, OutputStreamWriter : byte ��Ʈ���� ���� ��Ʈ������ ��ȯ
		Reader rd = new InputStreamReader(System.in);
		Writer wt = new OutputStreamWriter(System.out);
		
		int data;
		char ch;
		
		System.out.println("���ڿ� [���� : ctrl + z]");
		
		// ABC(Enter) => �ڵ尪 : 65, 66, 67 13, 10
		// ����(Enter) => �ڵ尪 : 45824 54620 13 10
		try {
			while ((data = rd.read()) != -1) {
				//System.out.println(data);
				
				ch = (char)data;
				
				wt.write(ch); // ���� ��Ʈ��
			}
			
			wt.flush();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

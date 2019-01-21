package test0121;

import java.io.IOException;
import java.io.OutputStream;

public class Test4 {

	public static void main(String[] args) {
		byte[] b = {65, 66, 67, 68, 69, 70, 71, 72, 73};
		
		try {
			OutputStream os = System.out;
			
			//os.write(b); // [���] ABCDEFGHI
			os.write(b, 0, b.length); // [���] ABCDEFGHI
			os.write(b, 0, 3); // [���] ABC
			os.write(b, 2, 2); // [���] CD
			
			// �迭�� ��쿡�� flush�� ���� �ʾƵ� �ȴ�. (���������� flush�� ������ �ִ�.)
			//os.flush();
			
			os.close();
			
			System.out.println("�ȳ�..."); // ��¾ȵ�. (������ ��� ��Ʈ���� �ݾұ� ����)
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}

package test0121;

import java.io.IOException;

public class Test1 {

	public static void main(String[] args) {
		// ����Ʈ �Է� ��Ʈ��
		int data;
		char ch;
		String text;
		
		try {
			// System.in : ǥ�� �Է�, InputStream
			// InputStream read() : 1byte ���ڸ� �Է¹޾� ASCII �ڵ� ���� �����Ѵ�.
			// �Է� : ABC(Enter) => �ڵ尪 : 65 66 67 13 10
			// �Է� : ����(Enter) => �ڵ尪 : 180 235 199 209 13 10
			// �ڵ尪 : 13 10 => Enter
			System.out.println("���ڿ� �Է� [���� : ctrl + z]");
			while((data = System.in.read()) != -1) {
				//System.out.println(data);
				
				ch = (char)data;
				//System.out.println(ch); // �ѱ��� ���� (2byte ���ڸ� 1byte�� ���� �� ���ڷ� ����߱� ����)
				
				System.out.write(ch); // ���� 1byte ���
			}
		} catch (IOException e) {
			System.out.println(e);
		}

	}

}

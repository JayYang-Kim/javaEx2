package test0121;

import java.io.FileOutputStream;
import java.io.IOException;

public class Test7 {

	public static void main(String[] args) {
		// File ����
		FileOutputStream fos = null;
		String pathname = "test.txt"; // ���ϸ�
		int data;
		
		try {
			// ������ ������ �����, ������ ����� ���� (�����)
			fos = new FileOutputStream(pathname);
			
			System.out.println("���ڿ� �Է� [���� : ctrl + z]");
			
			while ((data = System.in.read()) != -1) {
				fos.write(data); 
			}
			
			// fos.close() �ϸ� flush()�� ȣ������ �ʾƵ� ��� ���� ������ ��Ʈ������ ����
			fos.flush();
			
			System.out.println("���� ���� �Ϸ�");
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (Exception e2) {

			}
		}

	}

}

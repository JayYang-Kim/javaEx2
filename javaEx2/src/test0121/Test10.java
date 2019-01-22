package test0121;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test10 {

	public static void main(String[] args) {
		// [�߿�!!]
		String source, target;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		byte []b = new byte[1024]; // byte[1024] or byte[2048]
		int len;
		
		try {
			System.out.println("������ ���� ���ϸ�?");
			source = br.readLine();
			
			System.out.println("������ ���ϸ�?");
			target = br.readLine();
			
			fis = new FileInputStream(source); // ������ ���� ���, FileNotFoundException �߻�
			fos = new FileOutputStream(target); // ������ �����, ������ ����� ����
			
			long start = System.currentTimeMillis();
			
			/*while ((len = fis.read(b, 0, b.length)) != -1) {
				fos.write(b, 0, len);
			}*/
			
			// ���� �ҽ��� ����
			while ((len = fis.read(b)) != -1) { // return���� ������ ���� ���̸� ��ȯ
				fos.write(b, 0, len);
			}
			
			fos.flush();
			
			long end = System.currentTimeMillis();
			
			System.out.println("���Ϻ��� �Ϸ� : " + (end - start) + "(ms)");
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e2) {

				}
			}
			
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e2) {

				}
			}
		}
	}

}

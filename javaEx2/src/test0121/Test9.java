package test0121;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test9 {

	public static void main(String[] args) {
		int data;
		String source, target;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			System.out.println("������ ���� ���ϸ�?");
			source = br.readLine();
			
			System.out.println("������ ���ϸ�?");
			target = br.readLine();
			
			fis = new FileInputStream(source);
			fos = new FileOutputStream(target);
			
			long start = System.currentTimeMillis();
			
			while ((data = fis.read()) != -1) {
				fos.write(data);
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

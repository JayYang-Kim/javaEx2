package test0121;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test8 {

	public static void main(String[] args) {
		// ���� �б�
		String pathname = "test.txt";
		int data;
		
		FileInputStream fis = null;
		
		try {
			// ������ ������ FileNotFoundException �߻�
			fis = new FileInputStream(pathname);
			
			while ((data = fis.read()) != -1) {
				System.out.write(data);
			}
			
			System.out.flush();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (Exception e2) {

			}
		}
	}

}

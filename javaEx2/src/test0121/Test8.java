package test0121;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test8 {

	public static void main(String[] args) {
		// 파일 읽기
		String pathname = "test.txt";
		int data;
		
		FileInputStream fis = null;
		
		try {
			// 파일이 없으면 FileNotFoundException 발생
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

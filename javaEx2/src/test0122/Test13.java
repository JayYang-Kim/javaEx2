package test0122;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

public class Test13 {

	public static void main(String[] args) {
		try {
			File f= File.createTempFile("test", "tmp"); // �ӽ����� ����
			f.deleteOnExit(); // ���α׷��� �����ϸ� ����
			
			FileOutputStream fos = new FileOutputStream(f);
			
			for (int i = 65; i <= 90; i++) {
				fos.write(i);
			}
			
			fos.close();
			
			RandomAccessFile raf = new RandomAccessFile(f, "rw");
			
			for (int i = 0; i < 10; i+=2) {
				raf.seek(i);
				
				System.out.println((char)raf.readByte());
			}
			
			raf.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}

}

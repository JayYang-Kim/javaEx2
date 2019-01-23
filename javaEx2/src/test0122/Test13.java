package test0122;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

public class Test13 {

	public static void main(String[] args) {
		try {
			File f= File.createTempFile("test", "tmp"); // 임시파일 생성
			f.deleteOnExit(); // 프로그램이 종료하면 삭제
			
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

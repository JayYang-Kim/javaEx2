package test0122;

import java.io.RandomAccessFile;

public class Test12 {

	public static void main(String[] args) {
		try {
			// 없으면 만든다.
			RandomAccessFile raf = new RandomAccessFile("ex.txt", "rw"); // 파일 읽기, 쓰기 가능 처리
			
			for (int i = 65; i <= 90; i++) {
				raf.write(i); // 있으면 수정한다.
			}
			
			raf.seek(6); // index : 0부터 시작
			
			System.out.println((char)raf.readByte()); // [결과] G
			System.out.println((char)raf.readByte()); // [결과] G
			
			raf.seek(15);
			for (int i = 1; i <= 3; i++) { // [결과] P Q R
				System.out.println((char)raf.readByte());
			}
			
			raf.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}

}

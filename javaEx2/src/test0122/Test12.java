package test0122;

import java.io.RandomAccessFile;

public class Test12 {

	public static void main(String[] args) {
		try {
			// ������ �����.
			RandomAccessFile raf = new RandomAccessFile("ex.txt", "rw"); // ���� �б�, ���� ���� ó��
			
			for (int i = 65; i <= 90; i++) {
				raf.write(i); // ������ �����Ѵ�.
			}
			
			raf.seek(6); // index : 0���� ����
			
			System.out.println((char)raf.readByte()); // [���] G
			System.out.println((char)raf.readByte()); // [���] G
			
			raf.seek(15);
			for (int i = 1; i <= 3; i++) { // [���] P Q R
				System.out.println((char)raf.readByte());
			}
			
			raf.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}

}

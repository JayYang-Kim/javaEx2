package test0123;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileCopyEx {
	public final static int BUFFER_SZIE = 1024;

	public static void main(String[] args) {
		// [�߿�!!]
		String source, target;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		byte []b = new byte[BUFFER_SZIE];
		int len;
		
		try {
			System.out.println("������ ���� ���ϸ�?");
			source = br.readLine();
			
			System.out.println("������ ���ϸ�?");
			target = br.readLine();
			
			bis = new BufferedInputStream(new FileInputStream(source));
			bos = new BufferedOutputStream(new FileOutputStream(target), BUFFER_SZIE);
			
			long start = System.nanoTime();
			//long start = System.currentTimeMillis();
			
			/*while ((len = fis.read(b, 0, b.length)) != -1) {
				fos.write(b, 0, len);
			}*/
			
			// ���� �ҽ��� ����
			while ((len = bis.read(b)) != -1) { // return���� ������ ���� ���̸� ��ȯ
				bos.write(b, 0, len);
			}
			
			bos.flush();
			
			long end = System.nanoTime();
			
			System.out.println("���Ϻ��� �Ϸ� : " + (end - start) + "(ns)");
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (Exception e2) {

				}
			}
			
			if (bos != null) {
				try {
					bos.close();
				} catch (Exception e2) {

				}
			}
		}
	}

}

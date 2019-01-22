package test0121;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test10 {

	public static void main(String[] args) {
		// [중요!!]
		String source, target;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		byte []b = new byte[1024]; // byte[1024] or byte[2048]
		int len;
		
		try {
			System.out.println("복사할 원본 파일명?");
			source = br.readLine();
			
			System.out.println("복사할 파일명?");
			target = br.readLine();
			
			fis = new FileInputStream(source); // 파일이 없을 경우, FileNotFoundException 발생
			fos = new FileOutputStream(target); // 없으면 만들고, 있으면 지우고 만듬
			
			long start = System.currentTimeMillis();
			
			/*while ((len = fis.read(b, 0, b.length)) != -1) {
				fos.write(b, 0, len);
			}*/
			
			// 위의 소스와 동일
			while ((len = fis.read(b)) != -1) { // return값은 실제로 읽은 길이를 반환
				fos.write(b, 0, len);
			}
			
			fos.flush();
			
			long end = System.currentTimeMillis();
			
			System.out.println("파일복사 완료 : " + (end - start) + "(ms)");
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

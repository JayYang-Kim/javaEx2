package test0122;

import java.io.File;

public class Test6 {

	public static void main(String[] args) {
		// 폴더 만들기
		String pathname;
		
		try {
			// c:\test\ex\demo
			pathname = "c:" + File.separator + "test" + File.separator + "ex" + File.separator + "demo";
			
			File f = new File(pathname);
			
			if (!f.exists()) {
				//f.mkdir(); // c:\test\ex\demo (test, ex 폴더가 없기 때문에 demo 폴더만 생성일 불가능) 
				f.mkdirs();
				System.out.println("폴더 생성완료");
			}
		} catch (Exception e) {
			
		}

	}

}

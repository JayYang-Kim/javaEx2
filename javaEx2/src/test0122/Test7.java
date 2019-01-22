package test0122;

import java.io.File;

public class Test7 {

	public static void main(String[] args) {
		// 폴더 이름 변경
		String pathname1, pathname2;
		
		try {
			// c:\test\ex\demo
			pathname1 = "c:" + File.separator + "test" + File.separator + "ex" + File.separator + "demo";
			pathname2 = "c:" + File.separator + "test" + File.separator + "ex" + File.separator + "sample";
			
			File f = new File(pathname1);
			
			if (f.exists()) {
				f.renameTo(new File(pathname2)); // 파일명도 변경가능
				System.out.println("이름 변경완료");
			}
		} catch (Exception e) {
			
		}

	}

}

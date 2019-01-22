package test0122;

import java.io.File;

public class Test8 {

	public static void main(String[] args) {
		// 폴더 삭제
		String pathname;
		
		try {
			pathname = "c:" + File.separator + "test" + File.separator + "ex" + File.separator + "sample";
			
			File f = new File(pathname);
			
			if (f.exists()) {
				f.delete(); // 파일도 제거 가능 (단, 한 번에 하나만 삭제 가능, 폴더는 비어 있어야 가능)
				
				System.out.println("폴더 삭제완료");
			}
		} catch (Exception e) {
			
		}

	}

}

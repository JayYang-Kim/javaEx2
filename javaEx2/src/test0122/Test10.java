package test0122;

import java.io.File;

public class Test10 {
	// 폴더 하위 목록 확인
	public static void directoryList(String pathname) {
		File f = new File(pathname);
		
		File []list = f.listFiles();
		
		if (list == null) {
			return;
		}
		
		try {
			for (File file : list) {
				if (file.isFile()) {
					System.out.println(file.getName() + "\t" + file.length());
				} else if (file.isDirectory()) {
					System.out.println("[폴더] " + file.getName());
					
					directoryList(file.getCanonicalPath()); // 재귀 호출
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) {
		directoryList("c:\\windows");
		
	}

}

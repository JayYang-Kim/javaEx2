package test0122;

import java.io.File;
import java.io.IOException;

public class FileDeleteEx {
	public static void removeDir(String pathname) throws IOException {
		// 최상단의 폴더만 삭제 (c:\test\ex\demo => test폴더만 삭제)
		File f = new File(pathname);
		
		if (!f.exists()) {
			throw new IOException(pathname + "이 존재하지 않습니다.");
		}
		
		try {
			if (f.isDirectory()) {
				removeSubDir(pathname);
			}
			
			f.delete();
		} catch (Exception e) {
			throw e;
		}
	}
	
	private static void removeSubDir(String pathname) throws IOException {
		File []list = new File(pathname).listFiles();
		
		try {
			if (list != null) {
				for (File f : list) {
					if (f.isDirectory()) {
						removeSubDir(f.getPath());
					}
					
					// return값을 boolean 자료형으로 던져주기 때문에 변수로 받아서 삭제가 되었는지 체크가 가능
					f.delete();
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static void main(String[] args) {
		try {
			removeDir("c:\\test");
			
			System.out.println("삭제완료");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}

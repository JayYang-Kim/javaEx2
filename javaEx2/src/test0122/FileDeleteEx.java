package test0122;

import java.io.File;
import java.io.IOException;

public class FileDeleteEx {
	public static void removeDir(String pathname) throws IOException {
		// �ֻ���� ������ ���� (c:\test\ex\demo => test������ ����)
		File f = new File(pathname);
		
		if (!f.exists()) {
			throw new IOException(pathname + "�� �������� �ʽ��ϴ�.");
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
					
					// return���� boolean �ڷ������� �����ֱ� ������ ������ �޾Ƽ� ������ �Ǿ����� üũ�� ����
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
			
			System.out.println("�����Ϸ�");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}

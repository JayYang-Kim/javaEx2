package test0122;

import java.io.File;

public class Test6 {

	public static void main(String[] args) {
		// ���� �����
		String pathname;
		
		try {
			// c:\test\ex\demo
			pathname = "c:" + File.separator + "test" + File.separator + "ex" + File.separator + "demo";
			
			File f = new File(pathname);
			
			if (!f.exists()) {
				//f.mkdir(); // c:\test\ex\demo (test, ex ������ ���� ������ demo ������ ������ �Ұ���) 
				f.mkdirs();
				System.out.println("���� �����Ϸ�");
			}
		} catch (Exception e) {
			
		}

	}

}

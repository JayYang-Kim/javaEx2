package test0122;

import java.io.File;

public class Test8 {

	public static void main(String[] args) {
		// ���� ����
		String pathname;
		
		try {
			pathname = "c:" + File.separator + "test" + File.separator + "ex" + File.separator + "sample";
			
			File f = new File(pathname);
			
			if (f.exists()) {
				f.delete(); // ���ϵ� ���� ���� (��, �� ���� �ϳ��� ���� ����, ������ ��� �־�� ����)
				
				System.out.println("���� �����Ϸ�");
			}
		} catch (Exception e) {
			
		}

	}

}

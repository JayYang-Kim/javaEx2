package test0122;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test5 {

	public static void main(String[] args) {
		String appath = System.getProperty("user.dir");
		
		//System.out.println("���� �۾� ��� : " + appath);
		
		// ������ ���� ������(������ ���� ������) : File.separator
		String pathname = appath + File.separator + "ex.txt";
		
		try {
			File f = new File(pathname);
			
			System.out.println("���� ����");
			
			if (!f.exists()) { // ���� �Ǵ� ���� ���� ����
				System.out.println(pathname + "�� �������� �ʽ��ϴ�.");
				return;
			}
			
			System.out.println("���ϸ� : " + f.getName());
			System.out.println("���ϱ��� : " + f.length()); // long �ڷ���
			
			System.out.println("������ : " + f.getAbsolutePath()); // ���\ex.txt
			System.out.println("ǥ�ذ�� : " + f.getCanonicalPath()); // ǥ��(�߻�)���
			
			System.out.println("���� ������ : " + f.lastModified()); // ms ���� (long �ڷ���)
			System.out.println("���� ������ : " + new Date(f.lastModified()));
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String s = sdf.format(new Date(f.lastModified()));
			
			System.out.println("���� ������ : " + s);
			
			System.out.println("���� ��� : " + f.getParent());
			
			System.out.println("�б�Ӽ� : " + f.canRead());
			System.out.println("����Ӽ� : " + f.canWrite());
			
			s = f.getPath();
			System.out.println("Ȯ���� : " + s.substring(s.lastIndexOf(".") + 1, s.length()));
		} catch (Exception e) {
			
		}

	}

}

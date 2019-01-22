package test0122;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Test9 {

	public static void main(String[] args) {
		// ���� ��� Ȯ��
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String pathname;
		
		try {
			System.out.println("Ȯ���� ������?");
			pathname = br.readLine();
			
			File f = new File(pathname);
			
			// ������ �ִ��� üũ
			if (!f.exists()) {
				System.out.println(pathname + "�� �������� �ʽ��ϴ�.");
				return;
			}
			
			// �������� üũ
			if (!f.isDirectory()) { // is�� �� ���, boolean �ڷ���
				System.out.println(pathname + "������ �ƴմϴ�.");
				return;
			}
			
			File []list = f.listFiles();
			
			for(File a : list) {
				if (a.isDirectory()) { // �������� üũ
					System.out.println(a.getName() + "����");
				} else if (a.isFile()) { // �������� üũ
					System.out.println(a.getName() + "\t" + a.length()); // ���ϸ�, ���� ������
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

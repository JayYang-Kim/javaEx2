package test0122;

import java.io.BufferedReader;
import java.io.FileReader;

public class Test4 {

	public static void main(String[] args) {
		String pathname = "ex.txt";
		BufferedReader br = null;
		String str;
		int n = 0;
		
		try {
			// ����Ʈ ��Ʈ�� > ���� ��Ʈ�� > ���پ� �б�
			//br = new BufferedReader(new InputStreamReader(new FileInputStream(pathname)));
			br = new BufferedReader(new FileReader(pathname)); // ���� �ҽ��� ����
			
			while ((str = br.readLine()) != null) {
				System.out.println(++n + " : " + str);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e2) {
					
				}
			}
		}

	}

}

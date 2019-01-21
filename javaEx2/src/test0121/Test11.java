package test0121;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test11 {

	public static void main(String[] args) {
		// �ؽ�Ʈ ������ �о� �鿩 �ؽ�Ʈ ���Ϸ� �����ϱ�(2�� ������ �ȵ�)
		int data;
		String source, target;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		FileReader frd = null;
		FileWriter fwt = null;
		
		try {
			System.out.println("������ ���� ���ϸ�?");
			source = br.readLine();
			
			System.out.println("������ ���ϸ�?");
			target = br.readLine();
			
			frd = new FileReader(source);
			// Reader frd = new InputStreamReader(new FileInputStream(source)):
			fwt = new FileWriter(target);
			// Writer fwt = new OutputStreamWriter(new FileOutputStream(target)):
			
			while ((data = frd.read()) != -1) {
				fwt.write(data);
			}
			
			fwt.flush();
			
			System.out.println("�ؽ�Ʈ ���� ���� �Ϸ�");
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (frd != null) {
				try {
					frd.close();
				} catch (Exception e2) {

				}
			}
			
			if (fwt != null) {
				try {
					fwt.close();
				} catch (Exception e2) {

				}
			}
		}
	}

}

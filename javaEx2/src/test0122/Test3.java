package test0122;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Test3 {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String pathname = "ex.txt";
		int data;
		
		// ��ȣ�ȿ� ��ü ���� �� �ڵ� close ó��
		// true : apped�� (���� ���뿡 �߰�)
		try(FileOutputStream fos = new FileOutputStream(pathname, true)) {
			System.out.println("���ڿ� �Է� [���� : ctrl + z]");
			
			while((data = System.in.read()) != -1) {
				fos.write(data);
			}
			
			fos.flush();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}

package test0121;

import java.io.IOException;

public class Test2 {

	public static void main(String[] args) {
		// [����] ������ �Է� �޾� 1 ~ �Է� ���� �� ���� ��. (��, ������ �Է��� System.in.read() �̿�)
		int data, num = 0;
		
		try {
			// ex) 130(Enter) => �ڵ尪 : 49 51 48 13 10
			// 0 => ASCII �ڵ尪 : 48
			System.out.println("���� �Է� [���� : ctrl + z]");
			while((data = System.in.read()) != 13) {
				data = data - 48;
				
				num = num * 10 + data;
				
				System.out.println(num);
				
				int s = 0;
				
				for (int i = 1; i <= num; i++) {
					s += i;
				}
				
				System.out.println("��� : " + s);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}

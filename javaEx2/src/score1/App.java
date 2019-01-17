package score1;

import java.util.Scanner;

import com.util.DBConn;

public class App {

	public static void main(String[] args) {
		Score ss=new ScoreImpl();
		Scanner sc=new Scanner(System.in);
		int ch;
		
		System.out.println("Statement �������̽� ����... ");
		
		while(true) {
			do {
				System.out.print("1.�߰� 2.���� 3.���� 4.�й��˻� 5.�̸��˻� 6.����Ʈ 7.���� => ");
				ch=sc.nextInt();
			} while(ch<1||ch>7);
			
			if(ch==7)
				break;
			
			switch (ch) {
			case 1:ss.insertScore(); break;
			case 2:ss.updateScore(); break;
			case 3:ss.deleteScore(); break;
			case 4:ss.searchHak(); break;
			case 5:ss.searchName(); break;
			case 6:ss.listScore(); break;
			}
		}
		
		// DB Connection�� ������ �� ���� ���Ḧ ���ָ�ȴ�.
		DBConn.close();
		sc.close();
	}
}

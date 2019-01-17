package db.member2;

import java.util.Scanner;

import com.util.DBConn;

public class MemberApp {

	public static void main(String[] args) {
		Member mb = new MemberImpl();
		Scanner sc = new Scanner(System.in);
		int ch;
		
		while(true) {
			do {
				System.out.print("1.��� 2.���� 3.���� 4.���̵�˻� 5.�̸��˻� 6.����Ʈ 7.���� => ");
				ch=sc.nextInt();
			} while(ch<1||ch>7);
			
			if(ch==7)
				break;
			
			switch (ch) {
			case 1:mb.insertMember(); break;
			case 2:mb.updateMember(); break;
			case 3:mb.deleteMember(); break;
			case 4:mb.searchId(); break;
			case 5:mb.serachName(); break;
			case 6:mb.listMember(); break;
			}
		}
		
		// DB Connection�� ������ �� ���� ���Ḧ ���ָ�ȴ�.
		DBConn.close();
		sc.close();
	}

}

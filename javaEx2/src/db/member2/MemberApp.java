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
				System.out.print("1.등록 2.수정 3.삭제 4.아이디검색 5.이름검색 6.리스트 7.종료 => ");
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
		
		// DB Connection은 마지막 한 번만 종료를 해주면된다.
		DBConn.close();
		sc.close();
	}

}

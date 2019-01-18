package score3;

import java.util.Scanner;

import com.util.DBConn;

public class App {

	public static void main(String[] args) {
		Score ss=new ScoreImpl();
		Scanner sc=new Scanner(System.in);
		int ch;
		
		// CallableStatement Procedure를 처리하는 인터페이스
		System.out.println("CallableStatement 인터페이스 예제... ");
		
		while(true) {
			do {
				System.out.print("1.추가 2.수정 3.삭제 4.학번검색 5.이름검색 6.리스트 7.평균 8.종료 => ");
				ch=sc.nextInt();
			} while(ch<1||ch>8);
			
			if(ch==8)
				break;
			
			switch (ch) {
			case 1:ss.insertScore(); break;
			case 2:ss.updateScore(); break;
			case 3:ss.deleteScore(); break;
			case 4:ss.searchHak(); break;
			case 5:ss.searchName(); break;
			case 6:ss.listScore(); break;
			case 7:ss.average(); break;
			}
		}
		
		// DB Connection은 마지막 한 번만 종료를 해주면된다.
		DBConn.close();
		sc.close();
	}
}

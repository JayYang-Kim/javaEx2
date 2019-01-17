package score1;

import java.util.Scanner;

import com.util.DBConn;

public class App {

	public static void main(String[] args) {
		Score ss=new ScoreImpl();
		Scanner sc=new Scanner(System.in);
		int ch;
		
		System.out.println("Statement 인터페이스 예제... ");
		
		while(true) {
			do {
				System.out.print("1.추가 2.수정 3.삭제 4.학번검색 5.이름검색 6.리스트 7.종료 => ");
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
		
		// DB Connection은 마지막 한 번만 종료를 해주면된다.
		DBConn.close();
		sc.close();
	}
}

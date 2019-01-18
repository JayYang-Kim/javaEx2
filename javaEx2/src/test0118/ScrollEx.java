package test0118;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.util.DBConn;

public class ScrollEx {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Connection conn = DBConn.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		
		int ch;
		String sql;
		
		try {
			sql = "SELECT hak, name, birth, kor, eng, mat FROM score";
			
			// ResultSet : 기본은 순방향으로 이동 가능하지만, 역방향으로도 이동 가능하도록 설정
			// DB변화 감지, 수정불가능
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			rs = stmt.executeQuery(sql);
			
			while(true) {
				do {
					System.out.println("1.처음 2.이전 3.다음 4.마지막 5.종료");
					ch = sc.nextInt();
				} while(ch < 1 || ch > 5);
				
				if (ch == 5) {
					break;
				}
				
				switch (ch) {
				case 1: if (rs.first()) { 
							System.out.println("처음 : " + rs.getString(1) + " : " + rs.getString(2));
						} break;
				case 2: if (rs.previous()) { 
							System.out.println("이전 : " + rs.getString(1) + " : " + rs.getString(2));
						} break;
				case 3: if (rs.next()) { 
							System.out.println("다음 : " + rs.getString(1) + " : " + rs.getString(2));
						} break;
				case 4: if (rs.last()) { 
							System.out.println("마지막 : " + rs.getString(1) + " : " + rs.getString(2));
						} break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (Exception e) {
				
			}
		}
		
		DBConn.close();
		sc.close();
	}

}

package test0116;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.util.DBConn;

public class Test2 {

	public static void main(String[] args) {
		Connection conn = DBConn.getConnection();
		
		// Statement 쿼리를 실행하는 역활 (Interface)
		Statement stmt = null;
		
		String sql;
		int result;
		
		try {
			// Connection 객체 안에 Statement 객체를 가져오는 메소드 createStatement()  
			stmt = conn.createStatement();
			
			// 쿼리의 마지막에;이 있으면 ORA-00911 오류가 발생하므로 조심
			// 자바에서 INSERT, UPDATE, DELETE하면 자동 커밋됨
			sql = "INSERT INTO score(hak, name, birth, kor, eng, mat) VALUES('111', '홍길동', '2000-10-20', 80, 20, 10)";
			
			/*sql = "INSERT INTO score(hak, name, birth, kor, eng, mat)";
			sql += " VALUES('111', '홍길동', '2000-10-20', 80, 20, 10)";*/
			
			// executeUpdate : SELECT를 제외한 쿼리(DDL, DML) 실행
			// Return값 : (DDL)을 실행했을경우 변경된 데이터 개수를 리턴한다.
			result = stmt.executeUpdate(sql);
			
			if (result == 1) {
				System.out.println("추가 성공");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e2) {
					
				}
			}
			
			DBConn.close();
		}

	}

}

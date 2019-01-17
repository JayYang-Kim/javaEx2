package test0116;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.util.DBConn;

public class Test3 {

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
			
			// 수정
			sql = "UPDATE score SET kor = 100, eng = 90, mat = 90 WHERE hak = '111'";
			//sql = "UPDATE score SET kor = 100, eng = 90, mat = 90 WHERE hak = '000'"; // 조건에 일치하지 않는 레코드가 없어도 오류는 아님
			
			// executeUpdate : SELECT를 제외한 쿼리(DDL, DML) 실행
			// Return값 : (DDL)을 실행했을경우 변경된 데이터 개수를 리턴한다.
			result = stmt.executeUpdate(sql);
			
			System.out.println(result + "(개) 레코드가 수정 되었습니다.");
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

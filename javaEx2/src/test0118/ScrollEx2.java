package test0118;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.util.DBConn;

public class ScrollEx2 {

	public static void main(String[] args) {
		Connection conn = DBConn.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql;
		
		try {
			// SELECT 문을 이용하여 수정할 경우에는 SELECT * FROM score 처럼 쿼리를 만들면 안된다.
			// 아래 처럼 반드시 출력할 컬럼명을 입력해야한다.
			sql = "SELECT hak, name, birth, kor, eng, mat FROM score";
			
			// DB변화 감지, 수정가능
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			rs = stmt.executeQuery(sql);
			
			// 처음 데이터를 name : 유선, kor = 50
			if (rs.next()) {
				System.out.println(rs.getString(2) + " : " + rs.getString(4));
				
				rs.updateString("name", "유선");
				rs.updateInt("kor", 50);
				rs.updateRow(); // 바로 수정 처리
			}
			
			// 처음으로 이동 (수정된 내용 확인)
			if (rs.absolute(1)) {
				System.out.println(rs.getString(2) + " : " + rs.getString(4));
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
	}

}

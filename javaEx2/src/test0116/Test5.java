package test0116;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.util.DBConn;

public class Test5 {

	public static void main(String[] args) {
		Connection conn = DBConn.getConnection();
		
		// Statement 쿼리를 실행하는 역활 (Interface)
		Statement stmt = null;
		
		// 오라클의 Cursor와 같은 객체
		ResultSet rs = null;
		
		String sql;
		
		try {
			// Connection 객체 안에 Statement 객체를 가져오는 메소드 createStatement()  
			stmt = conn.createStatement();
			
			sql = "SELECT hak, name, birth, kor, eng, mat FROM score WHERE hak = '111'";
			
			// SELECT 문장만 실행
			rs = stmt.executeQuery(sql);
			
			// rs.next() : 오라클의 PETCH와 같은 기능
			if (rs.next()) { // true : 데이터가 있다, false : 데이터가 없다.
				String hak = rs.getString("hak");
				//String hak = rs.getString(1);
				
				String name = rs.getString("name");
				//String name = rs.getString(2);
				
				// NUMBER, DATE형은 String형으로 반환 받을 수 있다.
				// [기본] YYYY-MM-DD HH24:MI:SS
				String birth = rs.getString("birth");
				//String birth = rs.getString(3);
				
				int kor = rs.getInt("kor");
				int eng = rs.getInt("eng");
				int mat = rs.getInt("mat");
				
				System.out.println("hak : " + hak);
				System.out.println("name : " + name);
				System.out.println("birth : " + birth); 
				System.out.println("kor : " + kor);
				System.out.println("eng : " + eng);
				System.out.println("mat : " + mat);
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

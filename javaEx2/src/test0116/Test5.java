package test0116;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.util.DBConn;

public class Test5 {

	public static void main(String[] args) {
		Connection conn = DBConn.getConnection();
		
		// Statement ������ �����ϴ� ��Ȱ (Interface)
		Statement stmt = null;
		
		// ����Ŭ�� Cursor�� ���� ��ü
		ResultSet rs = null;
		
		String sql;
		
		try {
			// Connection ��ü �ȿ� Statement ��ü�� �������� �޼ҵ� createStatement()  
			stmt = conn.createStatement();
			
			sql = "SELECT hak, name, birth, kor, eng, mat FROM score WHERE hak = '111'";
			
			// SELECT ���常 ����
			rs = stmt.executeQuery(sql);
			
			// rs.next() : ����Ŭ�� PETCH�� ���� ���
			if (rs.next()) { // true : �����Ͱ� �ִ�, false : �����Ͱ� ����.
				String hak = rs.getString("hak");
				//String hak = rs.getString(1);
				
				String name = rs.getString("name");
				//String name = rs.getString(2);
				
				// NUMBER, DATE���� String������ ��ȯ ���� �� �ִ�.
				// [�⺻] YYYY-MM-DD HH24:MI:SS
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

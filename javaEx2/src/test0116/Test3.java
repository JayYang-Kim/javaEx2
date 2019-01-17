package test0116;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.util.DBConn;

public class Test3 {

	public static void main(String[] args) {
		Connection conn = DBConn.getConnection();
		
		// Statement ������ �����ϴ� ��Ȱ (Interface)
		Statement stmt = null;
		
		String sql;
		int result;
		
		try {
			// Connection ��ü �ȿ� Statement ��ü�� �������� �޼ҵ� createStatement()  
			stmt = conn.createStatement();
			
			// ������ ��������;�� ������ ORA-00911 ������ �߻��ϹǷ� ����
			// �ڹٿ��� INSERT, UPDATE, DELETE�ϸ� �ڵ� Ŀ�Ե�
			
			// ����
			sql = "UPDATE score SET kor = 100, eng = 90, mat = 90 WHERE hak = '111'";
			//sql = "UPDATE score SET kor = 100, eng = 90, mat = 90 WHERE hak = '000'"; // ���ǿ� ��ġ���� �ʴ� ���ڵ尡 ��� ������ �ƴ�
			
			// executeUpdate : SELECT�� ������ ����(DDL, DML) ����
			// Return�� : (DDL)�� ����������� ����� ������ ������ �����Ѵ�.
			result = stmt.executeUpdate(sql);
			
			System.out.println(result + "(��) ���ڵ尡 ���� �Ǿ����ϴ�.");
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

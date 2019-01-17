package test0116;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.util.DBConn;

public class Test2 {

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
			sql = "INSERT INTO score(hak, name, birth, kor, eng, mat) VALUES('111', 'ȫ�浿', '2000-10-20', 80, 20, 10)";
			
			/*sql = "INSERT INTO score(hak, name, birth, kor, eng, mat)";
			sql += " VALUES('111', 'ȫ�浿', '2000-10-20', 80, 20, 10)";*/
			
			// executeUpdate : SELECT�� ������ ����(DDL, DML) ����
			// Return�� : (DDL)�� ����������� ����� ������ ������ �����Ѵ�.
			result = stmt.executeUpdate(sql);
			
			if (result == 1) {
				System.out.println("�߰� ����");
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

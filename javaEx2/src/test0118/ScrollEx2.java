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
			// SELECT ���� �̿��Ͽ� ������ ��쿡�� SELECT * FROM score ó�� ������ ����� �ȵȴ�.
			// �Ʒ� ó�� �ݵ�� ����� �÷����� �Է��ؾ��Ѵ�.
			sql = "SELECT hak, name, birth, kor, eng, mat FROM score";
			
			// DB��ȭ ����, ��������
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			rs = stmt.executeQuery(sql);
			
			// ó�� �����͸� name : ����, kor = 50
			if (rs.next()) {
				System.out.println(rs.getString(2) + " : " + rs.getString(4));
				
				rs.updateString("name", "����");
				rs.updateInt("kor", 50);
				rs.updateRow(); // �ٷ� ���� ó��
			}
			
			// ó������ �̵� (������ ���� Ȯ��)
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

package test0118;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import com.util.DBConn;

public class Mysqlplus {
	//static Connection conn = DBConn.getConnection();
	
	// �ش� �ϴ� ������ �����Ͽ� ����� ���� �޼ҵ�
	public static void execute(String sql) {
		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			if (sql.toUpperCase().startsWith("SELECT")) {
				
				rs = pstmt.executeQuery();
				rsmd = rs.getMetaData();
				
				// �÷� ��
				int cols = rsmd.getColumnCount();
				
				for (int i = 1; i <= cols; i++) {
					System.out.println(rsmd.getColumnName(i));
				}
				
				System.out.println();
				
				for (int i = 1; i <= cols; i++) {
					System.out.print("==========");
				}
				
				System.out.println();
				
				while (rs.next()) {
					for (int i = 1; i <= cols; i++) {
						System.out.println(rs.getString(i));
					}
					
					System.out.println();
				}
				
			} else {
				int result = pstmt.executeUpdate();
				
				if (sql.toUpperCase().startsWith("INSERT")) {
					System.out.println(result + "���� �߰��Ǿ���ϴ�.");
				} else if (sql.toUpperCase().startsWith("UPDATE")) {
					System.out.println(result + "���� �����Ǿ����ϴ�.");
				} else if (sql.toUpperCase().startsWith("DELETE") ) {
					System.out.println(result + "���� �����Ǿ����ϴ�.");
				} else {
					System.out.println("������ ����Ǿ����ϴ�.");
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {

				}
			}
		}
	}
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String sql, s;
		int n;
		
		gogo:
		while (true) {
			try {
				System.out.print("SQL > ");
				sql = "";
				n = 1;
				
				do {
					s = br.readLine();
					
					if (s == null || s.equalsIgnoreCase("exit")) { // ctrl + z �Ǵ� exit �Է� ��
						DBConn.close();
						
						System.exit(0);
					}
					
					s = s.trim(); // ���� ����
					
					sql += s + " ";
					
					if (sql.trim().length() == 0) {
						continue gogo;
					}
					
					if (s.lastIndexOf(";") == -1) {
						System.out.print((++n) + " ");
					}
				} while(sql.lastIndexOf(";") == -1); // ������ �������� ;(�����ݷ�)�� ������ ��� ����
				
				sql = sql.trim();
				sql = sql.substring(0, sql.lastIndexOf(";"));
				
				if (sql.length() == 0) {
					continue;
				}
				
				System.out.println(sql);
				
				execute(sql);
				
			} catch (Exception e) {

			}
		}
	}

}

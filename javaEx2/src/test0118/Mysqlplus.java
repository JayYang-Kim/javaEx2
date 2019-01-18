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
	
	// 해당 하는 쿼리를 실행하여 결과를 보는 메소드
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
				
				// 컬럼 수
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
					System.out.println(result + "행이 추가되어습니다.");
				} else if (sql.toUpperCase().startsWith("UPDATE")) {
					System.out.println(result + "행이 수정되었습니다.");
				} else if (sql.toUpperCase().startsWith("DELETE") ) {
					System.out.println(result + "행이 삭제되었습니다.");
				} else {
					System.out.println("쿼리가 실행되었습니다.");
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
					
					if (s == null || s.equalsIgnoreCase("exit")) { // ctrl + z 또는 exit 입력 시
						DBConn.close();
						
						System.exit(0);
					}
					
					s = s.trim(); // 공백 제거
					
					sql += s + " ";
					
					if (sql.trim().length() == 0) {
						continue gogo;
					}
					
					if (s.lastIndexOf(";") == -1) {
						System.out.print((++n) + " ");
					}
				} while(sql.lastIndexOf(";") == -1); // 마지막 쿼리문에 ;(세미콜론)이 없으면 계속 실행
				
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

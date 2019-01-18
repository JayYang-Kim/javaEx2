package test0118;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import com.util.DBConn;

public class MetaEx {

	public static void main(String[] args) {
		Connection conn = DBConn.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String sql;
		
		try {
			System.out.println("쿼리?");
			sql = br.readLine();
			
			// DB변화 감지, 수정가능
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			rsmd = rs.getMetaData();
			
			// 컬럼 수
			int cols = rsmd.getColumnCount();
			
			for (int i = 1; i <= cols; i++) {
				System.out.println(rsmd.getColumnName(i));
			}
			
			System.out.println();
			
			while (rs.next()) {
				for (int i = 1; i <= cols; i++) {
					System.out.println(rs.getString(i));
				}
				
				System.out.println();
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

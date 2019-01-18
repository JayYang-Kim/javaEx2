package test0118;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.util.DBConn;

public class TransactionEx1 {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = null;
		
		String sql;
		String id, name, birth, tel;
		
		try {
			System.out.print("아이디?");
			id = br.readLine();
			
			System.out.print("이름?");
			name = br.readLine();
			
			System.out.print("생일?");
			birth = br.readLine();
			
			System.out.print("전화번호?");
			tel = br.readLine();
			
			// 트랜잭션 처리 (기본적으로 자동 commit되는 것은 commit 되지 않도록 설정)
			conn.setAutoCommit(false);
			
			// test1
			sql = "INSERT INTO test1(id, name) VALUES(?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			
			// test2
			sql = "INSERT INTO test2(id, birth) VALUES(?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, birth);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			
			// test3
			sql = "INSERT INTO test3(id, tel) VALUES(?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, tel);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			
			conn.commit(); // 수동 커밋 처리
			
			System.out.println("추가 성공");
		} catch (SQLException e) {
			System.out.println(e);
			
			try {
				conn.rollback();
			} catch (Exception e2) {
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (Exception e2) {

			}
			
			DBConn.close();
		}

	}

}

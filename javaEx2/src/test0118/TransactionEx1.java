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
			System.out.print("���̵�?");
			id = br.readLine();
			
			System.out.print("�̸�?");
			name = br.readLine();
			
			System.out.print("����?");
			birth = br.readLine();
			
			System.out.print("��ȭ��ȣ?");
			tel = br.readLine();
			
			// Ʈ����� ó�� (�⺻������ �ڵ� commit�Ǵ� ���� commit ���� �ʵ��� ����)
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
			
			conn.commit(); // ���� Ŀ�� ó��
			
			System.out.println("�߰� ����");
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

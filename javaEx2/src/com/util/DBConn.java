package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	private static Connection conn;
	
	private DBConn() {
	}
	
	// Oracle JDBC ���� ó��
	public static Connection getConnection() {
		String url = "jdbc:oracle:thin:@211.238.142.194:1521:xe";
		String user = "sky";
		String pw = "java$!";
		
		if (conn == null) {
			try {
				// OracleDriver Ŭ���� ���� �ε�
				Class.forName("oracle.jdbc.driver.OracleDriver"); // JDK 6.0���ʹ� �������� (�ڵ��ε�)
				
				// Connection ��ü ����
				conn = DriverManager.getConnection(url, user, pw);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return conn;
	}
	
	public static void close() {
		if (conn != null) {
			try {
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				
			}
		}
		
		conn = null;
	}
}

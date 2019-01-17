package test0116;

import java.sql.Connection;

import com.util.DBConn;

public class Test1 {

	public static void main(String[] args) {
		// DB 연동  테스트
		Connection conn = DBConn.getConnection();
		
		System.out.println(conn);
	}

}

package test0116;

import java.sql.Connection;

import com.util.DBConn;

public class Test1 {

	public static void main(String[] args) {
		// DB ����  �׽�Ʈ
		Connection conn = DBConn.getConnection();
		
		System.out.println(conn);
	}

}

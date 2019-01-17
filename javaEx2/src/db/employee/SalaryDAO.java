package db.employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.util.DBConn;

public class SalaryDAO {
	private Connection conn = DBConn.getConnection();
	
	public int insertSalary(SalaryDTO dto) {
		int result=0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "INSERT INTO salary(salarynum, sabeon, paydate, paymentdate, pay, sudang, tax, memo) VALUES(SALARY_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getSabeon());
			pstmt.setString(2, dto.getPayDate());
			pstmt.setString(3, dto.getPaymentDate());
			pstmt.setInt(4, dto.getPay());
			pstmt.setInt(5, dto.getSudang());
			pstmt.setInt(6, dto.getTax());
			pstmt.setString(7, dto.getMemo());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.getMessage();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {

				}
			}
		}
		
		return result;
	}
	
	public int updateSalary(SalaryDTO dto) {
		int result=0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "UPDATE salary SET paydate = ?, paymentdate = ?, pay = ?, sudang = ?, tax = ?, memo = ? WHERE sabeon = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getPayDate());
			pstmt.setString(2, dto.getPaymentDate());
			pstmt.setInt(3, dto.getPay());
			pstmt.setInt(4, dto.getSudang());
			pstmt.setInt(5, dto.getTax());
			pstmt.setString(6, dto.getMemo());
			pstmt.setString(7, dto.getSabeon());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.getMessage();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {

				}
			}
		}
		
		return result;
	}

	public int deleteSalary(int salaryNum) {
		int result=0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "DELETE FROM salary WHERE salarynum = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, salaryNum);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.getMessage();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {

				}
			}
		}

		return result;
	}
	
	public SalaryDTO readSalary(int salaryNum) {
		SalaryDTO dto=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT salarynum, sabeon, paydate, payment, paymentdate, pay, sudang, tax, memo FROM salary WHERE salarynum = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, salaryNum);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
			}
		} catch (Exception e) {
			e.getMessage();
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
		
		return dto;
	}
	
	public List<SalaryDTO> listSalary(String payDate) {
		List<SalaryDTO> list=new ArrayList<>();
		
		return list;
	}
	
	public List<SalaryDTO> listSalary(Map<String, Object> map) {
		List<SalaryDTO> list=new ArrayList<>();
		
		return list;
	}

	public List<SalaryDTO> listSalary() {
		List<SalaryDTO> list=new ArrayList<>();
		
		
		return list;
	}

}

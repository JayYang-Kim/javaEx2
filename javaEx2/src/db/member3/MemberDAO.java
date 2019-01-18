package db.member3;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

import oracle.jdbc.OracleTypes;

public class MemberDAO {
	private Connection conn=DBConn.getConnection();
	
	public int insertMember(MemberDTO dto) {
		int result = 0;
		CallableStatement cstmt = null;
		String sql;
		
		try {
			sql = "{CALL insertMember(?, ?, ?, ?, ?, ?)}";
			
			cstmt = conn.prepareCall(sql);
			
			cstmt.setString(1, dto.getId());
			cstmt.setString(2, dto.getPwd());
			cstmt.setString(3, dto.getName());
			cstmt.setString(4, dto.getBirth());
			cstmt.setString(5, dto.getEmail());
			cstmt.setString(6, dto.getTel());
			
			cstmt.executeUpdate();
			
			result = 1;
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			if(cstmt!=null) {
				try {
					cstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		
		return result;
	}
	
	public int updateMember(MemberDTO dto) {
		int result = 0;
		CallableStatement cstmt = null;
		String sql;
		
		try {
			sql = "{CALL updateMember(?, ?, ?, ?, ?, ?)}";
			
			cstmt = conn.prepareCall(sql);
			
			cstmt.setString(1, dto.getId());
			cstmt.setString(2, dto.getPwd());
			cstmt.setString(3, dto.getName());
			cstmt.setString(4, dto.getBirth());
			cstmt.setString(5, dto.getEmail());
			cstmt.setString(6, dto.getTel());
			
			cstmt.executeQuery();
			
			result = 1;
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			if(cstmt!=null) {
				try {
					cstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		
		return result;
	}
	
	public MemberDTO readMember(String id) {
		MemberDTO dto=null;
		CallableStatement cstmt=null;
		ResultSet rs=null;
		String sql;
		
		try {
			sql = "{CALL readMember(?, ?)}";
			
			cstmt = conn.prepareCall(sql);
			
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			
			cstmt.setString(2, id);
			
			cstmt.executeQuery();
			
			rs = (ResultSet)cstmt.getObject(1);
			
			if(rs.next()) {
				dto = new MemberDTO(); // select 결과가 null이거나 있을경우 확인하기 위해서 조건문 안에서 DTO 객체를 생성
				
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				dto.setName(rs.getString("name"));
				dto.setBirth(rs.getString("birth"));
				dto.setEmail(rs.getString("email"));
				dto.setTel(rs.getString("tel"));
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}
			
			if(cstmt!=null) {
				try {
					cstmt.close();
				} catch (Exception e2) {
				}
			}
		}		
		return dto;
	}
	
	public List<MemberDTO> listMember() {
		List<MemberDTO> list=new ArrayList<>();
		CallableStatement cstmt=null;
		ResultSet rs=null;
		String sql;
		
		try {
			sql = "{CALL listMember(?)}";
			
			cstmt = conn.prepareCall(sql);
			
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			
			cstmt.executeUpdate();
			
			rs = (ResultSet)cstmt.getObject(1);
			
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				dto.setName(rs.getString("name"));
				dto.setBirth(rs.getString("birth"));
				dto.setEmail(rs.getString("email"));
				dto.setTel(rs.getString("tel"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}
			
			if(cstmt!=null) {
				try {
					cstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		
		return list;
	}
	
	public List<MemberDTO> listMember(String val) {
		List<MemberDTO> list=new ArrayList<>();
		CallableStatement cstmt=null;
		ResultSet rs=null;
		String sql;
		
		try {
			sql = "{CALL searchNameMember(?, ?)}";
			
			cstmt = conn.prepareCall(sql);
			
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			
			cstmt.setString(2, val);
			
			cstmt.executeQuery();
			
			rs = (ResultSet)cstmt.getObject(1);
			
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				dto.setName(rs.getString("name"));
				dto.setBirth(rs.getString("birth"));
				dto.setEmail(rs.getString("email"));
				dto.setTel(rs.getString("tel"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}
			
			if(cstmt!=null) {
				try {
					cstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		
		return list;
	}
	
	public int deleteMember(String id) {
		int result=0;
		CallableStatement cstmt=null;
		String sql;
		
		try {
			sql = "{CALL deleteMember(?)}";
			
			cstmt = conn.prepareCall(sql);
			
			cstmt.setString(1, id);
			
			cstmt.executeUpdate();
			
			result = 1;
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			if(cstmt!=null) {
				try {
					cstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		
		return result;
	}
}

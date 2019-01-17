package db.member2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

public class MemberDAO {
	private Connection conn=DBConn.getConnection();
	
	public int insertMember(MemberDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			sb.append("INSERT ALL INTO member1(id, pwd, name) VALUES(?, ?, ?)");
			sb.append(" INTO member2(id, birth, email, tel) VALUES(?, ?, ?, ?)");
			sb.append(" SELECT * FROM dual");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getId());
			pstmt.setString(5, dto.getBirth());
			pstmt.setString(6, dto.getEmail());
			pstmt.setString(7, dto.getTel());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		
		return result;
	}
	
	public int updateMember(MemberDTO dto) {
		int result=0;
		PreparedStatement pstmt=null;
		StringBuilder sb=new StringBuilder();
		
		try {
			sb.append("UPDATE member1 SET ");
			sb.append("pwd = ?,");
			sb.append("name = ?");
			sb.append("  WHERE id = ?");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, dto.getPwd());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getId());
			
			result = pstmt.executeUpdate();
			
			sb = new StringBuilder();
			
			sb.append("UPDATE member2 SET ");
			sb.append("birth = ?,");
			sb.append("email = ?,");
			sb.append("tel = ?");
			sb.append("  WHERE id = ?");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, dto.getBirth());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getTel());
			pstmt.setString(4, dto.getId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		
		return result;
	}
	
	public MemberDTO readMember(String id) {
		MemberDTO dto=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuilder sb=new StringBuilder();
		
		try {
			sb.append("SELECT	m1.id, pwd, name, TO_CHAR(birth, 'YYYY-MM-DD') as birth, email, tel");
			sb.append(" FROM member1 m1");
			sb.append(" LEFT OUTER JOIN member2 m2");
			sb.append(" ON m1.id = m2.id");
			sb.append(" WHERE m1.id = ?");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			//dto=new MemberDTO(); // 메소드를 호출할때마다 새로운 객체가 생성되어 null을 체크할 수 없다.
			
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
			
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}		
		return dto;
	}
	
	public List<MemberDTO> listMember() {
		List<MemberDTO> list=new ArrayList<>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuilder sb=new StringBuilder();
		
		try {
			sb.append("SELECT  m1.id, pwd, name, TO_CHAR(birth, 'YYYY-MM-DD') as birth, email, tel");
			sb.append(" FROM member1 m1");
			sb.append(" LEFT OUTER JOIN member2 m2");
			sb.append(" ON m1.id = m2.id");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			rs = pstmt.executeQuery();
			
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
			
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		
		return list;
	}
	
	public List<MemberDTO> listMember(String val) {
		List<MemberDTO> list=new ArrayList<>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuilder sb=new StringBuilder();
		
		try {
			sb.append("SELECT  m1.id, pwd, name, TO_CHAR(birth, 'YYYY-MM-DD') as birth, email, tel");
			sb.append(" FROM member1 m1");
			sb.append(" LEFT OUTER JOIN member2 m2");
			sb.append(" 	ON m1.id = m2.id");
			sb.append(" WHERE INSTR(name, ?) >= 1");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, val);
			
			rs = pstmt.executeQuery();
			
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
			
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		
		return list;
	}
	
	public int deleteMember(String id) {
		int result=0;
		PreparedStatement pstmt=null;
		String sql;
		
		try {
			sql = "DELETE FROM member2 WHERE id = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
			
			sql = "DELETE FROM member1 WHERE id = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		
		return result;
	}
}

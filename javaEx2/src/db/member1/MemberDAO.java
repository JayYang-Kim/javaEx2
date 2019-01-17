package db.member1;

import java.sql.Connection;
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
		Statement stmt = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			sb.append("INSERT ALL");
			sb.append("	INTO member1(id, pwd, name) VALUES(");
			sb.append("'" + dto.getId() + "',");
			sb.append("'" + dto.getPwd() + "',");
			sb.append("'" + dto.getName() + "'");
			sb.append(")");
			sb.append("INTO member2(id, birth, email, tel) VALUES(");
			sb.append("'" + dto.getId() + "',");
			sb.append("'" + dto.getBirth() + "',");
			sb.append("'" + dto.getEmail() + "',");
			sb.append("'" + dto.getTel() + "'");
			sb.append(")");
			sb.append("SELECT *");
			sb.append("FROM dual");
			
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sb.toString()); // executeUpdate는 파라메터가 String 자료형이기 때문에 StringBuilder를 String 자료형으로 변환하여 파라메터를 전달한다.
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			if(stmt!=null) {
				try {
					stmt.close();
				} catch (Exception e2) {
				}
			}
		}
		
		return result;
	}
	
	public int updateMember(MemberDTO dto) {
		int result=0;
		Statement stmt=null;
		StringBuilder sb=new StringBuilder();
		
		try {
			stmt = conn.createStatement();
			
			sb.append("UPDATE member1 SET ");
			sb.append("pwd = '" + dto.getPwd() + "',");
			sb.append("name = '" + dto.getName() + "'");
			sb.append("  WHERE id = '" + dto.getId() + "'");
			
			result = stmt.executeUpdate(sb.toString());
			
			sb = new StringBuilder();
			
			sb.append("UPDATE member2 SET ");
			sb.append("birth = '" + dto.getBirth() + "',");
			sb.append("email = '" + dto.getEmail() + "',");
			sb.append("tel = '" + dto.getTel() + "'");
			sb.append("  WHERE id = '" + dto.getId() + "'");
			
			result = stmt.executeUpdate(sb.toString());
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			if(stmt!=null) {
				try {
					stmt.close();
				} catch (Exception e2) {
				}
			}
		}
		
		return result;
	}
	
	public MemberDTO readMember(String id) {
		MemberDTO dto=null;
		Statement stmt=null;
		ResultSet rs=null;
		StringBuilder sb=new StringBuilder();
		
		try {
			sb.append("SELECT	m1.id, pwd, name, TO_CHAR(birth, 'YYYY-MM-DD') as birth, email, tel");
			sb.append(" FROM member1 m1");
			sb.append(" LEFT OUTER JOIN member2 m2");
			sb.append(" ON m1.id = m2.id");
			sb.append(" WHERE m1.id = '" + id + "'");
			
			stmt = conn.createStatement();
			
			rs=stmt.executeQuery(sb.toString());
			
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
			
			if(stmt!=null) {
				try {
					stmt.close();
				} catch (Exception e2) {
				}
			}
		}		
		return dto;
	}
	
	public List<MemberDTO> listMember() {
		List<MemberDTO> list=new ArrayList<>();
		Statement stmt=null;
		ResultSet rs=null;
		StringBuilder sb=new StringBuilder();
		
		try {
			sb.append("SELECT  m1.id, pwd, name, TO_CHAR(birth, 'YYYY-MM-DD') as birth, email, tel");
			sb.append(" FROM member1 m1");
			sb.append(" LEFT OUTER JOIN member2 m2");
			sb.append(" ON m1.id = m2.id");
			
			stmt = conn.createStatement();
			
			rs=stmt.executeQuery(sb.toString());
			
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
			
			if(stmt!=null) {
				try {
					stmt.close();
				} catch (Exception e2) {
				}
			}
		}
		
		return list;
	}
	
	public List<MemberDTO> listMember(String val) {
		List<MemberDTO> list=new ArrayList<>();
		Statement stmt=null;
		ResultSet rs=null;
		StringBuilder sb=new StringBuilder();
		
		try {
			sb.append("SELECT  m1.id, pwd, name, TO_CHAR(birth, 'YYYY-MM-DD') as birth, email, tel");
			sb.append(" FROM member1 m1");
			sb.append(" LEFT OUTER JOIN member2 m2");
			sb.append(" 	ON m1.id = m2.id");
			sb.append(" WHERE INSTR(name, '"+val+"') >= 1");
			
			stmt = conn.createStatement();
			
			rs=stmt.executeQuery(sb.toString());
			
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
			
			if(stmt!=null) {
				try {
					stmt.close();
				} catch (Exception e2) {
				}
			}
		}
		
		return list;
	}
	
	public int deleteMember(String id) {
		int result=0;
		Statement stmt=null;
		String sql;
		
		try {
			stmt = conn.createStatement();
			sql = "DELETE FROM member2 WHERE id='" + id + "'";
			
			result = stmt.executeUpdate(sql);
			
			sql = "DELETE FROM member1 WHERE id='" + id + "'";
			
			result = stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			if(stmt!=null) {
				try {
					stmt.close();
				} catch (Exception e2) {
				}
			}
		}
		
		return result;
	}
}

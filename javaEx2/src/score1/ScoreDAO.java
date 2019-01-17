package score1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

// DAO(Data Access Object) : insert, update, delete, select 등 데이터 처리를 주 목적으로 하는 클래스
// CRUD : Create(INSERT), Retrieve(SELECT), Update, Delete 앞자를 따온 단어로 DB 작업을 나타냄
public class ScoreDAO {
	private Connection conn=DBConn.getConnection();
	
	public int insertScore(ScoreDTO dto) {
		int result=0;
		Statement stmt=null;
		StringBuilder sb=new StringBuilder();
		
		try {
			sb.append("INSERT INTO score(hak, name, birth, kor, eng, mat) VALUES (");
			sb.append("'" + dto.getHak() + "',");
			sb.append("'" + dto.getName() + "',");
			sb.append("'" + dto.getBirth() + "',");
			sb.append(dto.getKor() + ",");
			sb.append(dto.getEng() + ",");
			sb.append(dto.getMat());
			sb.append(")");
			
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
	
	public int updateScore(ScoreDTO dto) {
		int result=0;
		Statement stmt=null;
		StringBuilder sb=new StringBuilder();
		
		try {
			sb.append("UPDATE score SET ");
			sb.append("name='" + dto.getName() + "',");
			sb.append("birth='" + dto.getBirth() + "',");
			sb.append("kor="+dto.getKor() + ",");
			sb.append("eng="+dto.getEng() + ",");
			sb.append("mat="+dto.getMat());
			sb.append("  WHERE hak='" + dto.getHak() + "'");
			
			stmt = conn.createStatement();
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
	
	public ScoreDTO readScore(String hak) {
		ScoreDTO dto=null;
		Statement stmt=null;
		ResultSet rs=null;
		StringBuilder sb=new StringBuilder();
		
		try {
			sb.append("SELECT hak, name, TO_CHAR(birth, 'YYYY-MM-DD') birth, ");
			sb.append("   kor, eng, mat, (kor+eng+mat) tot, ") ;
			sb.append("   (kor+eng+mat)/3 ave") ;
			sb.append("   FROM score");
			sb.append("   WHERE hak = '"+hak+"'");
			
			stmt = conn.createStatement();
			
			rs=stmt.executeQuery(sb.toString());
			
			//dto=new ScoreDTO(); // 메소드를 호출할때마다 새로운 객체가 생성되어 null을 체크할 수 없다.
			
			if(rs.next()) {
				dto=new ScoreDTO(); // select 결과가 null이거나 있을경우 확인하기 위해서 조건문 안에서 DTO 객체를 생성
				
				dto.setHak(rs.getString("hak"));
				dto.setName(rs.getString("name"));
				dto.setBirth(rs.getString("birth"));
				dto.setKor(rs.getInt("kor"));
				dto.setEng(rs.getInt("eng"));
				dto.setMat(rs.getInt("mat"));
				dto.setTot(rs.getInt("tot"));
				dto.setAve(rs.getInt("ave"));
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
	
	public List<ScoreDTO> listScore() {
		List<ScoreDTO> list=new ArrayList<>();
		Statement stmt=null;
		ResultSet rs=null;
		StringBuilder sb=new StringBuilder();
		
		try {
			sb.append("SELECT hak, name, TO_CHAR(birth, 'YYYY-MM-DD') birth, ");
			sb.append("   kor, eng, mat, (kor+eng+mat) tot, ") ;
			sb.append("   (kor+eng+mat)/3 ave, ") ;
			sb.append("   RANK() OVER(ORDER BY (kor+eng+mat) DESC) rank ");
			sb.append("FROM score");
			
			stmt = conn.createStatement();
			
			rs=stmt.executeQuery(sb.toString());
			
			while(rs.next()) {
				ScoreDTO dto=new ScoreDTO();
				
				dto.setHak(rs.getString("hak"));
				dto.setName(rs.getString("name"));
				dto.setBirth(rs.getString("birth"));
				dto.setKor(rs.getInt("kor"));
				dto.setEng(rs.getInt("eng"));
				dto.setMat(rs.getInt("mat"));
				dto.setTot(rs.getInt("tot"));
				dto.setAve(rs.getInt("ave"));
				dto.setRank(rs.getInt("rank"));
				
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
	
	public List<ScoreDTO> listScore(String val) {
		List<ScoreDTO> list=new ArrayList<>();
		Statement stmt=null;
		ResultSet rs=null;
		StringBuilder sb=new StringBuilder();
		
		try {
			sb.append("SELECT hak, name, TO_CHAR(birth, 'YYYY-MM-DD') birth, ");
			sb.append("   kor, eng, mat, (kor+eng+mat) tot, ") ;
			sb.append("   (kor+eng+mat)/3 ave") ;
			sb.append("   FROM score");
			sb.append("   WHERE INSTR(name, '"+val+"') >= 1");
			
			stmt = conn.createStatement();
			
			rs=stmt.executeQuery(sb.toString());
			
			while(rs.next()) {
				ScoreDTO dto=new ScoreDTO();
				
				dto.setHak(rs.getString("hak"));
				dto.setName(rs.getString("name"));
				dto.setBirth(rs.getString("birth"));
				dto.setKor(rs.getInt("kor"));
				dto.setEng(rs.getInt("eng"));
				dto.setMat(rs.getInt("mat"));
				dto.setTot(rs.getInt("tot"));
				dto.setAve(rs.getInt("ave"));
				
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
	
	public int deleteScore(String hak) {
		int result=0;
		Statement stmt=null;
		String sql;
		
		try {
			sql = "DELETE FROM score WHERE hak='" + hak + "'";
			
			stmt = conn.createStatement();
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

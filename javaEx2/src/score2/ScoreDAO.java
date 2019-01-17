package score2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

// DAO(Data Access Object) : insert, update, delete, select 등 데이터 처리를 주 목적으로 하는 클래스
// CRUD : Create(INSERT), Retrieve(SELECT), Update, Delete 앞자를 따온 단어로 DB 작업을 나타냄
public class ScoreDAO {
	private Connection conn=DBConn.getConnection();
	
	public int insertScore(ScoreDTO dto) {
		int result=0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = "INSERT INTO score(hak, name, birth, kor, eng, mat) VALUES (?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getHak());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getBirth());
			pstmt.setInt(4, dto.getKor());
			pstmt.setInt(5, dto.getEng());
			pstmt.setInt(6, dto.getMat());
			
			result = pstmt.executeUpdate(); // 절대로 쿼리를 매개변수로 대입하지 않는다.
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
	
	public int updateScore(ScoreDTO dto) {
		int result=0;
		PreparedStatement pstmt=null;
		String sql;
		
		try {
			sql = "UPDATE score SET name = ?, birth = ?, kor = , eng = , mat = ? WHERE hak = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getBirth());
			pstmt.setInt(3, dto.getKor());
			pstmt.setInt(4, dto.getEng());
			pstmt.setInt(5, dto.getMat());
			pstmt.setString(6, dto.getHak());
			
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
	
	public ScoreDTO readScore(String hak) {
		ScoreDTO dto=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuilder sb=new StringBuilder();
		
		try {
			sb.append("SELECT hak, name, TO_CHAR(birth, 'YYYY-MM-DD') birth, ");
			sb.append("   kor, eng, mat, (kor+eng+mat) tot, ") ;
			sb.append("   (kor+eng+mat)/3 ave") ;
			sb.append("   FROM score");
			sb.append("   WHERE hak = ?");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, hak);
			
			rs = pstmt.executeQuery();
			
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
			
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}		
		return dto;
	}
	
	public List<ScoreDTO> listScore() {
		List<ScoreDTO> list=new ArrayList<>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuilder sb=new StringBuilder();
		
		try {
			sb.append("SELECT hak, name, TO_CHAR(birth, 'YYYY-MM-DD') birth, ");
			sb.append("   kor, eng, mat, (kor+eng+mat) tot, ") ;
			sb.append("   (kor+eng+mat)/3 ave, ") ;
			sb.append("   RANK() OVER(ORDER BY (kor+eng+mat) DESC) rank ");
			sb.append("FROM score");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			rs = pstmt.executeQuery();
			
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
			
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		
		return list;
	}
	
	public List<ScoreDTO> listScore(String val) {
		List<ScoreDTO> list=new ArrayList<>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuilder sb=new StringBuilder();
		
		try {
			sb.append("SELECT hak, name, TO_CHAR(birth, 'YYYY-MM-DD') birth, ");
			sb.append("   kor, eng, mat, (kor+eng+mat) tot, ") ;
			sb.append("   (kor+eng+mat)/3 ave") ;
			sb.append("   FROM score");
			sb.append("   WHERE INSTR(name, ?) >= 1");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, val);
			
			rs=pstmt.executeQuery();
			
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
			
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		
		return list;
	}
	
	public int deleteScore(String hak) {
		int result=0;
		PreparedStatement pstmt=null;
		String sql;
		
		try {
			sql = "DELETE FROM score WHERE hak = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, hak);
			
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

package score1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

// DAO(Data Access Object) : insert, update, delete, select �� ������ ó���� �� �������� �ϴ� Ŭ����
// CRUD : Create(INSERT), Retrieve(SELECT), Update, Delete ���ڸ� ���� �ܾ�� DB �۾��� ��Ÿ��
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
			result = stmt.executeUpdate(sb.toString()); // executeUpdate�� �Ķ���Ͱ� String �ڷ����̱� ������ StringBuilder�� String �ڷ������� ��ȯ�Ͽ� �Ķ���͸� �����Ѵ�.
			
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
			
			//dto=new ScoreDTO(); // �޼ҵ带 ȣ���Ҷ����� ���ο� ��ü�� �����Ǿ� null�� üũ�� �� ����.
			
			if(rs.next()) {
				dto=new ScoreDTO(); // select ����� null�̰ų� ������� Ȯ���ϱ� ���ؼ� ���ǹ� �ȿ��� DTO ��ü�� ����
				
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

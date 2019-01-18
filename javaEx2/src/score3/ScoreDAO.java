package score3;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.util.DBConn;

import oracle.jdbc.OracleTypes;

// DAO(Data Access Object) : insert, update, delete, select 등 데이터 처리를 주 목적으로 하는 클래스
// CRUD : Create(INSERT), Retrieve(SELECT), Update, Delete 앞자를 따온 단어로 DB 작업을 나타냄
public class ScoreDAO {
	private Connection conn=DBConn.getConnection();
	
	public int insertScore(ScoreDTO dto) {
		int result=0;
		CallableStatement cstmt = null;
		String sql;
		
		try {
			// 프로시져 실행 쿼리문
			sql = "{CALL insertScore(?, ?, ?, ?, ?, ?)}";
			
			cstmt = conn.prepareCall(sql);
			
			cstmt.setString(1, dto.getHak());
			cstmt.setString(2, dto.getName());
			cstmt.setString(3, dto.getBirth());
			cstmt.setInt(4, dto.getKor());
			cstmt.setInt(5, dto.getEng());
			cstmt.setInt(6, dto.getMat());
			
			cstmt.executeUpdate(); // 프로시져는 명령 실행 개수를 리턴하지 않고, 프로시져 실행 여부를 리턴한다.
			
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
	
	public int updateScore(ScoreDTO dto) {
		int result=0;
		CallableStatement cstmt=null;
		String sql;
		
		try {
			sql = "{CALL updateScore(?, ?, ?, ?, ?, ?)}";
			
			cstmt = conn.prepareCall(sql);
			
			// 프로시져에서 파라메터(매개변수)를 정의한 순서대로 입력
			cstmt.setString(1, dto.getHak());
			cstmt.setString(2, dto.getName());
			cstmt.setString(3, dto.getBirth());
			cstmt.setInt(4, dto.getKor());
			cstmt.setInt(5, dto.getEng());
			cstmt.setInt(6, dto.getMat());
			
			cstmt.executeUpdate();
			
			result = 1;
		} catch (SQLException e) {
			System.out.println(e.toString());
			System.out.println(e.getMessage());
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
	
	public ScoreDTO readScore(String hak) {
		ScoreDTO dto=null;
		CallableStatement cstmt=null;
		ResultSet rs=null;
		String sql;
		
		try {
			sql = "{CALL readScore(?, ?)}";
			
			cstmt = conn.prepareCall(sql);
			
			// OUT 파라미터는 타입 설정
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			
			// IN 파라미터는 setter로 값을 설정
			cstmt.setString(2, hak);
			
			cstmt.executeUpdate();
			
			// OUT 파라메터값 받아오기
			rs = (ResultSet)cstmt.getObject(1); // 파라메터 결과값이 하나가 아니기 때문에 Object로 변환하고 ResultSet 변환처리
			
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
			
			if(cstmt!=null) {
				try {
					cstmt.close();
				} catch (Exception e2) {
				}
			}
		}		
		return dto;
	}
	
	public List<ScoreDTO> listScore() {
		List<ScoreDTO> list=new ArrayList<>();
		CallableStatement cstmt=null;
		ResultSet rs=null;
		String sql;
		
		try {
			sql = "{CALL listScore(?)}";
			
			cstmt = conn.prepareCall(sql);
			
			// OUT 파라미터는 타입 설정
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			
			// IN 파라미터는 setter로 값을 설정
			cstmt.executeUpdate(); // 프로시져는 SELECT 문도 executeUpdate()로 실행한다.
			
			// OUT 파라메터값 받아오기
			rs = (ResultSet)cstmt.getObject(1); // 파라메터 결과값이 하나가 아니기 때문에 Object로 변환하고 ResultSet 변환처리
			
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
			
			if(cstmt!=null) {
				try {
					cstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		
		return list;
	}
	
	public List<ScoreDTO> listScore(String val) {
		List<ScoreDTO> list=new ArrayList<>();
		CallableStatement cstmt=null;
		ResultSet rs=null;
		String sql;
		
		try {
			sql = "{CALL listNameScore(?, ?)}";
			
			cstmt = conn.prepareCall(sql);
			
			// OUT 파라미터는 타입 설정
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			
			// IN 파라미터는 setter로 값을 설정
			cstmt.setString(2, val);
			
			cstmt.executeUpdate(); // 프로시져는 SELECT 문도 executeUpdate()로 실행한다.
			
			// OUT 파라메터값 받아오기
			rs = (ResultSet)cstmt.getObject(1); // 파라메터 결과값이 하나가 아니기 때문에 Object로 변환하고 ResultSet 변환처리
			
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
			
			if(cstmt!=null) {
				try {
					cstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		
		return list;
	}
	
	public int deleteScore(String hak) {
		int result=0;
		CallableStatement cstmt=null;
		String sql;
		
		try {
			sql = "{CALL deleteScore(?)}";
			
			cstmt = conn.prepareCall(sql);
			
			cstmt.setString(1, hak);
			
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
	
	public Map<String, Integer> averageScore() {
		Map<String, Integer> map = new HashMap<>();
		CallableStatement cstmt = null;
		String sql;
		
		try {
			sql = "{CALL listNameScore(?, ?, ?)}";
			
			cstmt = conn.prepareCall(sql);
			
			cstmt.registerOutParameter(1, OracleTypes.INTEGER);
			cstmt.registerOutParameter(2, OracleTypes.INTEGER);
			cstmt.registerOutParameter(3, OracleTypes.INTEGER);
			
			cstmt.executeUpdate();
			
			int kor = cstmt.getInt(1);
			//Integer kor = (Integer)cstmt.getObject(1);
			int eng = cstmt.getInt(2);
			int mat = cstmt.getInt(3);
			
			map.put("kor", kor);
			map.put("eng", eng);
			map.put("mat", mat);
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if(cstmt!=null) {
				try {
					cstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		
		return map;
	}
}

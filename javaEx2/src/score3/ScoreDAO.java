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

// DAO(Data Access Object) : insert, update, delete, select �� ������ ó���� �� �������� �ϴ� Ŭ����
// CRUD : Create(INSERT), Retrieve(SELECT), Update, Delete ���ڸ� ���� �ܾ�� DB �۾��� ��Ÿ��
public class ScoreDAO {
	private Connection conn=DBConn.getConnection();
	
	public int insertScore(ScoreDTO dto) {
		int result=0;
		CallableStatement cstmt = null;
		String sql;
		
		try {
			// ���ν��� ���� ������
			sql = "{CALL insertScore(?, ?, ?, ?, ?, ?)}";
			
			cstmt = conn.prepareCall(sql);
			
			cstmt.setString(1, dto.getHak());
			cstmt.setString(2, dto.getName());
			cstmt.setString(3, dto.getBirth());
			cstmt.setInt(4, dto.getKor());
			cstmt.setInt(5, dto.getEng());
			cstmt.setInt(6, dto.getMat());
			
			cstmt.executeUpdate(); // ���ν����� ��� ���� ������ �������� �ʰ�, ���ν��� ���� ���θ� �����Ѵ�.
			
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
			
			// ���ν������� �Ķ����(�Ű�����)�� ������ ������� �Է�
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
			
			// OUT �Ķ���ʹ� Ÿ�� ����
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			
			// IN �Ķ���ʹ� setter�� ���� ����
			cstmt.setString(2, hak);
			
			cstmt.executeUpdate();
			
			// OUT �Ķ���Ͱ� �޾ƿ���
			rs = (ResultSet)cstmt.getObject(1); // �Ķ���� ������� �ϳ��� �ƴϱ� ������ Object�� ��ȯ�ϰ� ResultSet ��ȯó��
			
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
			
			// OUT �Ķ���ʹ� Ÿ�� ����
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			
			// IN �Ķ���ʹ� setter�� ���� ����
			cstmt.executeUpdate(); // ���ν����� SELECT ���� executeUpdate()�� �����Ѵ�.
			
			// OUT �Ķ���Ͱ� �޾ƿ���
			rs = (ResultSet)cstmt.getObject(1); // �Ķ���� ������� �ϳ��� �ƴϱ� ������ Object�� ��ȯ�ϰ� ResultSet ��ȯó��
			
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
			
			// OUT �Ķ���ʹ� Ÿ�� ����
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			
			// IN �Ķ���ʹ� setter�� ���� ����
			cstmt.setString(2, val);
			
			cstmt.executeUpdate(); // ���ν����� SELECT ���� executeUpdate()�� �����Ѵ�.
			
			// OUT �Ķ���Ͱ� �޾ƿ���
			rs = (ResultSet)cstmt.getObject(1); // �Ķ���� ������� �ϳ��� �ƴϱ� ������ Object�� ��ȯ�ϰ� ResultSet ��ȯó��
			
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

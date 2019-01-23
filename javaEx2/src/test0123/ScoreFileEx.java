package test0123;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class ScoreVO {
	private String hak;
	private String name;
	private int kor;
	private int eng;
	private int mat;
	private int tot;
	private int ave;
	
	public String getHak() {
		return hak;
	}
	public void setHak(String hak) {
		this.hak = hak;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMat() {
		return mat;
	}
	public void setMat(int mat) {
		this.mat = mat;
	}
	public int getTot() {
		return tot;
	}
	public void setTot(int tot) {
		this.tot = tot;
	}
	public int getAve() {
		return ave;
	}
	public void setAve(int ave) {
		this.ave = ave;
	}
}

class ScoreService {
	private List<ScoreVO> list = new ArrayList<>();
	
	public void loadFile(String pathname) throws FileNotFoundException, IOException, Exception {
		BufferedReader br = null;
		String str;
		
		File f = new File(pathname);
		
		// 방법1)
		/*if (!f.exists()) {
			throw new FileNotFoundException(pathname + " 파일은 존재하지 않습니다.");
		}*/
		
		// 방법2)
		try {
			br = new BufferedReader(new FileReader(f));
			
			// 한줄씩 읽을때는 readLine() 사용
			// 전체 데이터 다 읽었으면 null
			while ((str = br.readLine()) != null) {
				str = str.trim();
				
				if (str.length() == 0) {
					continue;
				}
				
				ScoreVO vo = new ScoreVO();
				String []s = str.split("\\s"); // 공백을 가지고 한줄에 있는 데이터를 구분 (\s : 공백)
				
				vo.setHak(s[0]);
				vo.setName(s[1]);
				vo.setKor(Integer.parseInt(s[2]));
				vo.setEng(Integer.parseInt(s[3]));
				vo.setMat(Integer.parseInt(s[4]));
				
				vo.setTot(vo.getKor() + vo.getEng() + vo.getMat());
				vo.setAve(vo.getTot() / 3);
				
				list.add(vo);
			}
		} catch (FileNotFoundException e) {
			throw e; 
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e2) {

				}
			}
		}
	}
	
	public void saveOrderByTot(String pathname) {

		if (list.size() == 0) {
			System.out.println("등록된 자료가 앖습니다.");
			return;
		}
		
		BufferedWriter bw = null;
		
		try {
			File f = new  File(pathname);
			bw = new BufferedWriter(new FileWriter(f));
			//bw = new BufferedWriter(new FileWriter(pathname));
			
			// 총점 내림차순 정렬, 총점이 동일한 경우 국어 점수 내림차순 정렬
			Comparator<ScoreVO> comp = new Comparator<ScoreVO>() {
				
				@Override
				public int compare(ScoreVO o1, ScoreVO o2) {
					if (o1.getTot() == o2.getTot()) {
						return -(o1.getKor() - o2.getKor());
					} else {
						//return o1.getTot() - o2.getTot(); // 오름차순
						return -(o1.getTot() - o2.getTot()); // 내림차순
					}
				}
				
			};
			
			// 정렬 처리
			Collections.sort(list, comp);
			
			String s;
			for (ScoreVO vo : list) {
				s = String.format("%s %s %d %d %d %d %d", vo.getHak(), vo.getName(), vo.getKor(), vo.getEng(), vo.getMat(), vo.getTot(), vo.getAve());
				bw.write(s + "\n");
			}
			
			// flush를 해주지 않으면 데이터가 모두 입력되지 않을 수 있지만, 하단에서 close처리를 해서 따로 flush처리를 해주지 않아도 된다.
			bw.flush();
		} catch (IOException e) {
			System.out.println(e.toString());
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (Exception e2) {

				}
			}
		}
	}
	
	public void saveOrderByName(String pathname) {

		if (list.size() == 0) {
			System.out.println("등록된 자료가 앖습니다.");
			return;
		}
		
		BufferedWriter bw = null;
		
		try {
			File f = new  File(pathname);
			bw = new BufferedWriter(new FileWriter(f));
			//bw = new BufferedWriter(new FileWriter(pathname));
			
			// 이름 오름차순
			Comparator<ScoreVO> comp = new Comparator<ScoreVO>() {
				
				@Override
				public int compare(ScoreVO o1, ScoreVO o2) {
					return o1.getName().compareTo(o2.getName());
				}
				
			};
			
			// 정렬 처리
			Collections.sort(list, comp);
			
			String s;
			for (ScoreVO vo : list) {
				s = String.format("%s %s %d %d %d %d %d", vo.getHak(), vo.getName(), vo.getKor(), vo.getEng(), vo.getMat(), vo.getTot(), vo.getAve());
				bw.write(s + "\n");
			}
			
			// flush를 해주지 않으면 데이터가 모두 입력되지 않을 수 있지만, 하단에서 close처리를 해서 따로 flush처리를 해주지 않아도 된다.
			bw.flush();
		} catch (IOException e) {
			System.out.println(e.toString());
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (Exception e2) {

				}
			}
		}
	}
}

public class ScoreFileEx {

	public static void main(String[] args) {
		ScoreService ss = new ScoreService();
		
		try {
			ss.loadFile("score1.txt");
			
			// 총점순
			ss.saveOrderByTot("score2.txt");
			
			// 이름순
			ss.saveOrderByName("score3.txt");
			
			System.out.println("완료");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}

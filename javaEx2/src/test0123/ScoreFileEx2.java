package test0123;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

class ScoreDTO implements Comparable<ScoreDTO> {
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
	
	@Override
	public int compareTo(ScoreDTO o) {
		// ���� ��������
		return this.getTot() - o.getTot();
	}
}

class Score {
	// TreeSet : Comparable ���� ��ü�� ����
	// �������� ���������Ƿ� ������ �����ϸ� �ϳ��� ����� (Set�� �ߺ� ��� ����)
	private Set<ScoreDTO> set = new TreeSet<>();
	
	public void loadFile(String pathname) throws FileNotFoundException, IOException, Exception {
		BufferedReader br = null;
		String str;
		
		File f = new File(pathname);
		
		try {
			br = new BufferedReader(new FileReader(f));
			
			while ((str = br.readLine()) != null) {
				str = str.trim();
				
				if (str.length() == 0) {
					continue;
				}
				
				ScoreDTO vo = new ScoreDTO();
				String []s = str.split("\\s"); // ������ ������ ���ٿ� �ִ� �����͸� ���� (\s : ����)
				
				vo.setHak(s[0]);
				vo.setName(s[1]);
				vo.setKor(Integer.parseInt(s[2]));
				vo.setEng(Integer.parseInt(s[3]));
				vo.setMat(Integer.parseInt(s[4]));
				
				vo.setTot(vo.getKor() + vo.getEng() + vo.getMat());
				vo.setAve(vo.getTot() / 3);
				
				set.add(vo);
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

		if (set.size() == 0) {
			System.out.println("��ϵ� �ڷᰡ �׽��ϴ�.");
			return;
		}
		
		BufferedWriter bw = null;
		
		try {
			File f = new  File(pathname);
			bw = new BufferedWriter(new FileWriter(f));
			
			String s;
			for (ScoreDTO vo : set) {
				s = String.format("%s %s %d %d %d %d %d", vo.getHak(), vo.getName(), vo.getKor(), vo.getEng(), vo.getMat(), vo.getTot(), vo.getAve());
				bw.write(s + "\n");
			}
			
			// flush�� ������ ������ �����Ͱ� ��� �Էµ��� ���� �� ������, �ϴܿ��� closeó���� �ؼ� ���� flushó���� ������ �ʾƵ� �ȴ�.
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

public class ScoreFileEx2 {

	public static void main(String[] args) {
		Score ss = new Score();
		
		try {
			ss.loadFile("score1.txt");
			
			// ������
			ss.saveOrderByTot("score2.txt");
			
			System.out.println("�Ϸ�");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}

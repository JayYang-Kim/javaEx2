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
		
		// ���1)
		/*if (!f.exists()) {
			throw new FileNotFoundException(pathname + " ������ �������� �ʽ��ϴ�.");
		}*/
		
		// ���2)
		try {
			br = new BufferedReader(new FileReader(f));
			
			// ���پ� �������� readLine() ���
			// ��ü ������ �� �о����� null
			while ((str = br.readLine()) != null) {
				str = str.trim();
				
				if (str.length() == 0) {
					continue;
				}
				
				ScoreVO vo = new ScoreVO();
				String []s = str.split("\\s"); // ������ ������ ���ٿ� �ִ� �����͸� ���� (\s : ����)
				
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
			System.out.println("��ϵ� �ڷᰡ �׽��ϴ�.");
			return;
		}
		
		BufferedWriter bw = null;
		
		try {
			File f = new  File(pathname);
			bw = new BufferedWriter(new FileWriter(f));
			//bw = new BufferedWriter(new FileWriter(pathname));
			
			// ���� �������� ����, ������ ������ ��� ���� ���� �������� ����
			Comparator<ScoreVO> comp = new Comparator<ScoreVO>() {
				
				@Override
				public int compare(ScoreVO o1, ScoreVO o2) {
					if (o1.getTot() == o2.getTot()) {
						return -(o1.getKor() - o2.getKor());
					} else {
						//return o1.getTot() - o2.getTot(); // ��������
						return -(o1.getTot() - o2.getTot()); // ��������
					}
				}
				
			};
			
			// ���� ó��
			Collections.sort(list, comp);
			
			String s;
			for (ScoreVO vo : list) {
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
	
	public void saveOrderByName(String pathname) {

		if (list.size() == 0) {
			System.out.println("��ϵ� �ڷᰡ �׽��ϴ�.");
			return;
		}
		
		BufferedWriter bw = null;
		
		try {
			File f = new  File(pathname);
			bw = new BufferedWriter(new FileWriter(f));
			//bw = new BufferedWriter(new FileWriter(pathname));
			
			// �̸� ��������
			Comparator<ScoreVO> comp = new Comparator<ScoreVO>() {
				
				@Override
				public int compare(ScoreVO o1, ScoreVO o2) {
					return o1.getName().compareTo(o2.getName());
				}
				
			};
			
			// ���� ó��
			Collections.sort(list, comp);
			
			String s;
			for (ScoreVO vo : list) {
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

public class ScoreFileEx {

	public static void main(String[] args) {
		ScoreService ss = new ScoreService();
		
		try {
			ss.loadFile("score1.txt");
			
			// ������
			ss.saveOrderByTot("score2.txt");
			
			// �̸���
			ss.saveOrderByName("score3.txt");
			
			System.out.println("�Ϸ�");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}

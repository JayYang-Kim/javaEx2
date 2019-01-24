package test0124;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class ScoreVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String hak;
	private String name;
	private int kor;
	private int eng;
	private int mat;
	
	// ���Ͽ� �������� ���� (��Ʈ��ũ�� �������� �ʴ´�.)
	// ����ȭ ��󿡼� ���� ó��
	private transient int tot;
	private transient int ave;
	
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
		tot = kor + eng + mat;
		return tot;
	}
	public int getAve() {
		ave = getTot() / 3;
		return ave;
	}
}

class ScoreService {
	private List<ScoreVO> list = new ArrayList<>();
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private String pathname = "score.dat";
	
	public void loadFile() {
		File f = new File(pathname);
		
		if (!f.exists()) {
			return;
		}
		
		ObjectInputStream ois = null;
		
		try {
			ois = new ObjectInputStream(new FileInputStream(pathname));
			
			while (true) {
				ScoreVO vo = (ScoreVO)ois.readObject();
				if (vo == null) {
					break;
				}
				
				list.add(vo);
			}
		} catch (EOFException e) {
			// ������ ���� �����Ҷ� �߻��ϴ� ����
		} catch (FileNotFoundException e) {
			System.out.println(e.toString());
		} catch (IOException e) {
			System.out.println(e.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (Exception e2) {

				}
			}
		}
	}
	
	public void saveFile() {
		ObjectOutputStream oos = null;
		
		try {
			// ObjectOutputStream�� ������ append�� ó���� �� ����.
			oos = new ObjectOutputStream(new FileOutputStream(pathname));
			
			for (ScoreVO vo : list) {
				oos.writeObject(vo);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (Exception e2) {

				}
			}
		}
	}
	
	public void input() {
		System.out.println("�Է�");
		
		ScoreVO vo = new ScoreVO();
		
		try {
			System.out.println("�й�?");
			vo.setHak(br.readLine());
			
			System.out.println("�̸�?");
			vo.setName(br.readLine());
			
			System.out.println("����?");
			vo.setKor(Integer.parseInt(br.readLine()));
			
			System.out.println("����?");
			vo.setEng(Integer.parseInt(br.readLine()));
			
			System.out.println("����?");
			vo.setMat(Integer.parseInt(br.readLine()));
			
			list.add(vo);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public void print() {
		System.out.println("���");
		
		for (ScoreVO vo : list) {
			System.out.println("�й� : " + vo.getHak());
			System.out.println("�̸� : " + vo.getName());
			System.out.println("���� : " + vo.getKor());
			System.out.println("���� : " + vo.getEng());
			System.out.println("���� : " + vo.getMat());
			System.out.println("���� : " + vo.getTot());
			System.out.println("���� : " + vo.getAve());
		}
	}
}

public class ObjectScoreEx {

	public static void main(String[] args) {
		ScoreService ss = new ScoreService();
		
		char ch;
		
		ss.loadFile();
		
		try {
			while (true) {
				do {
					System.out.println("1.�Է� 2.��� 3.����");
					ch = (char)System.in.read();
					
					System.in.skip(2);
				} while(ch < '1' || ch > '3');
				
				if (ch == '3') {
					ss.saveFile();
					break;
				}
				
				switch (ch) {
				case '1' : ss.input(); break;
				case '2' : ss.print(); break;
				}
			}
		} catch (Exception e) {
			
		}
	}

}

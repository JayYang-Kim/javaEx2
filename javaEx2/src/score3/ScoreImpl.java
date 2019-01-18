package score3;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ScoreImpl implements Score {
	private ScoreDAO dao=new ScoreDAO();
	private Scanner sc=new Scanner(System.in);
	
	@Override
	public void insertScore() {
		System.out.println("\n������ �߰�...");
		
		try {
			ScoreDTO dto=new ScoreDTO();
			
			System.out.print("�й�?");
			dto.setHak(sc.next());
			
			System.out.print("�̸�?");
			dto.setName(sc.next());
			
			System.out.print("�������?");
			dto.setBirth(sc.next());
			
			System.out.print("����?");
			dto.setKor(sc.nextInt());
			
			System.out.print("����?");
			dto.setEng(sc.nextInt());

			System.out.print("����?");
			dto.setMat(sc.nextInt());
			
			int result = dao.insertScore(dto);
			
			if(result == 1) {
				System.out.println("�����͸� �߰��߽��ϴ�.");
			} else {
				System.out.println("������ �߰��� �����߽��ϴ�.");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void updateScore() {
		System.out.println("\n������ ����...");
		
		try {
			ScoreDTO dto=new ScoreDTO();
			
			System.out.print("������ �й�?");
			dto.setHak(sc.next());
			
			System.out.print("�̸�?");
			dto.setName(sc.next());
			
			System.out.print("�������?");
			dto.setBirth(sc.next());
			
			System.out.print("����?");
			dto.setKor(sc.nextInt());
			
			System.out.print("����?");
			dto.setEng(sc.nextInt());

			System.out.print("����?");
			dto.setMat(sc.nextInt());
			
			int result=dao.updateScore(dto);
			System.out.println(result+"���� ���ڵ带 ���� �߽��ϴ�.");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void deleteScore() {
		System.out.println("\n������ ����...");
		String hak;
		
		System.out.print("������ �й�?");
		hak=sc.next();
		
		int result=dao.deleteScore(hak);
		System.out.println(result+"���� ���ڵ带 ���� �߽��ϴ�.");
	}

	@Override
	public void listScore() {
		System.out.println("\n��ü ����Ʈ...");
		
		List<ScoreDTO> list=dao.listScore();
		
		for(ScoreDTO dto : list) {
			System.out.print(dto.getHak()+"\t");
			System.out.print(dto.getName()+"\t");
			System.out.print(dto.getBirth()+"\t");
			System.out.print(dto.getKor()+"\t");
			System.out.print(dto.getEng()+"\t");
			System.out.print(dto.getMat()+"\t");
			System.out.print(dto.getTot()+"\t");
			System.out.print(dto.getAve()+"\t");
			System.out.print(dto.getRank()+"\n");
		}
		
	}

	@Override
	public void searchHak() {
		System.out.println("\n�й� �˻�...");
		
		String hak;
		System.out.print("�˻��� �й�?");
		hak=sc.next();
		
		ScoreDTO dto=dao.readScore(hak);
		if(dto==null) {
			System.out.println("��ϵ� �ڷᰡ �����ϴ�.");
			return;
		}
		
		System.out.print(dto.getHak()+"\t");
		System.out.print(dto.getName()+"\t");
		System.out.print(dto.getBirth()+"\t");
		System.out.print(dto.getKor()+"\t");
		System.out.print(dto.getEng()+"\t");
		System.out.print(dto.getMat()+"\t");
		System.out.print(dto.getTot()+"\t");
		System.out.print(dto.getAve()+"\n");
	}

	@Override
	public void searchName() {
		System.out.println("\n�̸� �˻�...");
		
		String name;
		System.out.print("�˻��� �̸�?");
		name=sc.next();
		
		List<ScoreDTO> list=dao.listScore(name);
		
		for(ScoreDTO dto : list) {
			System.out.print(dto.getHak()+"\t");
			System.out.print(dto.getName()+"\t");
			System.out.print(dto.getBirth()+"\t");
			System.out.print(dto.getKor()+"\t");
			System.out.print(dto.getEng()+"\t");
			System.out.print(dto.getMat()+"\t");
			System.out.print(dto.getTot()+"\t");
			System.out.print(dto.getAve()+"\n");
		}
	}

	@Override
	public void average() {
		System.out.println("\n���� ���...");
		
		Map<String, Integer> map = dao.averageScore();
		
		int kor = map.get("kor");
		int eng = map.get("eng");
		int mat = map.get("mat");
		
		System.out.println("���� : " + kor);
		System.out.println("���� : " + eng);
		System.out.println("���� : " + mat);
	}

}

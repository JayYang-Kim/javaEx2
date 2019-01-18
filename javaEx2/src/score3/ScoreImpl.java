package score3;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ScoreImpl implements Score {
	private ScoreDAO dao=new ScoreDAO();
	private Scanner sc=new Scanner(System.in);
	
	@Override
	public void insertScore() {
		System.out.println("\n데이터 추가...");
		
		try {
			ScoreDTO dto=new ScoreDTO();
			
			System.out.print("학번?");
			dto.setHak(sc.next());
			
			System.out.print("이름?");
			dto.setName(sc.next());
			
			System.out.print("생년월일?");
			dto.setBirth(sc.next());
			
			System.out.print("국어?");
			dto.setKor(sc.nextInt());
			
			System.out.print("영어?");
			dto.setEng(sc.nextInt());

			System.out.print("수학?");
			dto.setMat(sc.nextInt());
			
			int result = dao.insertScore(dto);
			
			if(result == 1) {
				System.out.println("데이터를 추가했습니다.");
			} else {
				System.out.println("데이터 추가가 실패했습니다.");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void updateScore() {
		System.out.println("\n데이터 수정...");
		
		try {
			ScoreDTO dto=new ScoreDTO();
			
			System.out.print("수정할 학번?");
			dto.setHak(sc.next());
			
			System.out.print("이름?");
			dto.setName(sc.next());
			
			System.out.print("생년월일?");
			dto.setBirth(sc.next());
			
			System.out.print("국어?");
			dto.setKor(sc.nextInt());
			
			System.out.print("영어?");
			dto.setEng(sc.nextInt());

			System.out.print("수학?");
			dto.setMat(sc.nextInt());
			
			int result=dao.updateScore(dto);
			System.out.println(result+"개의 레코드를 수정 했습니다.");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void deleteScore() {
		System.out.println("\n데이터 삭제...");
		String hak;
		
		System.out.print("삭제할 학번?");
		hak=sc.next();
		
		int result=dao.deleteScore(hak);
		System.out.println(result+"개의 레코드를 삭제 했습니다.");
	}

	@Override
	public void listScore() {
		System.out.println("\n전체 리스트...");
		
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
		System.out.println("\n학번 검색...");
		
		String hak;
		System.out.print("검색할 학번?");
		hak=sc.next();
		
		ScoreDTO dto=dao.readScore(hak);
		if(dto==null) {
			System.out.println("등록된 자료가 없습니다.");
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
		System.out.println("\n이름 검색...");
		
		String name;
		System.out.print("검색할 이름?");
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
		System.out.println("\n과목별 평균...");
		
		Map<String, Integer> map = dao.averageScore();
		
		int kor = map.get("kor");
		int eng = map.get("eng");
		int mat = map.get("mat");
		
		System.out.println("국어 : " + kor);
		System.out.println("영어 : " + eng);
		System.out.println("수학 : " + mat);
	}

}

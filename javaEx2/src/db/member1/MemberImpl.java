package db.member1;

import java.util.List;
import java.util.Scanner;

import score1.ScoreDTO;

public class MemberImpl implements Member {
	private MemberDAO dao = new MemberDAO();
	private Scanner sc = new Scanner(System.in);

	@Override
	public void insertMember() {
		System.out.println("\n데이터 추가...");
		
		try {
			MemberDTO dto=new MemberDTO();
			
			System.out.print("아이디?");
			dto.setId(sc.next());
			
			System.out.print("비밀번호?");
			dto.setPwd(sc.next());
			
			System.out.print("이름?");
			dto.setName(sc.next());
			
			System.out.print("생년월일?");
			dto.setBirth(sc.next());
			
			System.out.print("이메일?");
			dto.setEmail(sc.next());
			
			System.out.print("전화번호?");
			dto.setTel(sc.next());
			
			int result = dao.insertMember(dto);
			
			if(result == 2) {
				System.out.println("회원 등록 성공...");
			} else {
				System.out.println("데이터 추가가 실패했습니다.");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void updateMember() {
		System.out.println("\n데이터 수정...");
		
		try {
			System.out.println("수정할 아이디?");
			String id = sc.next();
			
			MemberDTO dto = dao.readMember(id);
			
			if (dto == null) {
				System.out.println("등록된 아이디가 없습니다.");
				return;
			}
			
			System.out.print("비밀번호?");
			dto.setPwd(sc.next());
			
			System.out.print("이름?");
			dto.setName(sc.next());
			
			System.out.print("생년월일?");
			dto.setBirth(sc.next());
			
			System.out.print("이메일?");
			dto.setEmail(sc.next());
			
			System.out.print("전화번호?");
			dto.setTel(sc.next());
			
			int result = dao.updateMember(dto);
			
			if (result >= 1) {
				System.out.println("회원정보 수정 성공");
			} else {
				System.out.println("회원정보 수정 실패");
			}
			
			/*if (result == 0) {
				System.out.println("등록된 아이디가 없습니다.");
			}
			
			System.out.println(result + "개의 레코드를 수정 했습니다.");*/
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void deleteMember() {
		System.out.println("\n데이터 삭제...");
		
		try {
			System.out.print("아이디?");
			String id = sc.next();
			
			int result = dao.deleteMember(id);
			System.out.println(result + "개의 레코드를 삭제 했습니다.");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void listMember() {
		System.out.println("\n전체 리스트...");
		
		List<MemberDTO> list = dao.listMember();
		
		for(MemberDTO dto : list) {
			System.out.print(dto.getId()+"\t");
			System.out.print(dto.getPwd()+"\t");
			System.out.print(dto.getName()+"\t");
			System.out.print(dto.getBirth()+"\t");
			System.out.print(dto.getEmail()+"\t");
			System.out.print(dto.getTel()+"\t");
			System.out.println();
		}
		
	}

	@Override
	public void searchId() {
		System.out.println("\n아이디 찾기...");
		
		try {
			System.out.print("아이디?");
			String id = sc.next();
			
			MemberDTO dto = dao.readMember(id);
			
			if(dto == null) {
				System.out.println("등록된 자료가 없습니다.");
				return;
			}
			
			System.out.print(dto.getId()+"\t");
			System.out.print(dto.getPwd()+"\t");
			System.out.print(dto.getName()+"\t");
			System.out.print(dto.getBirth()+"\t");
			System.out.print(dto.getEmail()+"\t");
			System.out.print(dto.getTel()+"\t");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void serachName() {
		System.out.println("\n이름 찾기...");
		
		try {
			System.out.print("이름?");
			String name = sc.next();
			
			List<MemberDTO> list = dao.listMember(name);
			
			for(MemberDTO dto : list) {
				System.out.print(dto.getId()+"\t");
				System.out.print(dto.getPwd()+"\t");
				System.out.print(dto.getName()+"\t");
				System.out.print(dto.getBirth()+"\t");
				System.out.print(dto.getEmail()+"\t");
				System.out.print(dto.getTel()+"\t");
				System.out.println();
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

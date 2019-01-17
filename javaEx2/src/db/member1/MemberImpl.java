package db.member1;

import java.util.List;
import java.util.Scanner;

import score1.ScoreDTO;

public class MemberImpl implements Member {
	private MemberDAO dao = new MemberDAO();
	private Scanner sc = new Scanner(System.in);

	@Override
	public void insertMember() {
		System.out.println("\n������ �߰�...");
		
		try {
			MemberDTO dto=new MemberDTO();
			
			System.out.print("���̵�?");
			dto.setId(sc.next());
			
			System.out.print("��й�ȣ?");
			dto.setPwd(sc.next());
			
			System.out.print("�̸�?");
			dto.setName(sc.next());
			
			System.out.print("�������?");
			dto.setBirth(sc.next());
			
			System.out.print("�̸���?");
			dto.setEmail(sc.next());
			
			System.out.print("��ȭ��ȣ?");
			dto.setTel(sc.next());
			
			int result = dao.insertMember(dto);
			
			if(result == 2) {
				System.out.println("ȸ�� ��� ����...");
			} else {
				System.out.println("������ �߰��� �����߽��ϴ�.");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void updateMember() {
		System.out.println("\n������ ����...");
		
		try {
			System.out.println("������ ���̵�?");
			String id = sc.next();
			
			MemberDTO dto = dao.readMember(id);
			
			if (dto == null) {
				System.out.println("��ϵ� ���̵� �����ϴ�.");
				return;
			}
			
			System.out.print("��й�ȣ?");
			dto.setPwd(sc.next());
			
			System.out.print("�̸�?");
			dto.setName(sc.next());
			
			System.out.print("�������?");
			dto.setBirth(sc.next());
			
			System.out.print("�̸���?");
			dto.setEmail(sc.next());
			
			System.out.print("��ȭ��ȣ?");
			dto.setTel(sc.next());
			
			int result = dao.updateMember(dto);
			
			if (result >= 1) {
				System.out.println("ȸ������ ���� ����");
			} else {
				System.out.println("ȸ������ ���� ����");
			}
			
			/*if (result == 0) {
				System.out.println("��ϵ� ���̵� �����ϴ�.");
			}
			
			System.out.println(result + "���� ���ڵ带 ���� �߽��ϴ�.");*/
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void deleteMember() {
		System.out.println("\n������ ����...");
		
		try {
			System.out.print("���̵�?");
			String id = sc.next();
			
			int result = dao.deleteMember(id);
			System.out.println(result + "���� ���ڵ带 ���� �߽��ϴ�.");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void listMember() {
		System.out.println("\n��ü ����Ʈ...");
		
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
		System.out.println("\n���̵� ã��...");
		
		try {
			System.out.print("���̵�?");
			String id = sc.next();
			
			MemberDTO dto = dao.readMember(id);
			
			if(dto == null) {
				System.out.println("��ϵ� �ڷᰡ �����ϴ�.");
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
		System.out.println("\n�̸� ã��...");
		
		try {
			System.out.print("�̸�?");
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

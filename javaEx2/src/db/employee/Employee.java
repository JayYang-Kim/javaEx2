package db.employee;

import java.util.List;
import java.util.Scanner;

import db.member2.MemberDTO;

public class Employee {
	private Scanner sc = new Scanner(System.in);
	private EmployeeDAO dao = new EmployeeDAO();
	
	public void employeeManage() {
		int ch;
		while(true) {
			System.out.println("\n[�������]");
			do {
				System.out.print("1.������ 2.�������� 3.����˻� 4.�̸��˻� 5.����Ʈ 6.���� => ");
				ch = sc.nextInt();
			}while(ch < 1 || ch > 6);
			
			if(ch == 6) return;
			
			switch(ch) {
			case 1 : insert(); break;
			case 2 : update(); break;
			case 3 : searchSabeon(); break;
			case 4 : searchName(); break;
			case 5 : list(); break;
			}
		}
	}
	
	public void insert() {
		System.out.println("\n��� ���...");
		
		try {
			EmployeeDTO dto = new EmployeeDTO();
			
			System.out.println("���?");
			dto.setSabeon(sc.next());
			
			System.out.println("�̸�?");
			dto.setName(sc.next());
			
			System.out.println("����?");
			dto.setBirth(sc.next());
			
			System.out.println("��ȭ��ȣ?");
			dto.setTel(sc.next());
			
			int result = dao.insertEmployee(dto);
			
			if (result == 1) {
				System.out.println(dto.getSabeon() + "(���)��  �����Ǿ����ϴ�.");
			} else {
				System.out.println("��� ��Ͽ� �����߽��ϴ�.");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void update() {
		System.out.println("\n��� ���� ����...");
		
		try {
			EmployeeDTO dto = new EmployeeDTO();
			
			System.out.println("������ ���?");
			dto.setSabeon(sc.next());
			
			System.out.println("�̸�?");
			dto.setName(sc.next());
			
			System.out.println("����?");
			dto.setBirth(sc.next());
			
			System.out.println("��ȭ��ȣ?");
			dto.setTel(sc.next());
			
			int result = dao.updateEmployee(dto);
			
			if (result == 1) {
				System.out.println(dto.getSabeon() + "(���)��  �����Ǿ����ϴ�.");
			} else {
				System.out.println("������� ������ �����߽��ϴ�.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void searchSabeon() {
		System.out.println("\n��� �˻�...");
		
		try {
			System.out.print("���?");
			String sabeon = sc.next();
			
			EmployeeDTO dto = dao.readEmployee(sabeon);
			
			if (dto == null) {
				System.out.println("��ϵ��� ���� ����Դϴ�.");
				return;
			}
			
			System.out.println("��� : " + dto.getSabeon());
			System.out.println("�̸� : " + dto.getName());
			System.out.println("���� : " + dto.getBirth());
			System.out.println("��ȭ��ȣ : " + dto.getTel());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void searchName() {
		System.out.println("\n�̸� �˻�...");
		
		try {
			System.out.println("�̸�?");
			String name = sc.next();
			
			List<EmployeeDTO> list = dao.listEmployee(name);
			
			for (EmployeeDTO dto : list) {
				System.out.println("��� : " + dto.getSabeon());
				System.out.println("�̸� : " + dto.getName());
				System.out.println("���� : " + dto.getBirth());
				System.out.println("��ȭ��ȣ : " + dto.getTel());
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void list() {
		System.out.println("\n��� ����Ʈ...");
		
		try {			
			List<EmployeeDTO> list = dao.listEmployee();
			
			for (EmployeeDTO dto : list) {
				System.out.println("��� : " + dto.getSabeon());
				System.out.println("�̸� : " + dto.getName());
				System.out.println("���� : " + dto.getBirth());
				System.out.println("��ȭ��ȣ : " + dto.getTel());
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}

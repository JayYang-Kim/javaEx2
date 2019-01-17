package db.employee;

import java.util.Scanner;

public class Salary {
	private Scanner sc = new Scanner(System.in);
	private SalaryDAO dao = new SalaryDAO();
	private Employee emp = null;
	
	public Salary(Employee emp) {
		this.emp = emp;
	}
	
	public void salaryManage() {
		int ch;
		while(true) {
			System.out.println("\n[�޿�����]");
			do {
				System.out.print("1.���� 2.���� 3.���� 4.��������Ʈ 5.����˻� 6.����Ʈ 7.�������Ʈ 8.���� => ");
				ch = sc.nextInt();
			}while(ch<1||ch>8);
			
			if(ch==8) return;
			
			switch(ch) {
			case 1:payment(); break;
			case 2:update(); break;
			case 3:delete(); break;
			case 4:monthList(); break;
			case 5:searchSabeon(); break;
			case 6:list(); break;
			case 7:emp.list(); break;
			}
		}
	}
	
	public void payment() {
		System.out.println("\n�޿� ����...");
		
		try {
			SalaryDTO dto = new SalaryDTO();
			
			System.out.println("���?");
			dto.setSabeon(sc.next());
			
			System.out.println("�޿����?");
			dto.setPayDate(sc.next());
			
			System.out.println("�޿���������?");
			dto.setPaymentDate(sc.next());
			
			System.out.println("�⺻��?");
			dto.setPay(sc.nextInt());
			
			System.out.println("����?");
			dto.setSudang(sc.nextInt());
			
			// ���� ���
			if (dto.getPay() + dto.getSudang() >= 3000000) {
				dto.setTax((int)((dto.getPay() + dto.getSudang()) * 0.03));
			} else if (dto.getPay() + dto.getSudang() >= 2000000) {
				dto.setTax((int)((dto.getPay() + dto.getSudang()) * 0.02));
			} else {
				dto.setTax(0);
			}
			
			System.out.println("�޸�?");
			dto.setMemo(sc.next());
			
			int result = dao.insertSalary(dto);
			
			if (result == 1) {
				System.out.println(dto.getSabeon() + "(���)�� �޿� ����������  �����Ǿ����ϴ�.");
			} else {
				System.out.println("�޿� �������� ����� �����߽��ϴ�.");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void update() {
		System.out.println("\n�޿� ����...");
		
		try {
			SalaryDTO dto = new SalaryDTO();
			
			System.out.println("������ ���?");
			dto.setSabeon(sc.next());
			
			System.out.println("�޿����?");
			dto.setPayDate(sc.next());
			
			System.out.println("�޿���������?");
			dto.setPaymentDate(sc.next());
			
			System.out.println("�⺻��?");
			dto.setPay(sc.nextInt());
			
			System.out.println("����?");
			dto.setSudang(sc.nextInt());
			
			// ���� ���
			if (dto.getPay() + dto.getSudang() >= 3000000) {
				dto.setTax((int)((dto.getPay() + dto.getSudang()) * 0.03));
			} else if (dto.getPay() + dto.getSudang() >= 2000000) {
				dto.setTax((int)((dto.getPay() + dto.getSudang()) * 0.02));
			} else {
				dto.setTax(0);
			}
			
			System.out.println("�޸�?");
			dto.setMemo(sc.next());
			
			int result = dao.updateSalary(dto);
			
			if (result == 1) {
				System.out.println(dto.getSabeon() + "(���)�� �޿� ����������  �����Ǿ����ϴ�.");
			} else {
				System.out.println("�޿� �������� ������ �����߽��ϴ�.");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void delete() {
		System.out.println("\n�޿� ����...");
		
		try {
			System.out.println("������ ��ȣ?");
			int salaryNum = sc.nextInt();
			
			int result = dao.deleteSalary(salaryNum);
			
			if (result == 1) {
				System.out.println("�޿� ������ �����Ǿ����ϴ�.");
			} else {
				System.out.println("�޿� ���� ���� �����߽��ϴ�.");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	public void searchSabeon() {
		System.out.println("\n��� �˻�...");
		
		
	}

	public void monthList() {
		System.out.println("\n���� ����Ʈ...");
		
	}
	
	public void list() {
		System.out.println("\n�޿� ����Ʈ...");
	
	}
}

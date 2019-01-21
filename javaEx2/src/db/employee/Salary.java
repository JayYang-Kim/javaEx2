package db.employee;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Salary {
	private Scanner sc=new Scanner(System.in);
	private SalaryDAO dao=new SalaryDAO();
	private Employee emp=null;
	
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
			SalaryDTO dto=new SalaryDTO();
			
			System.out.print("�����ȣ ?");
			dto.setSabeon(sc.next());
			
			System.out.print("�޿����[yyyymm]?");
			dto.setPayDate(sc.next());
			
			System.out.print("�޿���������[yyyymmdd]?");
			dto.setPaymentDate(sc.next());
			
			System.out.print("�⺻��?");
			dto.setPay(sc.nextInt());
			
			System.out.print("����?");
			dto.setSudang(sc.nextInt());

			System.out.print("�޸�?");
			dto.setMemo(sc.next());

			int tot=dto.getPay()+dto.getSudang();
			int tax=0;
			
			if (tot >= 3000000)
				tax = (int)(tot * 0.03);
			else if (tot >= 2000000)
				tax = (int)(tot * 0.02);
			else
				tax = 0;
			
			dto.setTax(tax);
			
			int result=dao.insertSalary(dto);
			
			if(result >= 1) {
				System.out.println("�޿� ���� �Ϸ� !!!");
			} else {
				System.out.println("�޿� ���� ���� !!!");
			}
			
		}catch (InputMismatchException e) {
			System.out.println("�Է� ������ ��ġ���� �ʽ��ϴ�. !!!");
		}catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public void update() {
		System.out.println("\n�޿� ����...");
		
		try {
			int salaryNum;
			System.out.print("������ �޿� ��ȣ ?");
			salaryNum=sc.nextInt();
			
			SalaryDTO dto = dao.readSalary(salaryNum);
			if(dto == null) {
				System.out.println("��ϵ� ������ �ƴմϴ�. !!!");
				return;
			}
			
			System.out.println("������� -> ");
			System.out.print(dto.getSabeon()+"\t");
			System.out.print(dto.getName()+"\t");
			System.out.print(dto.getPayDate()+"\t");
			System.out.print(dto.getPaymentDate()+"\t");
			System.out.print(dto.getPay()+"\t");
			System.out.print(dto.getSudang()+"\t");
			System.out.print(dto.getTot()+"\t");
			System.out.print(dto.getTax()+"\t");
			System.out.print(dto.getAfterPay()+"\n");
			
			System.out.print("������ �����ȣ ?");
			dto.setSabeon(sc.next());
			
			System.out.print("�޿����[yyyymm]?");
			dto.setPayDate(sc.next());
			
			System.out.print("�޿���������[yyyymmdd]?");
			dto.setPaymentDate(sc.next());
			
			System.out.print("�⺻��?");
			dto.setPay(sc.nextInt());
			
			System.out.print("����?");
			dto.setSudang(sc.nextInt());
			
			System.out.print("�޸�?");
			dto.setMemo(sc.next());
			
			int tot=dto.getPay()+dto.getSudang();
			int tax=0;
			
			if(tot>=3000000)
				tax=(int)(tot*0.03);
			else if(tot>=2000000)
				tax=(int)(tot*0.02);
			else
				tax=0;
			
			dto.setTax(tax);
			
			int result=dao.updateSalary(dto);
			
			if(result>=1) {
				System.out.println("�޿� ���� �Ϸ� !!!");
			} else {
				System.out.println("�޿� ���� ���� !!!");
			}
			
		}catch (InputMismatchException e) {
			System.out.println("�Է� ������ ��ġ���� �ʽ��ϴ�. !!!");
		}catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void delete() {
		System.out.println("\n�޿� ����...");
		
		int salaryNum;
		System.out.print("������ �޿� ��ȣ ?");
		salaryNum=sc.nextInt();
		
		SalaryDTO dto=dao.readSalary(salaryNum);
		if(dto==null) {
			System.out.println("��ϵ� ������ �ƴմϴ�. !!!");
			return;
		}
		
		dao.deleteSalary(salaryNum);
	}

	public void searchSabeon() {
		System.out.println("\n��� �˻�...");
		
		String payDate, sabeon;
		System.out.print("�˻��� �޿����[yyyymm] ?");
		payDate=sc.next();
		System.out.print("�˻��� ��� ?");
		sabeon=sc.next();
		Map<String, Object> map=new HashMap<>();
		map.put("payDate", payDate);
		map.put("sabeon", sabeon);
		
		List<SalaryDTO> list=dao.listSalary(map);
		
		for(SalaryDTO dto : list) {
			System.out.print(dto.getSalaryNum()+"\t");
			System.out.print(dto.getSabeon()+"\t");
			System.out.print(dto.getName()+"\t");
			System.out.print(dto.getPayDate()+"\t");
			System.out.print(dto.getPaymentDate()+"\t");
			System.out.print(dto.getPay()+"\t");
			System.out.print(dto.getSudang()+"\t");
			System.out.print(dto.getTot()+"\t");
			System.out.print(dto.getTax()+"\t");
			System.out.print(dto.getAfterPay()+"\n");
		}
		
	}

	public void monthList() {
		System.out.println("\n���� ����Ʈ...");
		
		String payDate;
		System.out.print("�˻��� �޿����[yyyymm] ?");
		payDate=sc.next();
		List<SalaryDTO> list=dao.listSalary(payDate);
		
		for(SalaryDTO dto : list) {
			System.out.print(dto.getSalaryNum()+"\t");
			System.out.print(dto.getSabeon()+"\t");
			System.out.print(dto.getName()+"\t");
			System.out.print(dto.getPayDate()+"\t");
			System.out.print(dto.getPaymentDate()+"\t");
			System.out.print(dto.getPay()+"\t");
			System.out.print(dto.getSudang()+"\t");
			System.out.print(dto.getTot()+"\t");
			System.out.print(dto.getTax()+"\t");
			System.out.print(dto.getAfterPay()+"\n");
		}
	}
	
	public void list() {
		System.out.println("\n�޿� ����Ʈ...");
	
		List<SalaryDTO> list=dao.listSalary();
		
		for(SalaryDTO dto : list) {
			System.out.print(dto.getSalaryNum()+"\t");
			System.out.print(dto.getSabeon()+"\t");
			System.out.print(dto.getName()+"\t");
			System.out.print(dto.getPayDate()+"\t");
			System.out.print(dto.getPaymentDate()+"\t");
			System.out.print(dto.getPay()+"\t");
			System.out.print(dto.getSudang()+"\t");
			System.out.print(dto.getTot()+"\t");
			System.out.print(dto.getTax()+"\t");
			System.out.print(dto.getAfterPay()+"\n");
		}		
	}
}

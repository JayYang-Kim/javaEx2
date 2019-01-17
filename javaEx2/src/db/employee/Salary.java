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
			System.out.println("\n[급여관리]");
			do {
				System.out.print("1.지급 2.수정 3.삭제 4.월별리스트 5.사번검색 6.리스트 7.사원리스트 8.메인 => ");
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
		System.out.println("\n급여 지급...");
		
		try {
			SalaryDTO dto = new SalaryDTO();
			
			System.out.println("사번?");
			dto.setSabeon(sc.next());
			
			System.out.println("급여년월?");
			dto.setPayDate(sc.next());
			
			System.out.println("급여지급일자?");
			dto.setPaymentDate(sc.next());
			
			System.out.println("기본급?");
			dto.setPay(sc.nextInt());
			
			System.out.println("수당?");
			dto.setSudang(sc.nextInt());
			
			// 세금 계산
			if (dto.getPay() + dto.getSudang() >= 3000000) {
				dto.setTax((int)((dto.getPay() + dto.getSudang()) * 0.03));
			} else if (dto.getPay() + dto.getSudang() >= 2000000) {
				dto.setTax((int)((dto.getPay() + dto.getSudang()) * 0.02));
			} else {
				dto.setTax(0);
			}
			
			System.out.println("메모?");
			dto.setMemo(sc.next());
			
			int result = dao.insertSalary(dto);
			
			if (result == 1) {
				System.out.println(dto.getSabeon() + "(사번)의 급여 지급정보가  생성되었습니다.");
			} else {
				System.out.println("급여 지급정보 등록이 실패했습니다.");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void update() {
		System.out.println("\n급여 수정...");
		
		try {
			SalaryDTO dto = new SalaryDTO();
			
			System.out.println("수정할 사번?");
			dto.setSabeon(sc.next());
			
			System.out.println("급여년월?");
			dto.setPayDate(sc.next());
			
			System.out.println("급여지급일자?");
			dto.setPaymentDate(sc.next());
			
			System.out.println("기본급?");
			dto.setPay(sc.nextInt());
			
			System.out.println("수당?");
			dto.setSudang(sc.nextInt());
			
			// 세금 계산
			if (dto.getPay() + dto.getSudang() >= 3000000) {
				dto.setTax((int)((dto.getPay() + dto.getSudang()) * 0.03));
			} else if (dto.getPay() + dto.getSudang() >= 2000000) {
				dto.setTax((int)((dto.getPay() + dto.getSudang()) * 0.02));
			} else {
				dto.setTax(0);
			}
			
			System.out.println("메모?");
			dto.setMemo(sc.next());
			
			int result = dao.updateSalary(dto);
			
			if (result == 1) {
				System.out.println(dto.getSabeon() + "(사번)의 급여 지급정보가  수정되었습니다.");
			} else {
				System.out.println("급여 지급정보 수정이 실패했습니다.");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void delete() {
		System.out.println("\n급여 삭제...");
		
		try {
			System.out.println("삭제할 번호?");
			int salaryNum = sc.nextInt();
			
			int result = dao.deleteSalary(salaryNum);
			
			if (result == 1) {
				System.out.println("급여 정보가 삭제되었습니다.");
			} else {
				System.out.println("급여 정보 삭제 실패했습니다.");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	public void searchSabeon() {
		System.out.println("\n사번 검색...");
		
		
	}

	public void monthList() {
		System.out.println("\n월별 리스트...");
		
	}
	
	public void list() {
		System.out.println("\n급여 리스트...");
	
	}
}

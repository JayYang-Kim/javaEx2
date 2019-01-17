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
			System.out.println("\n[사원관리]");
			do {
				System.out.print("1.사원등록 2.정보수정 3.사번검색 4.이름검색 5.리스트 6.메인 => ");
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
		System.out.println("\n사원 등록...");
		
		try {
			EmployeeDTO dto = new EmployeeDTO();
			
			System.out.println("사번?");
			dto.setSabeon(sc.next());
			
			System.out.println("이름?");
			dto.setName(sc.next());
			
			System.out.println("생일?");
			dto.setBirth(sc.next());
			
			System.out.println("전화번호?");
			dto.setTel(sc.next());
			
			int result = dao.insertEmployee(dto);
			
			if (result == 1) {
				System.out.println(dto.getSabeon() + "(사번)이  생성되었습니다.");
			} else {
				System.out.println("사번 등록에 실패했습니다.");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void update() {
		System.out.println("\n사원 정보 수정...");
		
		try {
			EmployeeDTO dto = new EmployeeDTO();
			
			System.out.println("수정할 사번?");
			dto.setSabeon(sc.next());
			
			System.out.println("이름?");
			dto.setName(sc.next());
			
			System.out.println("생일?");
			dto.setBirth(sc.next());
			
			System.out.println("전화번호?");
			dto.setTel(sc.next());
			
			int result = dao.updateEmployee(dto);
			
			if (result == 1) {
				System.out.println(dto.getSabeon() + "(사번)이  수정되었습니다.");
			} else {
				System.out.println("사번정보 수정에 실패했습니다.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void searchSabeon() {
		System.out.println("\n사번 검색...");
		
		try {
			System.out.print("사번?");
			String sabeon = sc.next();
			
			EmployeeDTO dto = dao.readEmployee(sabeon);
			
			if (dto == null) {
				System.out.println("등록되지 않은 사번입니다.");
				return;
			}
			
			System.out.println("사번 : " + dto.getSabeon());
			System.out.println("이름 : " + dto.getName());
			System.out.println("생일 : " + dto.getBirth());
			System.out.println("전화번호 : " + dto.getTel());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void searchName() {
		System.out.println("\n이름 검색...");
		
		try {
			System.out.println("이름?");
			String name = sc.next();
			
			List<EmployeeDTO> list = dao.listEmployee(name);
			
			for (EmployeeDTO dto : list) {
				System.out.println("사번 : " + dto.getSabeon());
				System.out.println("이름 : " + dto.getName());
				System.out.println("생일 : " + dto.getBirth());
				System.out.println("전화번호 : " + dto.getTel());
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void list() {
		System.out.println("\n사원 리스트...");
		
		try {			
			List<EmployeeDTO> list = dao.listEmployee();
			
			for (EmployeeDTO dto : list) {
				System.out.println("사번 : " + dto.getSabeon());
				System.out.println("이름 : " + dto.getName());
				System.out.println("생일 : " + dto.getBirth());
				System.out.println("전화번호 : " + dto.getTel());
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}

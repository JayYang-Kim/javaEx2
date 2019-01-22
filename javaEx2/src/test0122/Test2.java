package test0122;

public class Test2 {

	public static void main(String[] args) {
		char ch;
		
		while (true) {
			try {
				do {
					System.out.println("1.입력 2.출력 3.종료 =>");
					ch = (char)System.in.read();
					
					System.in.skip(2); // 언터 버림 처리
				} while(ch < '1' || ch > '3');
				
				switch(ch) {
				case '1' : System.out.println("입력"); break;
				case '2' : System.out.println("출력"); break;
				case '3' : System.exit(0);
				}
				
			} catch (Exception e) {
				
			}
		}
			
	}

}

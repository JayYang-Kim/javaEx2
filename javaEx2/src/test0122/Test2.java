package test0122;

public class Test2 {

	public static void main(String[] args) {
		char ch;
		
		while (true) {
			try {
				do {
					System.out.println("1.�Է� 2.��� 3.���� =>");
					ch = (char)System.in.read();
					
					System.in.skip(2); // ���� ���� ó��
				} while(ch < '1' || ch > '3');
				
				switch(ch) {
				case '1' : System.out.println("�Է�"); break;
				case '2' : System.out.println("���"); break;
				case '3' : System.exit(0);
				}
				
			} catch (Exception e) {
				
			}
		}
			
	}

}

package test0122;

public class Test1 {

	public static void main(String[] args) {
		int data;
		
		try {
			// ABCDE ����
			System.out.println("���ڿ� �Է�");
			
			data = System.in.read();
			System.out.println(data); // A
			
			System.in.skip(3);
			
			data = System.in.read();
			System.out.println(data); // 3���� ���ڸ� �ǳʶڴ�.
			
			System.out.flush();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}

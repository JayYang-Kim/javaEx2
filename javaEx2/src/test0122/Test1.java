package test0122;

public class Test1 {

	public static void main(String[] args) {
		int data;
		
		try {
			// ABCDE 엔터
			System.out.println("문자열 입력");
			
			data = System.in.read();
			System.out.println(data); // A
			
			System.in.skip(3);
			
			data = System.in.read();
			System.out.println(data); // 3개의 문자를 건너뛴다.
			
			System.out.flush();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}

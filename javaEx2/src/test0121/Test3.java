package test0121;

public class Test3 {

	public static void main(String[] args) {
		// system.out : PrintStream 객체. IOException이 발생되지 않음
		
		System.out.write(65); // 하위 1byte를 출력 버퍼로 보냄
		System.out.write(66);
		System.out.write(67);
		
		// 대
		System.out.write(180);
		System.out.write(235);
		
		// 한
		System.out.write(199);
		System.out.write(209);
		
		System.out.flush(); // 출력 버퍼의 내용을 출력 스트림으로 보낸다.
		//System.out.close(); // 문자 출력 스트림을 닫는다.
		
		//System.out.println("추가...");
	}

}

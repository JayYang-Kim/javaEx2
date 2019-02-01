package test0129;

class Demo3 {
	public void write() {
		System.out.println("예제...");
	}
}

public class ReflectEx3 {
	public static void main(String[] args) {

		// Demo3 ob = new Demo3(); // 정적으로 생성

		String s = "test0129.Demo3";// 키보드에 입력받은 것 
		// 패키지명을 입력하지 않으면 java.lang.ClassNotFoundException: Demo3의 오류발생

		try {
			Class<?> cls = Class.forName(s);

			Object o = cls.newInstance(); // newInstance() : 객체 생성

			Demo3 d = (Demo3) o; // 다운캐스팅 

			d.write();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}

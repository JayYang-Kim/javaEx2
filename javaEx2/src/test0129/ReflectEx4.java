package test0129;

import java.lang.reflect.Method;

// reflect
class User {

	public Integer hap(Integer a, Integer b) {
		return a + b;
	}

	public int sub(int a, int b) {
		return a - b;

	}

	public void write(String title, int s) {
		System.out.println(title + " -> " + s);
	}

	public void print() {
		System.out.println("ㅎㅇㅎㅇ...");
	}
}

public class ReflectEx4 {
	public static void main(String[] args) {
		String ClassName = "test0129.User"; // 다운캐스팅 하지않고 위의 메소드 부를 수 있다

		try {
			Class<?> cls = Class.forName(ClassName);
			Object o = cls.newInstance();

			// 메소드 정의 getDeclaredMethod
			// Method m1 = cls.getDeclaredMethod("hap", new Class[] {Integer.class,
			// Integer.class});
			Method m1 = cls.getDeclaredMethod("hap", Integer.class, Integer.class);
			Method m2 = cls.getDeclaredMethod("write", String.class, int.class); // 기본자료형도 클래스로 표현할 수 있다.
			Method m3 = cls.getDeclaredMethod("print");
			Method m4 = cls.getDeclaredMethod("sub", int.class, int.class);

			// 메소드 호출 invoke
			Object rtn1 = m1.invoke(o, new Object[] { 10, 30 });
			m2.invoke(o, "합", rtn1);
			
			m3.invoke(o);

			Integer rtn2 = (Integer) m4.invoke(o, new Object[] { 20, 5 });
			m2.invoke(o, "차", rtn2);

		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}
}

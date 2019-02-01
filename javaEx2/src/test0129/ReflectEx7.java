package test0129;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectEx7 {
	public static void main(String[] args) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String className;

		try {
			System.out.print("패키지명을 포함한 클래스명 ? ");
			className = br.readLine(); // ex) java.lang.String
			Class<?> cls = Class.forName(className);

			System.out.println("\nModifiers...");
			System.out.println(cls.getModifiers()); // String => 17로 출력(public final)
			System.out.println(Modifier.toString(cls.getModifiers())); // public final

			System.out.println("\n슈퍼 클래스...");
			if (cls.getSuperclass() != null) {// superclass전부를 알 수 없다. object는 부모 없어서 알아낼 수 없다.
				System.out.println(cls.getSuperclass().getName());
			}

			System.out.println("\n구현한 인터페이스...");
			Class<?>[] it = cls.getInterfaces(); // 배열처리. 인터페이스는 하나이상을 구현할 수 있음
			for (Class<?> c : it)
				System.out.println(c.getName());

			System.out.println("\n생성자...");
			Constructor<?>[] cs = cls.getConstructors();
			for (Constructor<?> c : cs) {
				System.out.println(c);
			}

			System.out.println("\n필드(public)...");
			// Field[] ff = cls.getDeclaredFields(); //private 포함 모든 필드
			Field[] ff = cls.getFields();
			for (Field f : ff) {
				System.out.println(f);
			}

			System.out.println("\n메소드...");
			Method[] mm = cls.getMethods();
			for (Method m : mm) {
				System.out.println(m);
				// System.out.println(m.getModifiers() + " " + m.getName());
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

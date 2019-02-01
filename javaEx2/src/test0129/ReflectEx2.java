package test0129;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectEx2 {
	public static void main(String[] args) {

		try {
			// class<?> cls= String.class;
			Class<?> cls = Class.forName("java.lang.String");

			// 상위클래스가 알고 싶다
			if (cls.getSuperclass() != null)
				System.out.println("상위클래스 : " + cls.getSuperclass().getName()); // object

			// 생성자
			System.out.println("\n생성자...");
			Constructor<?>[] cc = cls.getConstructors();
			for (Constructor<?> c : cc)
				System.out.println("생성자 : " + c);

			// 필드(멤버변수)
			System.out.println("\n필드..");
			Field[] ff = cls.getDeclaredFields();
			for (Field f : ff) {
				System.out.println("필드 : " + f);
			}

			System.out.println("\n메소드..");
			Method[] mm = cls.getMethods();
			for (Method m : mm) {
				System.out.println("메소드 : " + m);
			}
			
			// 인터페이스인가 클래스인가
			if(cls.isInterface()) 
				System.out.println("인터페이스..");
			else
				System.out.println("클래스..");
			
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		}

	}
}

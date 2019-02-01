package test0129;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectEx2 {
	public static void main(String[] args) {

		try {
			// class<?> cls= String.class;
			Class<?> cls = Class.forName("java.lang.String");

			// ����Ŭ������ �˰� �ʹ�
			if (cls.getSuperclass() != null)
				System.out.println("����Ŭ���� : " + cls.getSuperclass().getName()); // object

			// ������
			System.out.println("\n������...");
			Constructor<?>[] cc = cls.getConstructors();
			for (Constructor<?> c : cc)
				System.out.println("������ : " + c);

			// �ʵ�(�������)
			System.out.println("\n�ʵ�..");
			Field[] ff = cls.getDeclaredFields();
			for (Field f : ff) {
				System.out.println("�ʵ� : " + f);
			}

			System.out.println("\n�޼ҵ�..");
			Method[] mm = cls.getMethods();
			for (Method m : mm) {
				System.out.println("�޼ҵ� : " + m);
			}
			
			// �������̽��ΰ� Ŭ�����ΰ�
			if(cls.isInterface()) 
				System.out.println("�������̽�..");
			else
				System.out.println("Ŭ����..");
			
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		}

	}
}

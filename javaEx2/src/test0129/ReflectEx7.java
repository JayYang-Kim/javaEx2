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
			System.out.print("��Ű������ ������ Ŭ������ ? ");
			className = br.readLine(); // ex) java.lang.String
			Class<?> cls = Class.forName(className);

			System.out.println("\nModifiers...");
			System.out.println(cls.getModifiers()); // String => 17�� ���(public final)
			System.out.println(Modifier.toString(cls.getModifiers())); // public final

			System.out.println("\n���� Ŭ����...");
			if (cls.getSuperclass() != null) {// superclass���θ� �� �� ����. object�� �θ� ��� �˾Ƴ� �� ����.
				System.out.println(cls.getSuperclass().getName());
			}

			System.out.println("\n������ �������̽�...");
			Class<?>[] it = cls.getInterfaces(); // �迭ó��. �������̽��� �ϳ��̻��� ������ �� ����
			for (Class<?> c : it)
				System.out.println(c.getName());

			System.out.println("\n������...");
			Constructor<?>[] cs = cls.getConstructors();
			for (Constructor<?> c : cs) {
				System.out.println(c);
			}

			System.out.println("\n�ʵ�(public)...");
			// Field[] ff = cls.getDeclaredFields(); //private ���� ��� �ʵ�
			Field[] ff = cls.getFields();
			for (Field f : ff) {
				System.out.println(f);
			}

			System.out.println("\n�޼ҵ�...");
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

package test0129;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

class UserVO {

	private String name;
	private String tel;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}

public class ReflectEx5 {
	public static void main(String[] args) {
		String className = "test0129.UserVO";

		// ���÷���

		try {
			Class<?> cls = Class.forName(className);// �ش� Ŭ���� ���� �о / ĳ������ �� ��� <?>
			Object ob = cls.newInstance();

			Field[] ff = cls.getDeclaredFields(); // ��� �ʵ� ��� (private�� ����)
			// Field[] ff = cls.getFields(); // public �ʵ���
			// Declared: ��� �� / ������ public��

			// setter �޼ҵ� ȣ��
			for (Field f : ff) {

				String fieldName = f.getName(); // �ʵ��
				String first = fieldName.substring(0, 1).toUpperCase(); // ù���� ������ �빮��
				String last = fieldName.substring(1);
				String setter = "set" + first + last; // setName => �޼ҵ� �̸� �˸� �޼ҵ带 �θ� �� �ִ�.

				// �޼ҵ� ����
				Method m = cls.getDeclaredMethod(setter, f.getType());// �������� ��

				if (f.getName().equals("name")) {
					// �̸� String�� // if("java.lang.String".equals.(f.getType().getName()) :�ڷ������� ��
					// �ڷ������� �񱳽� �ڷ����� ���� �͵��� ���� �� �����Ƿ� ����

					// �޼ҵ�ȣ��
					m.invoke(ob, "ȫ�浿");

				} else if (f.getName().equals("tel")) {// ���� int�� if("java.lang.int".equals.(f.getType().getName())
					m.invoke(ob, "010-1111-1111"); // : �ڷ������� ��

				} else if (f.getName().equals("age")) {
					m.invoke(ob, 20);
				}
			}

			// getter �޼ҵ� ȣ��
			for (Field f : ff) {

				String fieldName = f.getName(); // �ʵ��
				String first = fieldName.substring(0, 1).toUpperCase(); // ù���� ������ �빮��
				String last = fieldName.substring(1);
				String getter = "get" + first + last; // getName => �޼ҵ� �̸� �˸� �޼ҵ带 �θ� �� �ִ�.

				// �޼ҵ� ����
				Method m = cls.getDeclaredMethod(getter); // ���� ���� => void�� ���
				Object o = m.invoke(ob);
				// System.out.println(o);
				if (f.getType().getName().equals("java.lang.String")) {
					String s = (String) o;
					System.out.println(f.getName() + " : " + s);
				} else if (f.getType().getName().equals("int")) {
					Integer i = (Integer) o;
					System.out.println(f.getName() + " : " + i);
				}
			}

		} catch (Exception e) {
			System.out.println(e.toString());

		}

	}
}

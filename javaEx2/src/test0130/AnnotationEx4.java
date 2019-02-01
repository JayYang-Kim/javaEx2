package test0130;

// ������̼� => �ҽ�/���� �ٲ� �� �ִ� 
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

@Target(ElementType.FIELD) // target.. ��� ���� ������
@Retention(RetentionPolicy.RUNTIME) // ������ ������ ����ְ� ��
@interface MyAnn { // ������̼� ����� �տ� @
	String value() default "�ڹ�";
}

class Ex4 {
	@MyAnn("������")
	private String name;
	@MyAnn
	private String subject;
	private String tel;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
}

class MyContainer {// ����
	
	private <T> T invokeAnn(T t) throws IllegalAccessException {
		Field[] ff = t.getClass().getDeclaredFields();
		for (Field f : ff) { // �ʵ尡���� (������̼������Ƿ�)
			MyAnn a = f.getAnnotation(MyAnn.class); // ������ ������̼� �����Ƿ� �� �ϼ� ����
			if (a != null && f.getType() == String.class) { // String�̸�
				f.setAccessible(true); // ���÷�Ʈ�� private �ʵ峪 �޼ҵ带 ���� �����ϵ��� (���� �ٲٱ� ����)
				f.set(t, a.value()); // �ʵ尪����
			}
		}
		return t;
	}

	@SuppressWarnings("unchecked")
	public <T> T get(Class<?> cls) throws IllegalAccessException, InstantiationException {
		T t = (T) cls.newInstance(); // object�� ���ʸ����� ĳ���� �ǹǷ� ��� ���
		t = invokeAnn(t);
		return t;
	}

}

public class AnnotationEx4 {
	public static void main(String[] args) {
		Ex4 ob = new Ex4();
		System.out.println(ob.getName() + " : " + ob.getSubject() + " : " + ob.getTel()); // null : null : null

		try {
			MyContainer mc = new MyContainer();

			Ex4 ee = mc.get(Ex4.class); // ��ü����
			System.out.println(ee.getName() + " : " + ee.getSubject() + " : " + ee.getTel()); // ������ : �ڹ� : null

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}

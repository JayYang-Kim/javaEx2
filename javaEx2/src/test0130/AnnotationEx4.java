package test0130;

// 어노테이션 => 소스/값을 바꿀 수 있다 
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

@Target(ElementType.FIELD) // target.. 어디 붙일 것인지
@Retention(RetentionPolicy.RUNTIME) // 실행할 때까지 살아있게 함
@interface MyAnn { // 어노테이션 만들기 앞에 @
	String value() default "자바";
}

class Ex4 {
	@MyAnn("스프링")
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

class MyContainer {// 주입
	
	private <T> T invokeAnn(T t) throws IllegalAccessException {
		Field[] ff = t.getClass().getDeclaredFields();
		for (Field f : ff) { // 필드가져옴 (어노테이션있으므로)
			MyAnn a = f.getAnnotation(MyAnn.class); // 전번은 어노테이션 없으므로 널 일수 있음
			if (a != null && f.getType() == String.class) { // String이면
				f.setAccessible(true); // 리플렉트된 private 필드나 메소드를 접근 가능하도록 (값을 바꾸기 위해)
				f.set(t, a.value()); // 필드값변경
			}
		}
		return t;
	}

	@SuppressWarnings("unchecked")
	public <T> T get(Class<?> cls) throws IllegalAccessException, InstantiationException {
		T t = (T) cls.newInstance(); // object가 제너릭으로 캐스팅 되므로 경고 뜬당
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

			Ex4 ee = mc.get(Ex4.class); // 객체생성
			System.out.println(ee.getName() + " : " + ee.getSubject() + " : " + ee.getTel()); // 스프링 : 자바 : null

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}

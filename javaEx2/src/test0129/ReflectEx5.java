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

		// 리플렉터

		try {
			Class<?> cls = Class.forName(className);// 해당 클래스 정보 읽어냄 / 캐스팅할 일 없어서 <?>
			Object ob = cls.newInstance();

			Field[] ff = cls.getDeclaredFields(); // 모든 필드 목록 (private도 포함)
			// Field[] ff = cls.getFields(); // public 필드목록
			// Declared: 모든 것 / 없으면 public만

			// setter 메소드 호출
			for (Field f : ff) {

				String fieldName = f.getName(); // 필드명
				String first = fieldName.substring(0, 1).toUpperCase(); // 첫글자 가져옴 대문자
				String last = fieldName.substring(1);
				String setter = "set" + first + last; // setName => 메소드 이름 알면 메소드를 부를 수 있다.

				// 메소드 생성
				Method m = cls.getDeclaredMethod(setter, f.getType());// 동적으로 됨

				if (f.getName().equals("name")) {
					// 이름 String형 // if("java.lang.String".equals.(f.getType().getName()) :자료형으로 비교
					// 자료형으로 비교시 자료형이 같은 것들이 있을 수 있으므로 위험

					// 메소드호출
					m.invoke(ob, "홍길동");

				} else if (f.getName().equals("tel")) {// 나이 int형 if("java.lang.int".equals.(f.getType().getName())
					m.invoke(ob, "010-1111-1111"); // : 자료형으로 비교

				} else if (f.getName().equals("age")) {
					m.invoke(ob, 20);
				}
			}

			// getter 메소드 호출
			for (Field f : ff) {

				String fieldName = f.getName(); // 필드명
				String first = fieldName.substring(0, 1).toUpperCase(); // 첫글자 가져옴 대문자
				String last = fieldName.substring(1);
				String getter = "get" + first + last; // getName => 메소드 이름 알면 메소드를 부를 수 있다.

				// 메소드 생성
				Method m = cls.getDeclaredMethod(getter); // 인자 없음 => void인 경우
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

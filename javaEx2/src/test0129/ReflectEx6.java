package test0129;

interface Ex { // 인터페이스로 표준화가 쉬움
	public int add(int a, int b);

	public void print(String title, String result);

	public void write();
}

class ExImpl implements Ex {

	@Override
	public int add(int a, int b) {
		return a + b;
	}

	@Override
	public void print(String title, String result) {
		System.out.println(title + " -> " + result);
	}

	@Override
	public void write() {
		System.out.println("write...");
	}

}

public class ReflectEx6 {
	public static void main(String[] args) {

		String className = "test0129.ExImpl";
		try {
			Class<?> cls = Class.forName(className);
			Ex ob = (Ex) cls.newInstance();

			int s = ob.add(10, 5);
			ob.print("합", Integer.toString(s));
		} catch (Exception e) {
		}
	}
}

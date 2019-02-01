package test0129;

class Demo3 {
	public void write() {
		System.out.println("����...");
	}
}

public class ReflectEx3 {
	public static void main(String[] args) {

		// Demo3 ob = new Demo3(); // �������� ����

		String s = "test0129.Demo3";// Ű���忡 �Է¹��� �� 
		// ��Ű������ �Է����� ������ java.lang.ClassNotFoundException: Demo3�� �����߻�

		try {
			Class<?> cls = Class.forName(s);

			Object o = cls.newInstance(); // newInstance() : ��ü ����

			Demo3 d = (Demo3) o; // �ٿ�ĳ���� 

			d.write();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}

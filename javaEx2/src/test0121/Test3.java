package test0121;

public class Test3 {

	public static void main(String[] args) {
		// system.out : PrintStream ��ü. IOException�� �߻����� ����
		
		System.out.write(65); // ���� 1byte�� ��� ���۷� ����
		System.out.write(66);
		System.out.write(67);
		
		// ��
		System.out.write(180);
		System.out.write(235);
		
		// ��
		System.out.write(199);
		System.out.write(209);
		
		System.out.flush(); // ��� ������ ������ ��� ��Ʈ������ ������.
		//System.out.close(); // ���� ��� ��Ʈ���� �ݴ´�.
		
		//System.out.println("�߰�...");
	}

}

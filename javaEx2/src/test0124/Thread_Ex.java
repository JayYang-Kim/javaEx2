package test0124;

class Ex1 extends Thread {

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.println("������ : " + i);
			
			try {
				sleep(1000);
			} catch (Exception e) {
				
			}
		}
	}
	
}

public class Thread_Ex {

	public static void main(String[] args) {
		Ex1 t = new Ex1();
		
		//t.run(); // �����尡 �ƴ�
		
		t.start(); // �����带 ����
		
		for (int i = 1; i <= 10; i++) {
			System.out.println("���� : " + i);
			
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				
			}
		}
		
		System.out.println("���� ����");
	}

}

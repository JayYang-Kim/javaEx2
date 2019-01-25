package test0125;

class Ex2 extends Thread {
	public void run() {
		while(!isInterrupted()) {
			System.out.println("������ : " + isInterrupted());
			
			try {
				Thread.sleep(50); // interrupt() ȣ�� => �޴� �ʿ��� sleep() ȣ��
				                  // -> InterruptedException ���ܹ߻�
								  // -> InterruptedException ���ܹ߻� �Ǹ� isInterrupted()�� false
								  // -> ��κ� ���ѷ����� �ǹ���
			} catch (InterruptedException e) {
				System.out.println("InterruptedException �߻�");
			}
		}
		
		System.out.println("������ ����");
	}
}

public class Test2 {

	public static void main(String[] args) {
		Thread t = new Ex2();
		t.start();
		
		for (int i = 1; i <= 5; i++) {
			System.out.println("���� : " + i);
			
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

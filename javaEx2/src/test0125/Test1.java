package test0125;

import java.util.concurrent.TimeUnit;

class Ex1 extends Thread {
	private int n = 0;

	public void run() {
		while (true) {
			System.out.println("������ : " + ++n);
			if (isInterrupted()) { // isInterrupted() : Interrupted �޼ҵ尡 ȣ��Ǿ����� üũ  
				System.out.println("Interrupted() �޼ҵ尡 ȣ���");
				break;
			}
			
			try {
				TimeUnit.MICROSECONDS.sleep(1);
			} catch (InterruptedException e) {
				System.out.println("InterruptedException ����");
				break;
			}
		}
		
		System.out.println("������ ����");
	}
}

public class Test1 {

	public static void main(String[] args) {
		Ex1 ee = new Ex1();
		ee.start();
		
		while (true) {
			try {
				if (Math.random() > 0.5) {
					ee.interrupt();
					break;
				}
			} catch (Exception e) {
				
			}
		}
		
		System.out.println("���� ����");
	}

}

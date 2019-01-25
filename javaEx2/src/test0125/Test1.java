package test0125;

import java.util.concurrent.TimeUnit;

class Ex1 extends Thread {
	private int n = 0;

	public void run() {
		while (true) {
			System.out.println("스레드 : " + ++n);
			if (isInterrupted()) { // isInterrupted() : Interrupted 메소드가 호출되었는지 체크  
				System.out.println("Interrupted() 메소드가 호출됨");
				break;
			}
			
			try {
				TimeUnit.MICROSECONDS.sleep(1);
			} catch (InterruptedException e) {
				System.out.println("InterruptedException 예외");
				break;
			}
		}
		
		System.out.println("스레드 종료");
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
		
		System.out.println("메인 종료");
	}

}

package test0125;

class Ex2 extends Thread {
	public void run() {
		while(!isInterrupted()) {
			System.out.println("스레드 : " + isInterrupted());
			
			try {
				Thread.sleep(50); // interrupt() 호출 => 받는 쪽에서 sleep() 호출
				                  // -> InterruptedException 예외발생
								  // -> InterruptedException 예외발생 되면 isInterrupted()는 false
								  // -> 대부분 무한루프가 되버림
			} catch (InterruptedException e) {
				System.out.println("InterruptedException 발생");
			}
		}
		
		System.out.println("스레드 종료");
	}
}

public class Test2 {

	public static void main(String[] args) {
		Thread t = new Ex2();
		t.start();
		
		for (int i = 1; i <= 5; i++) {
			System.out.println("메인 : " + i);
			
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

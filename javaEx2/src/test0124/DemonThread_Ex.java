package test0124;

class Ex7 implements Runnable {

	@Override
	public void run() {
		for (int i = 1; i <= 20; i++) {
			System.out.println(Thread.currentThread().getName() + ":" + i);
			
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				
			}
		}
		
	}
	
}

public class DemonThread_Ex {

	public static void main(String[] args) {
		Thread t1 = new Thread(new Ex7());
		Thread t2 = new Thread(new Ex7());
		Thread t3 = new Thread(new Ex7());
		
		t1.setDaemon(true);
		t2.setDaemon(true);
		t3.setDaemon(true);
		
		t1.start();
		t2.start();
		t3.start();
		
		try {
			// 스레드가 종료될때까지 대기
			t1.join(); 
			t2.join(); 
			t3.join(); 
		} catch (Exception e) {
			
		}
		
		System.out.println("메인 종료");
	}

}

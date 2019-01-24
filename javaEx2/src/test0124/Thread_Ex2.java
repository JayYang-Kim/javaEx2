package test0124;

class Ex2 implements Runnable {

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.println("스레드 : " + i);
			
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				
			}
		}
	}
	
}

public class Thread_Ex2 {

	public static void main(String[] args) {
		/*Ex2 ee = new Ex2();
		Thread t = new Thread(ee);*/
		
		Thread t = new Thread(new Ex2());
		
		t.start();
		
		for (int i = 1; i <= 10; i++) {
			System.out.println("메인" + i);
			
			try {
				Thread.sleep(500);
			} catch (Exception e) {

			}
		}
		
		System.out.println("메인 종료");
	}

}

package test0124;

class Ex1 extends Thread {

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.println("스레드 : " + i);
			
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
		
		//t.run(); // 스레드가 아님
		
		t.start(); // 스레드를 시작
		
		for (int i = 1; i <= 10; i++) {
			System.out.println("메인 : " + i);
			
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				
			}
		}
		
		System.out.println("메인 종료");
	}

}

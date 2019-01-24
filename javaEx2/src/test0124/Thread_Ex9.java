package test0124;

class Ex9 extends Thread {

	@Override
	public void run() {
		try {
			while (true) {
				System.out.println("스레드 : " + getName());
				
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		}
		
		System.out.println("스레드 종료");
	}
	
}

public class Thread_Ex9 {

	public static void main(String[] args) {
		Ex9 t = new Ex9();
		
		t.start();
		
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			
		}
		
		//t.stop(); // 사용 하면안됨, "스레드 종료" 출력 안된다.
		//t.interrupt(); // 인터럽트를 받은 스레드가 sleep(), join(), wait() 등에 의해 대기 상태인 경우 InterruptedException 예외가 발생
		
		System.out.println("메인 종료");
	}

}

package test0124;

class Ex8 extends Thread {

	@Override
	public void run() {
		try {
			for (int i = 1; i <= 10; i++) {
				System.out.println(getName() + ":" + i);
				
				sleep(500);
			}
		} catch (Exception e) {
			
		}
	}
	
}

public class Thread_Ex8 {

	public static void main(String[] args) {
		Ex8 t = new Ex8();
		
		System.out.println("Alive : " + t.isAlive()); // 스레드가 살아있는지 확인
		
		t.start();
		
		System.out.println("Alive : " + t.isAlive());
		
		// join은 예외가 발생하기 때문에 try ~ catch를 해줘야한다.
		try {
			t.join();
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		}
		
		System.out.println("Alive : " + t.isAlive());
		
		System.out.println("종료");
	}

}

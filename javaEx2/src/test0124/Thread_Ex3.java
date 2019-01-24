package test0124;

public class Thread_Ex3 {

	public static void main(String[] args) {
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				System.out.println("스레드");
			}
			
		};
		
		Thread t = new Thread(r);
		
		t.start();
		
		System.out.println("메인");
	}

}

package test0124;

public class Thread_Ex4 {

	public static void main(String[] args) {
		// 람다식
		Runnable r = () ->{
			for (int i = 1; i <= 10; i++) {
				System.out.println("스레드 : " + i);
				
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					
				}
			}
		};
		
		Thread t = new Thread(r);
		
		t.start();
		
		for (int i = 1; i <= 10; i++) {
			System.out.println("메인 : " + i);
			
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				
			}
		}
		
		System.out.println("메인 종료");
	}

}

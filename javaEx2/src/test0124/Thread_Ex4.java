package test0124;

public class Thread_Ex4 {

	public static void main(String[] args) {
		// ���ٽ�
		Runnable r = () ->{
			for (int i = 1; i <= 10; i++) {
				System.out.println("������ : " + i);
				
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					
				}
			}
		};
		
		Thread t = new Thread(r);
		
		t.start();
		
		for (int i = 1; i <= 10; i++) {
			System.out.println("���� : " + i);
			
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				
			}
		}
		
		System.out.println("���� ����");
	}

}

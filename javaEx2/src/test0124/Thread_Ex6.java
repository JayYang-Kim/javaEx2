package test0124;

class Ex6 extends Thread {

	public Ex6(String name) {
		super(name); // �����带 ������ �� ������ �̸��� ����
	}
	
	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.println(getName() + ":" + i);
			
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				
			}
		}
	}
	
}

public class Thread_Ex6 {

	public static void main(String[] args) {
		// ������ �켱 ����
		Thread t1 = new Ex6("A");
		Thread t2 = new Ex6("B");
		Thread t3 = new Ex6("C");
		Thread t4 = new Ex6("D");
		Thread t5 = new Ex6("E");
	
		System.out.println("�⺻ ������ ���� : " + t1.getPriority()); // [���] 5
		
		// �켱���� ����
		t5.setPriority(Thread.MAX_PRIORITY); // 10
		t3.setPriority(Thread.MIN_PRIORITY); // 1
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}

}

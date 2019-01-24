package test0124;

class Ex9 extends Thread {

	@Override
	public void run() {
		try {
			while (true) {
				System.out.println("������ : " + getName());
				
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		}
		
		System.out.println("������ ����");
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
		
		//t.stop(); // ��� �ϸ�ȵ�, "������ ����" ��� �ȵȴ�.
		//t.interrupt(); // ���ͷ�Ʈ�� ���� �����尡 sleep(), join(), wait() � ���� ��� ������ ��� InterruptedException ���ܰ� �߻�
		
		System.out.println("���� ����");
	}

}

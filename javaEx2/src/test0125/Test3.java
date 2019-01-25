package test0125;

class Ex3 extends Thread {
	private int n = 0;
	
	public Ex3(String name) {
		super(name);
	}
	
	public void run() {
		for (int i = 1; i <= 20; i++) {
			n += i;
			System.out.println(getName() + ":" + n);
			
			yield(); // 디버깅 또는 테스트 목적으로 사용
		}
	}
}

public class Test3 {

	public static void main(String[] args) {
		Ex3 t1 = new Ex3("A");
		Ex3 t2 = new Ex3("B");
		Ex3 t3 = new Ex3("C");
		
		t1.start();
		t2.start();
		t3.start();
	}

}

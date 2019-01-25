package test0125;

// wait(), notify()
class Bank implements Runnable {
	private int bank_money = 1000;
	
	@Override
	public void run() {
		synchronized (this) { // 현객체에 대하여 동기화
			for (int i = 0; i < 10; i++) {
				if (getBankMoney() <= 0) {
					this.notifyAll(); // 모든 wait()을 깨움
					break;
				}
				
				drawMoney(100);
				
				if (getBankMoney() % 200 == 0) { // 잔고가 800, 600, 400, 200 있을때
					try {
						this.wait(); // 스레드 제어를 넘김 처리
					} catch (InterruptedException e) {

					}
					
				} else {
					this.notify();
				}
			}
		}
	}
	
	public int getBankMoney() {
		return bank_money;
	}
	
	public void drawMoney(int n) {
		System.out.print(Thread.currentThread().getName() + ", ");
		
		if (getBankMoney() >= n) {
			bank_money -= n;
			System.out.println("잔액 : " + getBankMoney());
		} else {
			System.out.println("잔액 부족");
		}
	}
}
public class Test4 {

	public static void main(String[] args) {
		Bank b = new Bank();
		
		Thread t1 = new Thread(b, "t1");
		Thread t2 = new Thread(b, "t2");
		
		t1.start();
		t2.start();
	}

}

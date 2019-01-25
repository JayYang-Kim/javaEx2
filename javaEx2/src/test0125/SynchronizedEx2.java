package test0125;

class Bank2 implements Runnable {
	private int money_bank = 1000;
	
	@Override
	public void run() {
		int money = 600;
		int n = 0;
		String msg = null;
		
		try {
			// 동기화 처리 (동기화 블럭)
			synchronized (this) {
				if (getMoneyBank() >= money) {
					Thread.sleep(200);

					n = drawMoney(money);
					msg = "인출 성공";
				} else {
					n = 0;
					msg = "인출 실패";
				}
			}
			
			System.out.println(msg  + ", 인출금액 : " + n + ", 잔고 : " + getMoneyBank());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getMoneyBank() {
		return money_bank;
	}
	
	public int drawMoney(int m) {
		money_bank -= m;
		return m;
	}
}

// 동기화
public class SynchronizedEx2 {

	public static void main(String[] args) {
		Bank2 b = new Bank2();
		
		Thread t1 = new Thread(b);
		Thread t2 = new Thread(b);
		
		t1.start();
		t2.start();
	}

}

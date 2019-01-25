package test0125;

// 동기화를 안한 경우
class ShareData1 {
	private int val = 100;
	
	public void up(String title) {
		System.out.print(title + ":" + val);
		val++;
		System.out.println(", 증가 : " + val);
	}
	
	public void down(String title) {
		System.out.print(title + ":" + val);
		val--;
		System.out.println(", 감소 : " + val);
	}
}

class UpThrea1 extends Thread {
	private ShareData1 share;
	private String title;
	
	public UpThrea1(ShareData1 share, String title) {
		this.share = share;
		this.title = title;
	}
	
	public void run() {
		for (int i = 0; i < 5; i++) {
			try {
				sleep(500);
				share.up(title);
			} catch (Exception e) {
				
			}
		}
	}
}

class DownThrea1 extends Thread {
	private ShareData1 share;
	private String title;
	
	public DownThrea1(ShareData1 share, String title) {
		this.share = share;
		this.title = title;
	}
	
	public void run() {
		for (int i = 0; i < 5; i++) {
			try {
				sleep(500);
				share.down(title);
			} catch (Exception e) {
				
			}
		}
	}
}

public class MethodSynchronizedEx {

	public static void main(String[] args) {
		ShareData1 share = new ShareData1();
		
		UpThrea1 t1 = new UpThrea1(share, "up");
		DownThrea1 t2 = new DownThrea1(share, "down");
		
		t1.start();
		t2.start();
	}

}

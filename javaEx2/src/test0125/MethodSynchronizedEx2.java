package test0125;

// 동기화
class ShareData2 {
	private int val = 100;
	
	public synchronized void up(String title) {
		System.out.print(title + ":" + val);
		val++;
		System.out.println(", 증가 : " + val);
	}
	
	public synchronized void down(String title) {
		System.out.print(title + ":" + val);
		val--;
		System.out.println(", 감소 : " + val);
	}
}

class UpThrea2 extends Thread {
	private ShareData2 share;
	private String title;
	
	public UpThrea2(ShareData2 share, String title) {
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

class DownThrea2 extends Thread {
	private ShareData2 share;
	private String title;
	
	public DownThrea2(ShareData2 share, String title) {
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

public class MethodSynchronizedEx2 {

	public static void main(String[] args) {
		ShareData2 share = new ShareData2();
		
		UpThrea2 t1 = new UpThrea2(share, "up");
		DownThrea2 t2 = new DownThrea2(share, "down");
		
		t1.start();
		t2.start();
	}

}

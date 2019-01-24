package test0124;

import java.text.SimpleDateFormat;
import java.util.Calendar;

class Test1 extends Thread {

	@Override
	public void run() {
		String s;
		
		while (true) {
			/*try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
				System.out.println(sdf.format(Calendar.getInstance().getTime()));
				
				Thread.sleep(1000);
			} catch (Exception e) {
				
			}*/
			
			try {
				s = String.format("%1$tF %1$tT", Calendar.getInstance());
				System.out.println(s);
				
				Thread.sleep(1000);
			} catch (Exception e) {

			}
		}
	}
	
}

public class Thread_Test {

	public static void main(String[] args) {
		//Test1 t = new Test1();
		Thread t = new Thread(new Test1()); 
		
		t.start(); // 스레드를 시작
	}

}

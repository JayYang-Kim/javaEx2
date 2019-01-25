package test0125;


import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

class Ex {
	private void printDays() {
		Calendar cal = Calendar.getInstance();
		
		//String s = String.format("%$1tF %$1tT", cal);
		String s = String.format("%tF %tT", cal, cal);
		
		System.out.println(s);
	}
	
	public Ex() {
		// TimerTask : 타이머에 의해 1회 또는 반복 실행하도록 하는 테스크되는 스케줄 
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				printDays();
			}
		};
		
		Timer t = new Timer();
		t.schedule(task, 1000); // 1초 후 한번 실행
		//t.schedule(task, 2000, 1000); // 2초후 1초마다 무한반복
		//t.schedule(task, new Date(System.currentTimeMillis()), 1000); // 바로 실행하고 1초마다 무한반복
		
		// 오늘밤 0시 0분 0초 부터 하루에 한번씩 반복
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1); // 하루 증가
		c.set(Calendar.HOUR_OF_DAY, 0); // 0시로 변경
		c.set(Calendar.MINUTE, 0); // 0분으로 변경
		c.set(Calendar.SECOND, 0); // 0초으로 변경
		c.set(Calendar.MILLISECOND, 0); 
		
		t.schedule(task, c.getTime(), 1000*60*60*24);
	}
}

public class TimerEx {

	public static void main(String[] args) {
		
	}

}

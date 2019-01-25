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
		// TimerTask : Ÿ�̸ӿ� ���� 1ȸ �Ǵ� �ݺ� �����ϵ��� �ϴ� �׽�ũ�Ǵ� ������ 
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				printDays();
			}
		};
		
		Timer t = new Timer();
		t.schedule(task, 1000); // 1�� �� �ѹ� ����
		//t.schedule(task, 2000, 1000); // 2���� 1�ʸ��� ���ѹݺ�
		//t.schedule(task, new Date(System.currentTimeMillis()), 1000); // �ٷ� �����ϰ� 1�ʸ��� ���ѹݺ�
		
		// ���ù� 0�� 0�� 0�� ���� �Ϸ翡 �ѹ��� �ݺ�
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1); // �Ϸ� ����
		c.set(Calendar.HOUR_OF_DAY, 0); // 0�÷� ����
		c.set(Calendar.MINUTE, 0); // 0������ ����
		c.set(Calendar.SECOND, 0); // 0������ ����
		c.set(Calendar.MILLISECOND, 0); 
		
		t.schedule(task, c.getTime(), 1000*60*60*24);
	}
}

public class TimerEx {

	public static void main(String[] args) {
		
	}

}

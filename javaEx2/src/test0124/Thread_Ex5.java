package test0124;

public class Thread_Ex5 {

	public static void main(String[] args) {
		//System.out.println(Thread.currentThread().getName()); // main
		
		ThreadGroup tg1 = Thread.currentThread().getThreadGroup();
		ThreadGroup tg2 = new ThreadGroup("myGroup"); // myGroup이라는 스레드 그룹을 생성
		ThreadGroup tg3 = new ThreadGroup(tg1, "myGroup3");
		
		Thread t1 = new Thread();
		Thread t2 = new Thread(tg1, "t-1");
		Thread t3 = new Thread(tg2, "t-2");
		Thread t4 = new Thread(tg3, "t-3");
		
		System.out.println(Thread.currentThread()); // 스레드 이름 : main
		System.out.println(t1); // Thread-0
		System.out.println(t2); // t-1
		System.out.println(t3); // t-2
		System.out.println(t4); // t-3
		
		// 스레드의 그룹 확인
		System.out.println(t1.getThreadGroup());
		System.out.println(t2.getThreadGroup());
		System.out.println(t3.getThreadGroup());
		System.out.println(t4.getThreadGroup());
	}

}

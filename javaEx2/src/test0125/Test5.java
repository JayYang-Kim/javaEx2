package test0125;

import java.util.concurrent.atomic.AtomicInteger;

public class Test5 {
	private static AtomicInteger count = new AtomicInteger(0);
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(count.incrementAndGet());
		}
	}

}

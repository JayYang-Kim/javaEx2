package test0124;

// stiatc import class를 선언안해도 메소드 사용가능
import static java.util.concurrent.TimeUnit.MICROSECONDS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class Thread_Ex7 {

	public static void main(String[] args) {
		try {
			//long start = System.currentTimeMillis();
			long start = System.nanoTime();
			
			SECONDS.sleep(1L); // 1초
			MILLISECONDS.sleep(1000); // 1초
			MICROSECONDS.sleep(1000000L); // 1초
			NANOSECONDS.sleep(1000L); // 1000ns => 1마이크로s
			
			//long end = System.currentTimeMillis();
			long end = System.nanoTime();
			
			System.out.println(end - start);
		} catch (Exception e) {
			
		}
	}

}

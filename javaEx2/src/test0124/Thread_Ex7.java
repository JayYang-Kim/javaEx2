package test0124;

// stiatc import class�� ������ص� �޼ҵ� ��밡��
import static java.util.concurrent.TimeUnit.MICROSECONDS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class Thread_Ex7 {

	public static void main(String[] args) {
		try {
			//long start = System.currentTimeMillis();
			long start = System.nanoTime();
			
			SECONDS.sleep(1L); // 1��
			MILLISECONDS.sleep(1000); // 1��
			MICROSECONDS.sleep(1000000L); // 1��
			NANOSECONDS.sleep(1000L); // 1000ns => 1����ũ��s
			
			//long end = System.currentTimeMillis();
			long end = System.nanoTime();
			
			System.out.println(end - start);
		} catch (Exception e) {
			
		}
	}

}

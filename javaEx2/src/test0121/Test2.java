package test0121;

import java.io.IOException;

public class Test2 {

	public static void main(String[] args) {
		// [문제] 정수를 입력 받아 1 ~ 입력 받은 수 까지 합. (단, 정수의 입력은 System.in.read() 이용)
		int data, num = 0;
		
		try {
			// ex) 130(Enter) => 코드값 : 49 51 48 13 10
			// 0 => ASCII 코드값 : 48
			System.out.println("정수 입력 [종료 : ctrl + z]");
			while((data = System.in.read()) != 13) {
				data = data - 48;
				
				num = num * 10 + data;
				
				System.out.println(num);
				
				int s = 0;
				
				for (int i = 1; i <= num; i++) {
					s += i;
				}
				
				System.out.println("결과 : " + s);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}

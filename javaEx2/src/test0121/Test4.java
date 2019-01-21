package test0121;

import java.io.IOException;
import java.io.OutputStream;

public class Test4 {

	public static void main(String[] args) {
		byte[] b = {65, 66, 67, 68, 69, 70, 71, 72, 73};
		
		try {
			OutputStream os = System.out;
			
			//os.write(b); // [결과] ABCDEFGHI
			os.write(b, 0, b.length); // [결과] ABCDEFGHI
			os.write(b, 0, 3); // [결과] ABC
			os.write(b, 2, 2); // [결과] CD
			
			// 배열인 경우에는 flush를 쓰지 않아도 된다. (내부적으로 flush를 가지고 있다.)
			//os.flush();
			
			os.close();
			
			System.out.println("안녕..."); // 출력안됨. (위에서 출력 스트림을 닫았기 때문)
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}

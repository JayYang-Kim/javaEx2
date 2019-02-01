package test0131;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

// channel통해 파일속에 저장
public class Test2 {
	public static void main(String[] args) {

		String s = "자바 안드로이드 웹 스프링...";

		FileOutputStream fos = null; // FileOutputStream 파일저장
		FileInputStream fis = null;
		FileChannel channel = null;

		try {
			fos = new FileOutputStream("test.txt");
			// 중개적 역할 : 채널(바이트배열사용)
			channel = fos.getChannel(); // 채널객체 생성

			byte[] b = s.getBytes(); // 바이트배열값
			ByteBuffer buf = ByteBuffer.wrap(b); // 바이트배열 생성 // 바이트배열 복제 / allocate + put역할

			channel.write(buf);

			channel.close();
			fos.close();
			System.out.println("저장완료...\n");

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		try {
			fis = new FileInputStream("test.txt");
			FileChannel channel2 = fis.getChannel();

			ByteBuffer buf2 = ByteBuffer.allocate((int) channel2.size());// long형
			channel2.read(buf2);

			// 포지션 : 데이터 읽어들이는 위치 => 처음으로 만들어줌 / 끝은 limit지정
			buf2.flip(); // 현재의 포지션을 limit로 지정하고 포지션은 0으로 / 가장 앞으로.

			byte[] bb = new byte[buf2.limit()]; // 읽어들일 수 있는 위치(limit)만큼

			buf2.get(bb); // 버퍼에 있는 것 바이트 배열로 보냄

			System.out.println("읽은 문자열 : " + new String(bb));

			channel2.close();
			fis.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}
}

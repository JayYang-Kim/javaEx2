package test0131;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class Test1 {
	public static void main(String[] args) {
		// NIO
		// byte형 버퍼생성

		// 다이렉트버퍼 : 운영체제가 관리하는 메모리공간을 이용하는 버퍼
		ByteBuffer b1 = ByteBuffer.allocateDirect(100);

		System.out.println(b1.capacity() + "바이트");

		CharBuffer b2 = ByteBuffer.allocateDirect(100).asCharBuffer(); // asCharBuffer() 캐릭터형으로 변경
		System.out.println(b2.capacity() + "바이트");

		ByteBuffer b3 = ByteBuffer.allocate(10);
		System.out.println(b3.capacity() + "바이트");

		// 버퍼에 데이터 넣는당 put
		// b3.put(65); // 오류 : 65는 정수. 바이트가 받을 수 없기 때문 => 캐스팅 필요
		b3.put((byte) 65);
		b3.put((byte) 0x42);

		// 배열 속에 있는 것을 넣을 수도 있다
		byte[] bb = { 67, 68, 69 };
		b3.put(bb);

		print(b3);
	}

	public static void print(ByteBuffer b) {
		// 버퍼 복제
		ByteBuffer buf = b.duplicate();

		byte x;
		buf.position(0);// 원하는 위치로 이동
		// buf.limit(2); // 접근할 수있는 마지막 버퍼 2개까지 제한 //10번반복인데 읽어오는건 2개 / 에러
		// java.nio.BufferUnderflowException
		buf.limit(buf.capacity());

		System.out.println("limit:" + buf.limit());
		for (int i = 0; i < buf.capacity(); i++) {
			x = buf.get();
			System.out.println(buf.position() + ": " + x);
		}
		System.out.println();
	}
}

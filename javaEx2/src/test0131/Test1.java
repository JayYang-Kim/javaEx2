package test0131;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class Test1 {
	public static void main(String[] args) {
		// NIO
		// byte�� ���ۻ���

		// ���̷�Ʈ���� : �ü���� �����ϴ� �޸𸮰����� �̿��ϴ� ����
		ByteBuffer b1 = ByteBuffer.allocateDirect(100);

		System.out.println(b1.capacity() + "����Ʈ");

		CharBuffer b2 = ByteBuffer.allocateDirect(100).asCharBuffer(); // asCharBuffer() ĳ���������� ����
		System.out.println(b2.capacity() + "����Ʈ");

		ByteBuffer b3 = ByteBuffer.allocate(10);
		System.out.println(b3.capacity() + "����Ʈ");

		// ���ۿ� ������ �ִ´� put
		// b3.put(65); // ���� : 65�� ����. ����Ʈ�� ���� �� ���� ���� => ĳ���� �ʿ�
		b3.put((byte) 65);
		b3.put((byte) 0x42);

		// �迭 �ӿ� �ִ� ���� ���� ���� �ִ�
		byte[] bb = { 67, 68, 69 };
		b3.put(bb);

		print(b3);
	}

	public static void print(ByteBuffer b) {
		// ���� ����
		ByteBuffer buf = b.duplicate();

		byte x;
		buf.position(0);// ���ϴ� ��ġ�� �̵�
		// buf.limit(2); // ������ ���ִ� ������ ���� 2������ ���� //10���ݺ��ε� �о���°� 2�� / ����
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

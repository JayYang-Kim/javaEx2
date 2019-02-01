package test0131;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

// channel���� ���ϼӿ� ����
public class Test2 {
	public static void main(String[] args) {

		String s = "�ڹ� �ȵ���̵� �� ������...";

		FileOutputStream fos = null; // FileOutputStream ��������
		FileInputStream fis = null;
		FileChannel channel = null;

		try {
			fos = new FileOutputStream("test.txt");
			// �߰��� ���� : ä��(����Ʈ�迭���)
			channel = fos.getChannel(); // ä�ΰ�ü ����

			byte[] b = s.getBytes(); // ����Ʈ�迭��
			ByteBuffer buf = ByteBuffer.wrap(b); // ����Ʈ�迭 ���� // ����Ʈ�迭 ���� / allocate + put����

			channel.write(buf);

			channel.close();
			fos.close();
			System.out.println("����Ϸ�...\n");

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		try {
			fis = new FileInputStream("test.txt");
			FileChannel channel2 = fis.getChannel();

			ByteBuffer buf2 = ByteBuffer.allocate((int) channel2.size());// long��
			channel2.read(buf2);

			// ������ : ������ �о���̴� ��ġ => ó������ ������� / ���� limit����
			buf2.flip(); // ������ �������� limit�� �����ϰ� �������� 0���� / ���� ������.

			byte[] bb = new byte[buf2.limit()]; // �о���� �� �ִ� ��ġ(limit)��ŭ

			buf2.get(bb); // ���ۿ� �ִ� �� ����Ʈ �迭�� ����

			System.out.println("���� ���ڿ� : " + new String(bb));

			channel2.close();
			fis.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}
}

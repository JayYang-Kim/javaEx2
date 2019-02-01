package udp1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

// 1:1
public class UDPServer implements Runnable {
	private int SIZE = 80;
	private InetAddress ia = null;
	private int port1 = 7788, port2 = 7799; // �޴� �� ������ �� => ������ �ݴ��

	private DatagramSocket ds1, ds2; // �޴� �� ������ �� (�����Ͱ� ���� �� �� �𸣹Ƿ�)
	private DatagramPacket dp1, dp2;

	private int guess;

	public UDPServer() {
		Thread th = new Thread(this);
		th.start();
		System.out.println("UDP ���� ���� !!!");
	}

	public void run() { // run �޴� ��
		try {
			guess = getNumber();

			String str;
			int num;
			ds1 = new DatagramSocket(port1);

			byte[] buffer;
			while (true) {
				buffer = new byte[SIZE];
				dp1 = new DatagramPacket(buffer, SIZE);
				ds1.receive(dp1);
				ia = dp1.getAddress();
				str = new String(dp1.getData());
				str = str.trim();

				num = Integer.parseInt(str);

				str = check(num, guess);
				sendData(str);

				// ������ �ٽ� ������ ���
				if (str.equals("Congratulations")) {
					guess = getNumber();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendData(String msg) {
		try {
			if (ia == null)
				return;

			byte[] buffer = msg.getBytes();
			ds2 = new DatagramSocket(); // ������ ��
			dp2 = new DatagramPacket(buffer, buffer.length, ia, port2);
			ds2.send(dp2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ���ڸ��� ������ �߻���
	public int getNumber() {
		int comNum;
		Random rd = new Random();
		int m1, m2, m3;
		m1 = rd.nextInt(9) + 1;
		do {
			m2 = rd.nextInt(9) + 1;
		} while (m1 == m2);
		do {
			m3 = rd.nextInt(9) + 1;
		} while (m1 == m3 || m2 == m3);
		comNum = (m1 * 100 + m2 * 10 + m3);
		return comNum;
	}

	public String check(int num, int guess) {
		String s;

		int strikes = 0, balls = 0;
		int[] n = new int[3], m = new int[3];
		for (int i = 0; i < 3; i++) {
			n[i] = num % 10;
			m[i] = guess % 10;

			num = (int) num / 10;
			guess = (int) guess / 10;
		}
		for (int i = 0; i < 3; i++) {
			if (n[i] == m[i])
				strikes++;
			for (int j = 0; j < 3; j++)
				if (n[i] == m[j] && i != j)
					balls++;
		}
		if (strikes == 3) {
			s = "Congratulations";
		} else {
			s = strikes + " strikes, " + balls + " balls";
		}
		return s;
	}

	public static void main(String args[]) {
		new UDPServer();
	}
}

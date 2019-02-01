package test0128;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

// 1:��
public class ChatServer2 {
	// ���� ���� ��������, ������ => ����
	private Vector<Socket> clients = new Vector<>();
	private ServerSocket ss = null;
	private int port = 8000;

	public ChatServer2() {

	}

	// ����Ŭ����
	// ������ Ŭ���� :Ŭ���̾�Ʈ�� �ϳ��� ������� ó��
	class WorkerThread extends Thread {

		private Socket sc = null;

		public WorkerThread(Socket sc) {
			this.sc = sc;
		}

		public void run() {
			String ip = null;
			String s;

			try {
				if (sc == null)
					return;

				// ������ Ŭ���̾�Ʈ�� �Է½�Ʈ��
				BufferedReader br = new BufferedReader(new InputStreamReader(sc.getInputStream()));

				ip = sc.getInetAddress().getHostAddress(); // ip �ּ� �˾Ƴ�

				// Ŭ���̾�Ʈ ������ ���Ϳ� ����
				clients.add(sc);

				// �ٸ� Ŭ���̾�Ʈ���� ���ӻ���� �˸�
				s = "[" + ip + "] ���� �����ϼ̽��ϴ�. �Ϸա�";
				// �� ���� ������ ��ο��� ������.
				sendAll(s, sc);

				System.out.println(s);

				// ���� Ŭ���̾�Ʈ�� ���� ������ �ٸ� Ŭ���̾�Ʈ���� ������
				while ((s = br.readLine()) != null) {
					sendAll(s, sc);
				}
			} catch (IOException e) {
				// �������� ����� �ٸ� Ŭ���̾�Ʈ���� �˸�
				s = "[" + ip + "] ���� �����ϼ̽��ϴ�. �Ϸա�";
				sendAll(s, sc);

				clients.remove(sc);
				sc = null;
				System.out.println(s);
			}

		}
	}

	public void sendAll(String msg, Socket socket) {
		// ��������� �� ������ �޼ҵ�
		// socket�� null�̸� msg�� ���� ���� / null�� �ƴϸ� �����״� ���� ����

		for (Socket sc : clients) {

			try {
				// �Ѹ��� ������ ���ܹ߻� for�� ���������ϱ� for�� �ȿ� try catch
				if (socket != null && socket == sc)
					continue; // socket �����״� ����������

				PrintWriter out = new PrintWriter(sc.getOutputStream(), true);
				out.println(msg);
			} catch (IOException e) {

			}
		}
	}

	public void sendOne(String msg, Socket socket) { // �ѻ�����Ը� ������.

		try {
			if (socket == null)
				return;
			
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.println(msg);

		} catch (IOException e) {

		}
	}

	public void serverStart() {
		try {
			ss = new ServerSocket(port); // ���� ����
			System.out.println("��������! �Ϸա�");

			while (true) {
				// Ŭ���̾�Ʈ ���� ���
				Socket sc = ss.accept(); // while ���� accept ���� 

				// ���� Ŭ���̾�Ʈ�� �ϳ��� �����带 ����� ������ ����...
				WorkerThread t = new WorkerThread(sc); // �������� ���̵� ����ϱ� ���ϵ���
				
				t.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ChatServer2().serverStart();
	}
}

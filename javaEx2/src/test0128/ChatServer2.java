package test0128;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

// 1:다
public class ChatServer2 {
	// 여러 명의 소켓저장, 스레드 => 벡터
	private Vector<Socket> clients = new Vector<>();
	private ServerSocket ss = null;
	private int port = 8000;

	public ChatServer2() {

	}

	// 내부클래스
	// 스레드 클래스 :클라이언트당 하나의 스레드로 처리
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

				// 접속한 클라이언트의 입력스트림
				BufferedReader br = new BufferedReader(new InputStreamReader(sc.getInputStream()));

				ip = sc.getInetAddress().getHostAddress(); // ip 주소 알아냄

				// 클라이언트 소켓을 벡터에 저장
				clients.add(sc);

				// 다른 클라이언트에게 접속사실을 알림
				s = "[" + ip + "] 님이 입장하셨습니다. 뾰롱★";
				// 이 것을 나빼고 모두에게 보낸다.
				sendAll(s, sc);

				System.out.println(s);

				// 접속 클라이언트가 보낸 정보를 다른 클라이언트에게 보내기
				while ((s = br.readLine()) != null) {
					sendAll(s, sc);
				}
			} catch (IOException e) {
				// 접속해제 사실을 다른 클라이언트에게 알림
				s = "[" + ip + "] 님이 퇴장하셨습니다. 뾰롱★";
				sendAll(s, sc);

				clients.remove(sc);
				sc = null;
				System.out.println(s);
			}

		}
	}

	public void sendAll(String msg, Socket socket) {
		// 모든사람에게 다 보내는 메소드
		// socket이 null이면 msg를 전부 보냄 / null이 아니면 얘한테는 가지 않음

		for (Socket sc : clients) {

			try {
				// 한명이 나가면 예외발생 for문 나가버리니까 for문 안에 try catch
				if (socket != null && socket == sc)
					continue; // socket 얘한테는 보내지않음

				PrintWriter out = new PrintWriter(sc.getOutputStream(), true);
				out.println(msg);
			} catch (IOException e) {

			}
		}
	}

	public void sendOne(String msg, Socket socket) { // 한사람에게만 보낸다.

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
			ss = new ServerSocket(port); // 서버 켜짐
			System.out.println("서버시작! 뾰롱★");

			while (true) {
				// 클라이언트 접속 대기
				Socket sc = ss.accept(); // while 통해 accept 많이 

				// 접속 클라이언트당 하나의 스레드를 만들어 스레드 시작...
				WorkerThread t = new WorkerThread(sc); // 의존관계 없이도 사용하기 편리하도록
				
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

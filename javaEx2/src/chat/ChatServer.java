package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ChatServer {
	private Vector<WorkerThread> client = new Vector<WorkerThread>();
	private ServerSocket ss;

	public ChatServer() {
	}

	public void serverStart() {
		try {
			ss = new ServerSocket(8000);
			System.out.println("서버 시작 !!!");
			while (true) {
				// 클라이언트가 접속하기를 기다린다.
				Socket sc = ss.accept();

				WorkerThread th = new WorkerThread(sc);
				th.start();
			}
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	public static void main(String[] args) {
		ChatServer cs = new ChatServer();
		cs.serverStart();
	}
	
	// 스레드 클래스
	class WorkerThread extends Thread {
		private Socket sc = null;
		private String nickName=null;
		private BufferedReader in;
		private PrintWriter out;

		public WorkerThread(Socket sc) {
			try{
				this.sc = sc;
				in = new BufferedReader(new InputStreamReader(this.sc.getInputStream()));
				out = new PrintWriter(this.sc.getOutputStream(), true);
				// System.out.println(sc.getInetAddress().getHostAddress()+"에서 클라이언트 접속...");
			}catch(Exception e){
			}
		}
		
		public PrintWriter getOut() {
			return out;
		}
		
		// 다른 클라이언트에게 전송
		public void sendMsg(String msg) {
			for (WorkerThread user : client) {
				try {
					if (user==this) // 자기 자신인 경우
						continue;
				
					user.getOut().println(msg);
				}catch(Exception e) {
				}
			}	// for_end
		}

		public void run() {
			String msg = null;
			try {
				// 클라이언트의 닉네임을 받는다.
				nickName = in.readLine();
				
				// 클라이언트 정보를 저장한다.
				client.add(this);

				// 접속 사실을 다른 클라이언트에게 알린다.
				msg="["+nickName+"]님이 입장하였습니다.";
				sendMsg(msg);

				System.out.println(msg);

				// 클라이언트의 채팅 문자열을 받아 다른 클라이언트에게 전송한다.
				boolean bDisconn=false;
				while ((msg = in.readLine()) != null) {
					if(msg.startsWith("/q")) {
						msg="["+nickName+"]님이 퇴장하였습니다.";
						bDisconn=true;
					}
					
					// 다른 클라이언트에게 전송
					sendMsg(msg);
					
					if(bDisconn) { // 퇴장인경우
						client.remove(this);
						sc = null;
						
						System.out.println(msg);
						break;
					}
					
				}
				
			} catch (IOException e) {
				msg="["+nickName+"]님이 퇴장하였습니다.";
				
				sendMsg(msg);

				client.remove(this);
				sc = null;
				
				System.out.println(msg);
			}
		}
	}
	
}

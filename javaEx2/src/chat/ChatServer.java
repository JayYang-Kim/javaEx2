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
			System.out.println("���� ���� !!!");
			while (true) {
				// Ŭ���̾�Ʈ�� �����ϱ⸦ ��ٸ���.
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
	
	// ������ Ŭ����
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
				// System.out.println(sc.getInetAddress().getHostAddress()+"���� Ŭ���̾�Ʈ ����...");
			}catch(Exception e){
			}
		}
		
		public PrintWriter getOut() {
			return out;
		}
		
		// �ٸ� Ŭ���̾�Ʈ���� ����
		public void sendMsg(String msg) {
			for (WorkerThread user : client) {
				try {
					if (user==this) // �ڱ� �ڽ��� ���
						continue;
				
					user.getOut().println(msg);
				}catch(Exception e) {
				}
			}	// for_end
		}

		public void run() {
			String msg = null;
			try {
				// Ŭ���̾�Ʈ�� �г����� �޴´�.
				nickName = in.readLine();
				
				// Ŭ���̾�Ʈ ������ �����Ѵ�.
				client.add(this);

				// ���� ����� �ٸ� Ŭ���̾�Ʈ���� �˸���.
				msg="["+nickName+"]���� �����Ͽ����ϴ�.";
				sendMsg(msg);

				System.out.println(msg);

				// Ŭ���̾�Ʈ�� ä�� ���ڿ��� �޾� �ٸ� Ŭ���̾�Ʈ���� �����Ѵ�.
				boolean bDisconn=false;
				while ((msg = in.readLine()) != null) {
					if(msg.startsWith("/q")) {
						msg="["+nickName+"]���� �����Ͽ����ϴ�.";
						bDisconn=true;
					}
					
					// �ٸ� Ŭ���̾�Ʈ���� ����
					sendMsg(msg);
					
					if(bDisconn) { // �����ΰ��
						client.remove(this);
						sc = null;
						
						System.out.println(msg);
						break;
					}
					
				}
				
			} catch (IOException e) {
				msg="["+nickName+"]���� �����Ͽ����ϴ�.";
				
				sendMsg(msg);

				client.remove(this);
				sc = null;
				
				System.out.println(msg);
			}
		}
	}
	
}

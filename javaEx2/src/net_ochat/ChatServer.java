package net_ochat;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/*
 * ObjectInputStream�� ������ ����� �ݴ����� OutputObjectStream���� Ȯ�����ϴ�
 * �۾��� ��ġ�� ������ �����ڸ� ȣ���ϰ� �ݴ��� OutputObjectStream����
 * �ش���(������ ���� �ѹ�)�� ���� �ޱ� �������� ���ŷ�ȴ�.
 * ���� ����/Ŭ���̾�Ʈ ��Ž� ���� ObjectInputStream�� ��� ��������� ��ٸ���
 * ���ŷ ���°� �ǹǷ� ����/Ŭ���̾�Ʈ�� ��� ������� ������.
 * �̸� �ذ��ϱ� ���� �ذ�å : oos.flush();
 */

public class ChatServer {
	// Ŭ���̾�Ʈ ���� ����
	private ArrayList<SocketInfo> client = new ArrayList<SocketInfo>();
	private ServerSocket ss = null;
	private int port=7777;
	
	public void serverStart() {
		try {
			ss = new ServerSocket(port);
			System.out.println("���� ���� !!!");
			
			Socket sc = null;
			while(true) {
				sc = ss.accept();
				
				WorkerThread th = new WorkerThread(sc);
				th.start();
			}
		} catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public static void main(String[] args) {
		ChatServer ob = new ChatServer();
		ob.serverStart();
	}
	
	// �۾��� ������
	class WorkerThread extends Thread {
		private Socket sc;
		public WorkerThread(Socket sc) {
			this.sc = sc;
		}
		
		public void run() {
			String userName = null;
			
			try {
				ObjectOutputStream oos = new ObjectOutputStream(sc.getOutputStream());
				oos.flush();  // JDK 6.0 �̻��� ���� ����
				ObjectInputStream ois = new ObjectInputStream(sc.getInputStream());
				System.out.println(sc.getInetAddress().getHostAddress() + "���� !!! ");
				
				Object ob = null;
				while((ob = ois.readObject()) != null) {
					if(ob instanceof Login) {
						// �α��� ��û�� ��쿡 �α��� ���� �Ǵ� ���и� ����
						Login login = (Login)ob;
						userName = login.getUserName();
						if(login.getCode() == 100) {
							boolean flag = false;
							for(SocketInfo si : client) {
								if(si.userName.equals(login.getUserName())) {
									flag = true;
									break;
								}
							}
							
							if(flag || userName == null) {
								// �α��ο� ������ ����� �˷���
								Login loginFail = new Login();
								loginFail.setCode(120);
								loginFail.setUserName(login.getUserName());
								oos.writeObject(loginFail);
								
								sc.close();
								sc=null;
								break;
							}
							
							// �α��ο� ������ ����� �˷���
							Login loginOk = new Login();
							loginOk.setCode(110);
							loginOk.setUserName(login.getUserName());
							oos.writeObject(loginOk);
							
							// Ŭ���̾�Ʈ ���� ����
							SocketInfo info = new SocketInfo();
							info.userName = login.getUserName();
							info.sock = sc;
							info.oos = oos;
							client.add(info);
							
							System.out.println(login.getUserName() + " �α��� ��� !!!");
							
							// �ٸ� ��� Ŭ���̾�Ʈ���� ���� ��� �˸�
							Login loginUser = new Login();
							loginUser.setCode(111);
							loginUser.setUserName(login.getUserName());
							
							for(SocketInfo si : client) {
								if(si.sock == sc)
									continue;
								
								si.oos.writeObject(loginUser);
								Thread.sleep(10);
							}
						}
					} // if(ob instanceof Login) {_end
					else if(ob instanceof ChatMsg) {
						ChatMsg chatMsg = (ChatMsg)ob;
						
						for(SocketInfo si : client) {
							if(si.sock == sc)
								continue;
							
							si.oos.writeObject(chatMsg);
							Thread.sleep(10);
						}
					}
				} // while_end
			} catch(Exception e) {
				// �ٸ� ��� Ŭ���̾�Ʈ���� �α׾ƿ� ��� �˸�
				Login logoutUser = new Login();
				logoutUser.setCode(201);
				logoutUser.setUserName(userName);
				
				System.out.println(userName + " ���� !!!");
				
				SocketInfo info = null;
				try {
					for(SocketInfo si : client) {
						if(si.sock == sc) {
							info = si;
							continue;
						}
						if(si.oos != null)
							si.oos.writeObject(logoutUser);
						
						Thread.sleep(10);
					}
				} catch(Exception e1) {}
				
				if(info != null ) {
					info.sock = null;
					info.oos = null;
					client.remove(info);
				}
				
				// System.out.println(e.toString());
			}
		}
	} // WorkerThread_end
}

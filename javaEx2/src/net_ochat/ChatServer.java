package net_ochat;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/*
 * ObjectInputStream은 생성시 연결된 반대쪽이 OutputObjectStream인지 확인을하는
 * 작업을 거치기 때문에 생성자를 호출하고 반대쪽 OutputObjectStream에서
 * 해더값(일종의 매직 넘버)를 전송 받기 전까지는 블록킹된다.
 * 따라서 서버/클라이언트 통신시 양쪽 ObjectInputStream이 모두 헤더가값을 기다리는
 * 블록킹 상태가 되므로 서버/클라이언트가 모두 데드락에 빠진다.
 * 이를 해결하기 위한 해결책 : oos.flush();
 */

public class ChatServer {
	// 클라이언트 정보 저장
	private ArrayList<SocketInfo> client = new ArrayList<SocketInfo>();
	private ServerSocket ss = null;
	private int port=7777;
	
	public void serverStart() {
		try {
			ss = new ServerSocket(port);
			System.out.println("서버 시작 !!!");
			
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
	
	// 작업자 스레드
	class WorkerThread extends Thread {
		private Socket sc;
		public WorkerThread(Socket sc) {
			this.sc = sc;
		}
		
		public void run() {
			String userName = null;
			
			try {
				ObjectOutputStream oos = new ObjectOutputStream(sc.getOutputStream());
				oos.flush();  // JDK 6.0 이상은 생략 가능
				ObjectInputStream ois = new ObjectInputStream(sc.getInputStream());
				System.out.println(sc.getInetAddress().getHostAddress() + "접속 !!! ");
				
				Object ob = null;
				while((ob = ois.readObject()) != null) {
					if(ob instanceof Login) {
						// 로그인 요청인 경우에 로그인 성공 또는 실패를 전송
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
								// 로그인에 실패한 사실을 알려줌
								Login loginFail = new Login();
								loginFail.setCode(120);
								loginFail.setUserName(login.getUserName());
								oos.writeObject(loginFail);
								
								sc.close();
								sc=null;
								break;
							}
							
							// 로그인에 성공한 사실을 알려줌
							Login loginOk = new Login();
							loginOk.setCode(110);
							loginOk.setUserName(login.getUserName());
							oos.writeObject(loginOk);
							
							// 클라이언트 정보 저장
							SocketInfo info = new SocketInfo();
							info.userName = login.getUserName();
							info.sock = sc;
							info.oos = oos;
							client.add(info);
							
							System.out.println(login.getUserName() + " 로그인 허용 !!!");
							
							// 다른 모든 클라이언트에게 접속 사실 알림
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
				// 다른 모든 클라이언트에게 로그아웃 사실 알림
				Login logoutUser = new Login();
				logoutUser.setCode(201);
				logoutUser.setUserName(userName);
				
				System.out.println(userName + " 퇴장 !!!");
				
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

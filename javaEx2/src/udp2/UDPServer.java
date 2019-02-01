package udp2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Random;
// 1 : 다
public class UDPServer implements Runnable {
	private int SIZE = 80;
	
	private int sendPort=0, recePort=2401; // 서로 다른 포트 client는 반대
	private ArrayList<User> list= new ArrayList<User>();
	private DatagramSocket sendSock=null, receSock=null;
	private DatagramPacket sendPack, recePack;
	private InetAddress ia=null;
	
	private int guess;

	public UDPServer() {
		Thread th = new Thread(this);
		th.start();
		System.out.println("UDP 서버 시작 !!!");
	}

	public void run() {
		try {
			guess=getNumber();

			String str, s;
			int num, port;
			
			sendSock=new DatagramSocket();
			receSock=new DatagramSocket(recePort);

			byte[] buffer;
			while (true) {
				buffer = new byte[SIZE];
				recePack=new DatagramPacket(buffer,SIZE);
				receSock.receive(recePack);
				ia = recePack.getAddress();
				
				// 클라이언트 전송 받은 데이터
				// 처음접속할때
				//    conn:닉네임:포트번호
				// 게임중일때
				//    num:닉네임:세자리수
				// 종료
				//    quit:닉네임:포트번호
				
				str = new String(recePack.getData()).trim();
				String []ss=str.split(":");

				if(ss.length!=3)
					continue;
				
				if(ss[0].equals("conn")) {
					// 접속을 다른 클라이언트에게 전송
					port = Integer.parseInt(ss[2]);
					broadcast(str);			
					
					// 리스트에 접속한 클라이언트의 닉네임 저장
					list.add(new User(ss[1], ia.getHostAddress(),port));
					System.out.println(ss[1]+"님이 접속하였습니다.");
					
				} else if(ss[0].equals("num")) {
					num=Integer.parseInt(ss[2]);
					
					s=check(num, guess);
					if(s.equals("Congratulations")) {
						guess=getNumber();
					}					
					str="game:"+ss[1]+"->"+num+":"+s;
					broadcast(str);
					
				} else if(ss[0].equals("quit")) {
					for(User u : list) {
						if(u.nickNname.equals(ss[1])) {
							list.remove(u);
							break;
						}
					}
					System.out.println(ss[1]+"님이 퇴장했습니다.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void broadcast(String msg) {
		try {
			if (ia == null)
				return;

			byte[] buffer;
			InetAddress dest;
			for(User u : list) {
				dest=InetAddress.getByName(u.addr);
				buffer =msg.getBytes();
				sendPort=u.port;
				sendPack=new DatagramPacket(buffer, buffer.length, dest, sendPort);
				sendSock.send(sendPack);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 세자리의 난수를 발생함
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
		
		System.out.println(comNum);
		
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

class User {
	String nickNname;
	String addr;
	int port;
	public User(String nickNname, String addr, int port){
		this.nickNname = nickNname;
		this.addr = addr;
		this.port = port;
	}
}

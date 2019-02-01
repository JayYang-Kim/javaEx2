package net_ochat;

import java.io.ObjectOutputStream;
import java.net.Socket;

// 서버용
// 클라이언트의 소켓 및 닉네임 저장용 클래스
public class SocketInfo {
	Socket sock;
	String userName; 
	ObjectOutputStream oos;
}

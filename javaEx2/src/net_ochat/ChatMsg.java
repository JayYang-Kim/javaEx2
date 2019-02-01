package net_ochat;

import java.io.Serializable;

// 서버, 클라이언트 공용
public class ChatMsg implements Serializable { // 직렬화
	private static final long serialVersionUID = 2L;
	
	private String userName;
	private String msg;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}

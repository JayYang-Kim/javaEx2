package net_ochat;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient  extends JFrame implements ActionListener, Runnable {
	private static final long serialVersionUID = 3L;

	private JTextField tfIp;
	private JTextField tfName;
	private JButton conn, disConn;
	private JTextArea taMsg;
	private JTextField tfMsg;
	
	private Socket sc = null;
	
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	
	private String host = "127.0.0.1";
	private String userName;
	private int port = 7777;

	public ChatClient() {
		JPanel pan = new JPanel();
		
		tfIp = new JTextField(10);
		tfIp.setText("127.0.0.1");
		tfName = new JTextField(8);
		conn = new JButton("서버 접속");
		conn.addActionListener(this);
		disConn = new JButton("연결 해제");
		disConn.addActionListener(this);
		disConn.setEnabled(false);
		
		pan.add(new JLabel("서버주소 : ", JLabel.LEFT));
		pan.add(tfIp);
		pan.add(new JLabel("  닉네임 : ", JLabel.LEFT));
		pan.add(tfName);
		pan.add(conn);
		pan.add(disConn);
		add(pan, BorderLayout.NORTH);
		
		taMsg = new JTextArea();
		taMsg.setEditable(false);
		JScrollPane pane=new JScrollPane(taMsg);
		add(pane, BorderLayout.CENTER);
		
		tfMsg = new JTextField();
		tfMsg.addActionListener(this);
		add(tfMsg, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("채팅 클라이언트");
		setSize(500, 400);
		setVisible(true);
		setResizable(false);
		
		tfIp.requestFocus();
	}
	
	public void run() {
		try {
			Object ob = null;
			while((ob = ois.readObject()) != null) {
				if(ob instanceof Login) {
					Login login = (Login) ob;
					
					if(login.getCode() == 110) { // 로그인 성공
						taMsg.setText("서버에 로그인 했습니다. !!!");
						
						conn.setEnabled(false);
						disConn.setEnabled(true);
						tfMsg.requestFocus();
					
					} else if (login.getCode() == 111) { // 다른 클라이언트가 로그인 한 경우
						taMsg.append("\r\n"+login.getUserName()+"님이 입장했습니다. !!!");
						tfMsg.requestFocus();

					} else if (login.getCode() == 120) { // 로그인 실패
						taMsg.setText("서버에 로그인 실패 했습니다. !!!");
						conn.setEnabled(true);
						disConn.setEnabled(false);
						
						sc.close();
						sc = null;
						ois=null;
						oos=null;
						break;
					} else if (login.getCode() == 201) { // 다른 클라이언트가 로그 아웃 한 경우
						taMsg.append("\r\n"+login.getUserName()+"님이 퇴장했습니다. !!!");
						tfMsg.requestFocus();
					}
				} // if(ob instanceof Login) { _ end
				else if(ob instanceof ChatMsg) {
					ChatMsg chatMsg = (ChatMsg) ob;
					taMsg.append("\r\n"+ chatMsg.getUserName() + "] " +chatMsg.getMsg());
					tfMsg.requestFocus();
				}
				taMsg.setCaretPosition(taMsg.getDocument().getLength());
			}
		} catch(Exception e) {
			System.out.println(e.toString());
			sc = null;
			ois=null;
			oos=null;
			conn.setEnabled(true);
			disConn.setEnabled(false);
			taMsg.append("\r\n접속 종료. !!!");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		Component ob = (Component)evt.getSource();
		
		if(ob instanceof JTextField) {
			sendMsg();
		} else if(ob instanceof JButton) {
			// 서버 연결 버튼
			if(ob == conn) {
				host = tfIp.getText().trim();
				if(host.equals("")) {
					tfIp.requestFocus();
					return;
				}
				
				userName = tfName.getText().trim();
				if(userName.equals("")) {
					tfName.requestFocus();
					return;
				}
				
				// 서버에 접속
				try {
					sc = new Socket(host, port);
					
					oos = new ObjectOutputStream(sc.getOutputStream());
					oos.flush(); // 중요(생략 불가)
					ois = new ObjectInputStream(sc.getInputStream());
					
					// 로그인 요청
					Login loginReq = new Login();
					loginReq.setCode(100);
					loginReq.setUserName(userName);
					oos.writeObject(loginReq);
					
					// 스레드 생성
					Thread th = new Thread(this);
					th.start();
				} catch(Exception e) {
					System.out.println(e.toString());

					sc = null;
					conn.setEnabled(true);
					disConn.setEnabled(false);
				}
				
			} else if(ob == disConn) {
				try {
					if(sc != null) {
						// 서버에 로그아웃 정보를 보냄(보내지 않아도 됨)
						
						sc.close();
					}
				} catch(Exception e) {
					System.out.println(e.toString());
				}
				
				sc = null;
				conn.setEnabled(true);
				disConn.setEnabled(false);
				
			}
		}
	}
	
	// 서버에 메시지 전송
	private void sendMsg() {
		String msg =tfMsg.getText().trim(); 
		if(msg.equals(""))
			return;
		
		if(sc == null)
			return;
		
		// 서버에 메시지 전송
		ChatMsg chatMsg = new ChatMsg();
		chatMsg.setUserName(userName);
		chatMsg.setMsg(msg);
		
		try {
			oos.writeObject(chatMsg);
			taMsg.append("\r\n보냄] "+msg);
		} catch(Exception e) {
			System.out.println(e.toString());
			
			sc = null;
			conn.setEnabled(true);
			disConn.setEnabled(false);
		}
		taMsg.setCaretPosition(taMsg.getDocument().getLength());
		
		tfMsg.setText("");
		tfMsg.requestFocus();
	}

	public static void main(String[] args){
		new ChatClient();
	}
}

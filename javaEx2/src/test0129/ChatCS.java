package test0129;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

// DatagramSocket : UDP 소켓으로 데이터그램 패킷을 전송하거나 수신
// DatagramPacket : UDP 프로토콜을 통하여 전송 될 수 있는 데이터
// MulticastSocket : 한번에 다수의 클라이언트에 데이터 그램을 전송

// 그룹지정 : D class(224.0.0.0 ~ 239.255.255.255) 

// 多 :多 일 경우 위의 D class의 경우만 사용할 수 있다.
public class ChatCS extends JFrame implements ActionListener, Runnable { // 스윙 , action이벤트처리, 스레드 작업

	private static final long serialVersionUID = 1L;

	private JTextField tf = new JTextField(); // 한줄
	private JTextArea ta = new JTextArea(); // 여러줄

	private MulticastSocket ms = null;
	private InetAddress xGroup = null;

	private String host = "230.0.0.1";
	private int port = 5555;
	private String userName = "뚜뚜루";

	public ChatCS() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ta.setEditable(false);// 수정못하고 읽기전용으로
		JScrollPane pane = new JScrollPane(ta); // 스크롤바
		add(pane, BorderLayout.CENTER);

		tf.addActionListener(this); // 엔터처리 가능하게
		add(tf, BorderLayout.SOUTH);

		setTitle("★채팅★");
		setSize(500, 500);
		setResizable(false);
		setVisible(true);

	}

	public static void main(String[] args) {
		new ChatCS().setup();
	}

	public void setup() {
		try {
			xGroup = InetAddress.getByName(host); // inetaddress 
			ms = new MulticastSocket(port); // 객체생성 포트번호로

			// 특정 그룹에 포함
			ms.joinGroup(xGroup); 

			Thread t = new Thread(this); // 받으려고 스레드 존재
			t.start();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void disConn() {
		try {
			// 특정그룹에서 빠져나옴
			ms.leaveGroup(xGroup);
			ms.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		ms = null;
	}

	@Override
	public void run() {
		if (ms == null)
			return;

		try {
			while (true) {
				byte[] b = new byte[512];
				// 전송 받을 패킷
				DatagramPacket p = new DatagramPacket(b, b.length);

				// 전송 받음
				ms.receive(p);
				String s = new String(p.getData()).trim(); // getData() 바이트배열임 =>  new String으로 문자열
				ta.append(s + "\n");
				ta.setCaretPosition(ta.getDocument().getLength());
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			disConn();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == tf) {
			String s = tf.getText().trim();
			if (s.length() == 0)
				return;

			try { // 전송
				if (ms == null)
					return;

				byte[] b = (userName + "] " + s).getBytes(); // 문자열을 바이트배열로 (바이트로만 전송됨)

				DatagramPacket p = new DatagramPacket(b, b.length, xGroup, port);
				ms.send(p);

				tf.setText("");
				tf.requestFocus();
			} catch (Exception e2) {

			}
		}

	}

}

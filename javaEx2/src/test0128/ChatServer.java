package test0128;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

// 1:1
public class ChatServer extends JFrame implements ActionListener, Runnable { // 스윙 , action이벤트처리, 스레드 작업

	private static final long serialVersionUID = 1L;

	private JTextField tf = new JTextField();
	private JTextArea ta = new JTextArea();

	private ServerSocket ss = null; // 서버소켓
	private int port = 8000; // 포트번호 8000번
	private Socket sc = null; // 소켓

	public ChatServer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ta.setEditable(false);// 수정못하고 읽기전용으로
		JScrollPane pane = new JScrollPane(ta); // 스크롤바
		add(pane, BorderLayout.CENTER);

		tf.addActionListener(this); // 엔터처리 가능하게
		add(tf, BorderLayout.SOUTH);

		setTitle("★ 채팅 서버 ★");
		setSize(500, 500);
		setResizable(false);
		setVisible(true);

	}

	private void serverStart() {
		try {
			ss = new ServerSocket(port); // 서버 소켓 생성 => 서버 켜진 것
			ta.setText("★뾰롱 서버시작...\n");

			// 클라이언트가 접속하기를 대기
			sc = ss.accept(); // 서버는 멈췄다가 클라이언트가 접속하면 accept깨어남 // 접속한 클라이언트의 소켓을 넘겨줌

			// 클라이언트가 접속하면 스레드 실행!
			Thread t = new Thread(this); // run으로 제어가 넘어감
			t.start();

		} catch (Exception e) {
			// 예외발생하면 서버 죽은것
			System.out.println(e.toString());
		}
	}

	public static void main(String[] args) {
		new ChatServer().serverStart();
	}

	@Override
	public void run() {

		String s, ip = "";

		try {
			if (sc == null)
				return;

			// 클라이언트가 보낸 정보를 읽어들인다.

			// 접속한 클라이언트의 입력 스트림을 구해서 클라이언트 정보를 읽어들인다.
			BufferedReader br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			// 소켓속에는 바이트 스트림(get으로 들고온당)

			ip = sc.getInetAddress().getHostAddress(); // ip주소 읽어냄
			ta.append("[" + ip + "] 뾰롱★ 접속...\n");

			while ((s = br.readLine()) != null) { // 계속  받기 위해 while => 클라이언트 나갈 때 까지
				// 채팅문자를 받음
				ta.append(s + "\n");
				ta.setCaretPosition(ta.getDocument().getLength());
			}
		} catch (IOException e) {
			s = "[" + ip + "] 님이 퇴장하셨습니다. 뾰롱★\n";
			ta.append(s);
			sc = null; // 소켓은 끊김.
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == tf) {
			String s = tf.getText().trim();
			if (s.length() == 0)
				return;

			try { // 클라이언트에게 정보를 전송
				PrintWriter pw = new PrintWriter(sc.getOutputStream(), true); // true는 autoflush
				pw.println("서버] " + s); // print (x) 엔터 때문

				ta.append("보냄] " + s + "\n");
				ta.setCaretPosition(ta.getDocument().getLength()); // 스크롤바 자동 이동

				tf.setText("");
				tf.requestFocus();
			} catch (Exception e2) {
				ta.append("클라이언트가 퇴장했습니다. 뾰롱★\n");
				sc = null;
			}
		}

	}

}

package test0128;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient extends JFrame implements ActionListener, Runnable { // 스윙 , action이벤트처리, 스레드 작업

	private static final long serialVersionUID = 1L;

	private JTextField tf = new JTextField();
	private JTextArea ta = new JTextArea();

	private Socket sc = null; // 소켓은 전화기

	private String host = "127.0.0.1";
	// private String host = "211.238.142.206";
	private int port = 8000;
	private String nickName = "뚜정";

	public ChatClient() {
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

	private void connect() {
		// 서버에 연결
		try {
			sc = new Socket(host, port);
			ta.setText("서버에 접속...뾰롱★\n");

			Thread t = new Thread(this);
			t.start();

		} catch (IOException e) { // 서버 죽을 때 발생 IOException
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ChatClient().connect();
	}

	@Override
	public void run() {
		String s;

		try {
			if (sc == null)
				return;

			// 서버가 보낸 정보 받기
			BufferedReader br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			while ((s = br.readLine()) != null) {
				ta.append(s + "\n");
				ta.setCaretPosition(ta.getDocument().getLength());

			}

		} catch (IOException e) {
			ta.append("서버가 죽었어  뾰롱★\n");
			sc = null;
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == tf) {
			String s = tf.getText().trim();
			if (s.length() == 0)
				return;

			try { 
				// 전송
				// 서버에게 정보를 보낸다.
				PrintWriter pw = new PrintWriter(sc.getOutputStream(), true); // true는 autoflush
				pw.println(nickName + "] " + s);

				ta.append("보냄] " + s + "\n");
				ta.setCaretPosition(ta.getDocument().getLength()); // 스크롤바 자동 이동

				tf.setText("");
				tf.requestFocus();
			} catch (IOException e2) {
				ta.append("서버가 종료되었습니다. 뾰롱★\n");
				sc = null;

			}
		}

	}

}

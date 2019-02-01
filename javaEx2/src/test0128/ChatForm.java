package test0128;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatForm extends JFrame implements ActionListener, Runnable { // 스윙 , action이벤트처리, 스레드 작업

	private static final long serialVersionUID = 1L;

	private JTextField tf = new JTextField(); // 한줄
	private JTextArea ta = new JTextArea(); // 여러줄

	public ChatForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ta.setEditable(false);// 수정못하고 읽기전용으로
		JScrollPane pane = new JScrollPane(ta); // 스크롤바
		add(pane, BorderLayout.CENTER);

		tf.addActionListener(this); // 엔터처리 가능하게
		add(tf, BorderLayout.SOUTH);

		setTitle("채팅...");
		setSize(500, 500);
		setResizable(false);
		setVisible(true);

	}

	public static void main(String[] args) {
		new ChatForm();
	}

	@Override
	public void run() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == tf) {
			String s = tf.getText().trim();
			if (s.length() == 0)
				return;

			try { // 전송
				ta.append("보냄] " + s + "\n");
				ta.setCaretPosition(ta.getDocument().getLength()); // 스크롤바 자동 이동

				tf.setText("");
				tf.requestFocus();
			} catch (Exception e2) {

			}
		}

	}

}

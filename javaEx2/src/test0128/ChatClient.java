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

public class ChatClient extends JFrame implements ActionListener, Runnable { // ���� , action�̺�Ʈó��, ������ �۾�

	private static final long serialVersionUID = 1L;

	private JTextField tf = new JTextField();
	private JTextArea ta = new JTextArea();

	private Socket sc = null; // ������ ��ȭ��

	private String host = "127.0.0.1";
	// private String host = "211.238.142.206";
	private int port = 8000;
	private String nickName = "����";

	public ChatClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ta.setEditable(false);// �������ϰ� �б���������
		JScrollPane pane = new JScrollPane(ta); // ��ũ�ѹ�
		add(pane, BorderLayout.CENTER);

		tf.addActionListener(this); // ����ó�� �����ϰ�
		add(tf, BorderLayout.SOUTH);

		setTitle("�� ä�� ���� ��");
		setSize(500, 500);
		setResizable(false);
		setVisible(true);

	}

	private void connect() {
		// ������ ����
		try {
			sc = new Socket(host, port);
			ta.setText("������ ����...�Ϸա�\n");

			Thread t = new Thread(this);
			t.start();

		} catch (IOException e) { // ���� ���� �� �߻� IOException
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

			// ������ ���� ���� �ޱ�
			BufferedReader br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			while ((s = br.readLine()) != null) {
				ta.append(s + "\n");
				ta.setCaretPosition(ta.getDocument().getLength());

			}

		} catch (IOException e) {
			ta.append("������ �׾���  �Ϸա�\n");
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
				// ����
				// �������� ������ ������.
				PrintWriter pw = new PrintWriter(sc.getOutputStream(), true); // true�� autoflush
				pw.println(nickName + "] " + s);

				ta.append("����] " + s + "\n");
				ta.setCaretPosition(ta.getDocument().getLength()); // ��ũ�ѹ� �ڵ� �̵�

				tf.setText("");
				tf.requestFocus();
			} catch (IOException e2) {
				ta.append("������ ����Ǿ����ϴ�. �Ϸա�\n");
				sc = null;

			}
		}

	}

}

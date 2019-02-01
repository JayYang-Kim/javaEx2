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
public class ChatServer extends JFrame implements ActionListener, Runnable { // ���� , action�̺�Ʈó��, ������ �۾�

	private static final long serialVersionUID = 1L;

	private JTextField tf = new JTextField();
	private JTextArea ta = new JTextArea();

	private ServerSocket ss = null; // ��������
	private int port = 8000; // ��Ʈ��ȣ 8000��
	private Socket sc = null; // ����

	public ChatServer() {
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

	private void serverStart() {
		try {
			ss = new ServerSocket(port); // ���� ���� ���� => ���� ���� ��
			ta.setText("�ڻϷ� ��������...\n");

			// Ŭ���̾�Ʈ�� �����ϱ⸦ ���
			sc = ss.accept(); // ������ ����ٰ� Ŭ���̾�Ʈ�� �����ϸ� accept��� // ������ Ŭ���̾�Ʈ�� ������ �Ѱ���

			// Ŭ���̾�Ʈ�� �����ϸ� ������ ����!
			Thread t = new Thread(this); // run���� ��� �Ѿ
			t.start();

		} catch (Exception e) {
			// ���ܹ߻��ϸ� ���� ������
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

			// Ŭ���̾�Ʈ�� ���� ������ �о���δ�.

			// ������ Ŭ���̾�Ʈ�� �Է� ��Ʈ���� ���ؼ� Ŭ���̾�Ʈ ������ �о���δ�.
			BufferedReader br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			// ���ϼӿ��� ����Ʈ ��Ʈ��(get���� ���´�)

			ip = sc.getInetAddress().getHostAddress(); // ip�ּ� �о
			ta.append("[" + ip + "] �Ϸա� ����...\n");

			while ((s = br.readLine()) != null) { // ���  �ޱ� ���� while => Ŭ���̾�Ʈ ���� �� ����
				// ä�ù��ڸ� ����
				ta.append(s + "\n");
				ta.setCaretPosition(ta.getDocument().getLength());
			}
		} catch (IOException e) {
			s = "[" + ip + "] ���� �����ϼ̽��ϴ�. �Ϸա�\n";
			ta.append(s);
			sc = null; // ������ ����.
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == tf) {
			String s = tf.getText().trim();
			if (s.length() == 0)
				return;

			try { // Ŭ���̾�Ʈ���� ������ ����
				PrintWriter pw = new PrintWriter(sc.getOutputStream(), true); // true�� autoflush
				pw.println("����] " + s); // print (x) ���� ����

				ta.append("����] " + s + "\n");
				ta.setCaretPosition(ta.getDocument().getLength()); // ��ũ�ѹ� �ڵ� �̵�

				tf.setText("");
				tf.requestFocus();
			} catch (Exception e2) {
				ta.append("Ŭ���̾�Ʈ�� �����߽��ϴ�. �Ϸա�\n");
				sc = null;
			}
		}

	}

}

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

// DatagramSocket : UDP �������� �����ͱ׷� ��Ŷ�� �����ϰų� ����
// DatagramPacket : UDP ���������� ���Ͽ� ���� �� �� �ִ� ������
// MulticastSocket : �ѹ��� �ټ��� Ŭ���̾�Ʈ�� ������ �׷��� ����

// �׷����� : D class(224.0.0.0 ~ 239.255.255.255) 

// �� :�� �� ��� ���� D class�� ��츸 ����� �� �ִ�.
public class ChatCS extends JFrame implements ActionListener, Runnable { // ���� , action�̺�Ʈó��, ������ �۾�

	private static final long serialVersionUID = 1L;

	private JTextField tf = new JTextField(); // ����
	private JTextArea ta = new JTextArea(); // ������

	private MulticastSocket ms = null;
	private InetAddress xGroup = null;

	private String host = "230.0.0.1";
	private int port = 5555;
	private String userName = "�Ѷѷ�";

	public ChatCS() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ta.setEditable(false);// �������ϰ� �б���������
		JScrollPane pane = new JScrollPane(ta); // ��ũ�ѹ�
		add(pane, BorderLayout.CENTER);

		tf.addActionListener(this); // ����ó�� �����ϰ�
		add(tf, BorderLayout.SOUTH);

		setTitle("��ä�á�");
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
			ms = new MulticastSocket(port); // ��ü���� ��Ʈ��ȣ��

			// Ư�� �׷쿡 ����
			ms.joinGroup(xGroup); 

			Thread t = new Thread(this); // �������� ������ ����
			t.start();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void disConn() {
		try {
			// Ư���׷쿡�� ��������
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
				// ���� ���� ��Ŷ
				DatagramPacket p = new DatagramPacket(b, b.length);

				// ���� ����
				ms.receive(p);
				String s = new String(p.getData()).trim(); // getData() ����Ʈ�迭�� =>  new String���� ���ڿ�
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

			try { // ����
				if (ms == null)
					return;

				byte[] b = (userName + "] " + s).getBytes(); // ���ڿ��� ����Ʈ�迭�� (����Ʈ�θ� ���۵�)

				DatagramPacket p = new DatagramPacket(b, b.length, xGroup, port);
				ms.send(p);

				tf.setText("");
				tf.requestFocus();
			} catch (Exception e2) {

			}
		}

	}

}

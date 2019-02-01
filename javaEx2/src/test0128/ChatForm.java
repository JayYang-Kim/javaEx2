package test0128;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatForm extends JFrame implements ActionListener, Runnable { // ���� , action�̺�Ʈó��, ������ �۾�

	private static final long serialVersionUID = 1L;

	private JTextField tf = new JTextField(); // ����
	private JTextArea ta = new JTextArea(); // ������

	public ChatForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ta.setEditable(false);// �������ϰ� �б���������
		JScrollPane pane = new JScrollPane(ta); // ��ũ�ѹ�
		add(pane, BorderLayout.CENTER);

		tf.addActionListener(this); // ����ó�� �����ϰ�
		add(tf, BorderLayout.SOUTH);

		setTitle("ä��...");
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

			try { // ����
				ta.append("����] " + s + "\n");
				ta.setCaretPosition(ta.getDocument().getLength()); // ��ũ�ѹ� �ڵ� �̵�

				tf.setText("");
				tf.requestFocus();
			} catch (Exception e2) {

			}
		}

	}

}

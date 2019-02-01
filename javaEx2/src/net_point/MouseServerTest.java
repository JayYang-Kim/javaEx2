package net_point;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

// ���콺 ������
public class MouseServerTest extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;

	private Canvas gp; // �׸� �׸��� ����
	private ObjectInputStream ois = null; // ��ü ���� �����
	private ObjectOutputStream oos = null;
	private ServerSocket ss;

	public MouseServerTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		gp = new Canvas(); 
		gp.setBackground(new Color(255, 255, 125)); // ���� 
		add(gp);

		gp.addMouseListener(new MouseHandler()); //���콺 �̺�Ʈ ���� 2���� /���콺�̺�Ʈ ���ʿ����ʴ������� / ���콺����̺�Ʈ �巡�׾ص��

		setTitle("����");
		setSize(500, 500);
		setResizable(false); // ũ����ٲٰ�
		setVisible(true);

		Socket sock;
		try {
			ss = new ServerSocket(5555);
			sock = ss.accept();

			oos = new ObjectOutputStream(sock.getOutputStream()); // ObjectOutputStream�� appendx
			oos.flush();
			ois = new ObjectInputStream(sock.getInputStream());
			Thread th = new Thread(this); 
			th.start();
		} catch (Exception e) {
		}
	}

	public static void main(String[] args) {
		new MouseServerTest();
	}

	public void run() {
		try {
			while (true) {
				Object ob = ois.readObject(); // ���� �� �Է� readObject ....
				if (ob instanceof PointMsg) { // pointMsg��ü���
					PointMsg pm = (PointMsg) ob; // �ٿ�ĳ�����Ѵ�. (��ǥ��)

					Graphics g = gp.getGraphics();
					String str;
					int x, y;
					x = pm.x;
					y = pm.y;
					str = "<" + x + ", " + y + ">";
					g.drawString(str, x, y);
				}
			}
		} catch (Exception e) {
		}
	}

	class MouseHandler extends MouseAdapter {
		public void mousePressed(MouseEvent evt) { // ���콺 ������ ��
			Graphics g = gp.getGraphics();
			String str;
			int x, y;
			x = evt.getX(); // ��ǥ����
			y = evt.getY(); // ��ǥ����
			str = "(" + x + ", " + y + ")";
			g.drawString(str, x, y);

			try {
				PointMsg pm = new PointMsg();
				pm.x = evt.getX();
				pm.y = evt.getY();
				oos.writeObject(pm); // ��ü�� �����ش�. writeObject
			} catch (Exception e) {
			}
		}
	}
}

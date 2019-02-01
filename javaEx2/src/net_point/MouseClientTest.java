package net_point;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;

// 마우스 포인터
public class MouseClientTest extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;

	private Canvas gp;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;

	public MouseClientTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		gp = new Canvas();
		gp.setBackground(new Color(255, 255, 125));
		add(gp);

		gp.addMouseListener(new MouseHandler()); // 마우스 이밴트 눌럿다가 띤것

		setTitle("클라이언트");
		setSize(500, 500);
		setResizable(false); // 화면 크기 못바꿈
		setVisible(true);

		Socket sock;
		try {
			sock = new Socket("127.0.0.1", 5555);
			oos = new ObjectOutputStream(sock.getOutputStream());
			oos.flush(); // 중요
			ois = new ObjectInputStream(sock.getInputStream());
			Thread th = new Thread(this);
			th.start();
		} catch (Exception e) {
		}
	}

	public static void main(String[] args) {
		new MouseClientTest();
	}

	public void run() {
		try {
			while (true) {
				Object ob = ois.readObject();
				if (ob instanceof PointMsg) {
					PointMsg pm = (PointMsg) ob;

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
		public void mousePressed(MouseEvent evt) {
			Graphics g = gp.getGraphics();
			String str;
			int x, y;
			x = evt.getX();
			y = evt.getY();
			str = "(" + x + ", " + y + ")";
			g.drawString(str, x, y);

			try {
				PointMsg pm = new PointMsg();
				pm.x = evt.getX(); // 좌표 가져옴
				pm.y = evt.getY();
				oos.writeObject(pm);
			} catch (Exception e) {
			}
		}

	}
}
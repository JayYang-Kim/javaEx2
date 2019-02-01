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

// 마우스 포인터
public class MouseServerTest extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;

	private Canvas gp; // 그림 그리는 영역
	private ObjectInputStream ois = null; // 객체 통해 입출력
	private ObjectOutputStream oos = null;
	private ServerSocket ss;

	public MouseServerTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		gp = new Canvas(); 
		gp.setBackground(new Color(255, 255, 125)); // 배경색 
		add(gp);

		gp.addMouseListener(new MouseHandler()); //마우스 이벤트 종류 2가지 /마우스이벤트 왼쪽오른쪽눌렀을때 / 마우스모션이벤트 드래그앤드랍

		setTitle("서버");
		setSize(500, 500);
		setResizable(false); // 크기못바꾸게
		setVisible(true);

		Socket sock;
		try {
			ss = new ServerSocket(5555);
			sock = ss.accept();

			oos = new ObjectOutputStream(sock.getOutputStream()); // ObjectOutputStream는 appendx
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
				Object ob = ois.readObject(); // 받을 땐 입력 readObject ....
				if (ob instanceof PointMsg) { // pointMsg객체라면
					PointMsg pm = (PointMsg) ob; // 다운캐스팅한다. (좌표를)

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
		public void mousePressed(MouseEvent evt) { // 마우스 눌렀을 때
			Graphics g = gp.getGraphics();
			String str;
			int x, y;
			x = evt.getX(); // 좌표구함
			y = evt.getY(); // 좌표구함
			str = "(" + x + ", " + y + ")";
			g.drawString(str, x, y);

			try {
				PointMsg pm = new PointMsg();
				pm.x = evt.getX();
				pm.y = evt.getY();
				oos.writeObject(pm); // 객체로 보내준다. writeObject
			} catch (Exception e) {
			}
		}
	}
}

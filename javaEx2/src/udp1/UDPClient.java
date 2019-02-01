package udp1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//1:1
public class UDPClient extends JFrame implements Runnable, ActionListener {
	private static final long serialVersionUID = 1L;
	
	private JTextArea ta = new JTextArea();
	private JTextField tf = new JTextField(7);
	private JButton btn=new JButton("확인");

	private int SIZE = 80;
	private String host="127.0.0.1";
	
	private InetAddress ia = null;
	private int port1 = 7788, port2 = 7799;

	private DatagramSocket ds1, ds2;
	private DatagramPacket dp1, dp2;
	
	private String myNum;
	private int count;

	public UDPClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ta.setEditable(false);
		add(new JScrollPane(ta), BorderLayout.CENTER);
		
		JPanel p=new JPanel();
		p.add(new JLabel("your guess : "));
		p.add(tf);
		p.add(btn);
		btn.addActionListener(this);	
		
		add(p, BorderLayout.SOUTH);

		setLocation(400, 300);
		setSize(400, 300);
		setResizable(false);
		setVisible(true);

		try {
			ia = InetAddress.getByName(host);
			Thread th = new Thread(this);
			th.start();
			
			ta.setText("게임을 시작합니다.\n");
		} catch(Exception e) {
		}
	}

	public void run() {
		try {
			String str;
			ds2 = new DatagramSocket(port2);
			
			count=0;
			
			byte[] buffer;
			while (true) {
				buffer = new byte[SIZE];
				dp2 = new DatagramPacket(buffer, SIZE);
				ds2.receive(dp2);
				str = new String(dp2.getData());
				str=str.trim();
				
				if(str.equals("Congratulations")){
					ta.append("Congratulations !!!\n");
					
					int result;
					
					result = JOptionPane.showConfirmDialog(
						this
						, "계속 하시겠습니까?", "질문"
						, JOptionPane.YES_NO_OPTION
						, JOptionPane.QUESTION_MESSAGE
					);
					
					if(result==JOptionPane.OK_OPTION) {
						ta.setText("게임을 시작합니다.\n");
						count=0;
						continue;
					} else {
						ia=null;
						System.exit(0);
					}
				}
				
				count++;
				ta.append(count+ " your guess : " + myNum +" -> " + str + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (ia == null)
				return;
			
			myNum = tf.getText().trim();
			if(myNum.length()==0) {
				tf.requestFocus();
				return;
			}
			
			boolean b=Pattern.matches("\\d{3}", myNum);
			if(! b || ! formatOK(Integer.parseInt(myNum))) {
				JOptionPane.showMessageDialog(
						this, "서로 다른  1~9까지의 세자리 정수만 입력 가능합니다.", "알림"
						, JOptionPane.INFORMATION_MESSAGE 
					);
				tf.requestFocus();
				return;
			}

			byte[] buffer = myNum.getBytes();
			ds1 = new DatagramSocket();
			dp1 = new DatagramPacket(buffer, buffer.length, ia, port1);
			ds1.send(dp1);
			tf.setText("");
			tf.requestFocus();
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}
	
	// 서로 다른 세자리 수인지 확인
	 public boolean formatOK(int num){
		   if(num<123||num>987)
			   return false;
		   
		   int n1 = (int) num / 100;
		   int n2 = ((int) num / 10) %10;
		   int n3 =  num % 10;

		   return (n1 != n2 && n2 != n3 && n1!= n3);
	}
	 
	public static void main(String args[]) throws Exception {
		new UDPClient();
	}
}

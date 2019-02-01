package udp2;

import java.awt.BorderLayout;
import java.awt.Component;
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

// �г����� �ٸ��� �Է��ؾ���
public class UDPClient extends JFrame implements Runnable, ActionListener {
	private static final long serialVersionUID = 1L;
	
	private JTextField tfAddr=new JTextField(7);
	private JTextField tfNickName=new JTextField(7);
	private JButton btnConnect=new JButton("Connect");
	
	private JTextField tf = new JTextField(7);
	private JButton btnGame=new JButton("Ȯ��");
	private JTextArea taMsg=new JTextArea();
			
	private int SIZE = 80;
	private String host = "127.0.0.1";
	private InetAddress ia = null;
		
	private DatagramSocket sendSock=null, receSock=null;
	private DatagramPacket sendPack,recePack;
	private int sendPort=2401, recePort=0;
	
	private String nickName;
	private String myNum;

	public UDPClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel p=new JPanel();
		p.add(new JLabel("���� �ּ� : "));
		tfAddr.setText(host);
		p.add(tfAddr);
		p.add(new JLabel("  �г��� : "));
		p.add(tfNickName);
		p.add(btnConnect);
		btnConnect.addActionListener(this);
		add(p, BorderLayout.NORTH);

		taMsg.setEditable(false);
		JScrollPane pane=new JScrollPane(taMsg);
		add(pane, BorderLayout.CENTER);
		
		p=new JPanel();
		p.add(new JLabel("your guess : "));
		p.add(tf);
		p.add(btnGame);
		btnGame.addActionListener(this);	
		
		add(p, BorderLayout.SOUTH);
		
		setTitle("StrikeBall");
		setSize(700, 600);
		setResizable(false);
		setVisible(true);
		
		tfNickName.requestFocus();
	}
	
	public void init() {
		try {
			sendSock=new DatagramSocket();
			recePort=1024+(int)(Math.random()*28000); // ������ ������ ��Ʈ��ȣ �ٲ� (�������� �������)
			receSock=new DatagramSocket(recePort); 
			
			new Thread(this).start();
	
			ia= InetAddress.getByName(host);
			
			// �ڽ��� �г��Ӱ� ��Ʈ��ȣ�� ����
			//    conn:�г���:����Ʈ��Ʈ��ȣ
			String str="conn:"+nickName+":"+recePort; // ������ �𸣴� ��Ʈ��ȣ�� ���� ������
			byte[] buffer= str.getBytes();
			sendPack=new DatagramPacket(buffer,buffer.length,ia,sendPort);

		    sendSock.send(sendPack);
		    
		    btnConnect.setEnabled(false);
		} catch (Exception e) {
		}
	}

	public void run() {
		try {
			String str;
			
			byte[] buffer;
			while (true) {
				try {
					buffer=new byte[SIZE];
					recePack=new DatagramPacket(buffer,SIZE);
					receSock.receive(recePack);
					
					// �����κ��� ���� ���� ������
					// ó�������Ҷ�
					//    conn:�г���:��Ʈ��ȣ
					// �������϶�
					//    game:�г���->���ڸ���:�޽���
					str=new String(recePack.getData()).trim();
					String []ss=str.split(":");

					if(ss.length!=3)
						continue;
					
					if(ss[0].equals("conn")) {
						taMsg.append(ss[1]+"���� ���ӿ� ���� �߽��ϴ�.\n"); 
						taMsg.setCaretPosition(taMsg.getDocument().getLength());
					} else if(ss[0].equals("game")) {
						taMsg.append(ss[1]+" : " + ss[2]+"\n");
						taMsg.setCaretPosition(taMsg.getDocument().getLength());
					}
					
					if(ss[2].equals("Congratulations")){
						int result;
						
						result = JOptionPane.showConfirmDialog(
							this
							, "��� �Ͻðڽ��ϱ�?", "����"
							, JOptionPane.YES_NO_OPTION
							, JOptionPane.QUESTION_MESSAGE
						);
						
						if(result==JOptionPane.OK_OPTION) {
							taMsg.setText("������ �����մϴ�.\n");
							continue;
						} else {
							// �������� ����
							str="quit:"+nickName+":"+recePort;
							buffer= str.getBytes();
							sendPack=new DatagramPacket(buffer,buffer.length,ia,sendPort);
							  
						    sendSock.send(sendPack);
						
							ia=null;
							System.exit(0);
						}
					}					
					
				} catch(Exception e) {
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent evt) {
		Component ob = (Component)evt.getSource();
		
		if(ob==btnConnect) {
			host=tfAddr.getText().trim();
			if(host.length()==0) {
				JOptionPane.showMessageDialog(
						this, "���� �ּҸ� ��Ȯ�� �Է� �ϼ���.", "�˸�"
						, JOptionPane.INFORMATION_MESSAGE 
					);
				tfAddr.requestFocus();
				return;
			}
			
			nickName=tfNickName.getText().trim();
			if(nickName.length()==0) {
				JOptionPane.showMessageDialog(
						this, "�г����� ��Ȯ�� �Է� �ϼ���.", "�˸�"
						, JOptionPane.INFORMATION_MESSAGE 
					); 
				tfNickName.requestFocus();
				return;
			}
			
			init();
			
		} else if(ob==btnGame) {
			try {
				if(ia == null)
					return;
				
				myNum = tf.getText().trim();
				if(myNum.length()==0) {
					tf.requestFocus();
					return;
				}
				
				// ����ǥ�������� 3�ڸ� ������ �Է� �����ϵ��� ����
				boolean b=Pattern.matches("\\d{3}", myNum);
				if(! b || ! formatOK(Integer.parseInt(myNum))) {
					JOptionPane.showMessageDialog(
							this, "���� �ٸ�  1~9������ ���ڸ� ������ �Է� �����մϴ�.", "�˸�"
							, JOptionPane.INFORMATION_MESSAGE 
						);
					tf.requestFocus();
					return;
				}
	
				// ������ ���� 
				//     num:�г���:���ڸ���
				String str="num:"+nickName+":"+myNum;
				byte[] buffer= str.getBytes();
				sendPack=new DatagramPacket(buffer,buffer.length,ia,sendPort);
				  
			    sendSock.send(sendPack);

				tf.setText("");
				tf.requestFocus();
			} catch (Exception exp) {
				exp.printStackTrace();
			}
		}
	}
	
	// ���� �ٸ� ���ڸ� ������ Ȯ��
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

package chat;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient extends JFrame implements ActionListener, Runnable {
	private static final long serialVersionUID = 1L;
	
	private JTextField tfAddr=new JTextField(7);
	private JTextField tfNickName=new JTextField(7);
	private JButton btnConnect=new JButton("������ ����");
	
	private JTextField tfTalk=new JTextField();
	private JTextArea taMsg=new JTextArea();
			
	private Socket sc = null;
	private String host = "127.0.0.1";
	private String nickName;
	private int port = 8000;

	public ChatClient() {
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
		
		tfTalk.addActionListener(this);
		add(tfTalk, BorderLayout.SOUTH);
		
		setTitle("ä�� Ŭ���̾�Ʈ");
		setSize(700, 600);
		setVisible(true);
		setResizable(false);
		
		tfNickName.requestFocus();
	}
	
	public void run() {
		String msg = null;
		
		try {
			if(sc==null)
				return;
			
			// ������ �г��� ����
			PrintWriter out = new PrintWriter(sc.getOutputStream(), true);
			out.println(nickName);
			
			// ������ ���� ������ ���� �Է� ��Ʈ��
			BufferedReader br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			msg = null;
		
			while((msg = br.readLine()) != null) {
				taMsg.append(msg+"\n");
				taMsg.setCaretPosition(taMsg.getDocument().getLength());
			}
		} catch (IOException e) {
			msg = "������ ���� �߽��ϴ�.\n";
			taMsg.append(msg);
			taMsg.setCaretPosition(taMsg.getDocument().getLength());
			
			sc= null;
			// System.out.println(e.toString());
		}
	}
	
	@Override
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
			
			connect();
		} else if(ob == tfTalk) {
			if(sc==null)
				return;
			
			String msg =tfTalk.getText().trim(); 
			if(msg.length()==0)
				return;
			
			try {
				if(msg.startsWith("/q")) {
					PrintWriter out = new PrintWriter(sc.getOutputStream(), true);
					out.println("/q");
					
					taMsg.append("������ ���� �Ͽ����ϴ�.\n");
					taMsg.setCaretPosition(taMsg.getDocument().getLength());
					tfTalk.setText("");
					
					sc=null;
					btnConnect.setEnabled(true);
					return;
				}
			
			// ������ �޽��� ����
				PrintWriter out = new PrintWriter(sc.getOutputStream(), true);
				out.println(nickName+"] "+msg);
				
				taMsg.append("����] " + msg + "\n");
				taMsg.setCaretPosition(taMsg.getDocument().getLength());
			
				tfTalk.setText("");
				tfTalk.requestFocus();
			} catch(IOException e) {
				System.out.println(e.toString());
			}
		}
		
	}
	
	public void connect() {
		// ������ ����
		try {
			sc = new Socket(host, port);
			taMsg.setText("������ ���� �Ͽ����ϴ�.!!!\n");
			
			Thread thread = new Thread(this);
			thread.start();
			
			btnConnect.setEnabled(false);
		} catch(IOException e) {
			sc = null;
			taMsg.setText("���� ������ �����߽��ϴ�.!!!\n");
			
			System.out.println(e.toString());
		}
	}

	public static void main(String[] args){
		new ChatClient();
	}
}

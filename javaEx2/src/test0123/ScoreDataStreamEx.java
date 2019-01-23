package test0123;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ScoreDataStreamEx {

	public static void main(String[] args) {
		ScoreProcess ss = new ScoreProcess();
		char ch;
		
		try {
			while (true) {
				do {
					System.out.println("1.�Է� 2.��� 3.���� =>");
					ch = (char)System.in.read();
					
					System.in.skip(2);
				} while(ch < '1' || ch > '3');
				
				if (ch == '3') {
					break;
				}
				
				switch (ch) {
				case '1' : ss.wrtieFile(); break;
				case '2' : ss.readFile(); break;
				}
			}
		} catch (Exception e) {
			
		}

	}

}

class ScoreProcess {
	private String pathname = "score.txt";
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public void readFile() {
		File f = new File(pathname);
		
		// ���� ������ ���� ��� ����
		if (!f.exists()) {
			return;
		}
		
		String hak, name;
		int kor, eng, mat;
		
		DataInputStream dis = null;
		
		try {
			dis = new DataInputStream(new FileInputStream(f));
			
			while(true) {
				hak = dis.readUTF();
				name = dis.readUTF();
				kor = dis.readInt();
				eng = dis.readInt();
				mat = dis.readInt();
				
				System.out.println(hak + "\t" + name + "\t" + kor + "\t" + eng + "\t" + mat);
			}
		} catch (EOFException e) {
			// DataInputStream�� �� �̻� ���Ͽ��� ���� �����Ͱ� ������EOFException ���ܰ� �߻�
		} catch (IOException e) {
			System.out.println(e.toString());
		} finally {
			if (dis != null) {
				try {
					dis.close();
				} catch (Exception e2) {
					
				}
			}
		}
	}
	
	public void wrtieFile() {
		String hak, name;
		int kor, eng, mat;
		DataOutputStream dos = null;
		
		try {
			// FileOutputStream() ������ ����� �����, ������ ���� ����
			// append
			dos = new DataOutputStream(new FileOutputStream(pathname, true));
			
			System.out.println("�й�?");
			hak = br.readLine();
			
			System.out.println("�̸�?");
			name = br.readLine();
			
			System.out.println("����?");
			kor = Integer.parseInt(br.readLine());
			
			System.out.println("����?");
			eng = Integer.parseInt(br.readLine());
			
			System.out.println("����?");
			mat = Integer.parseInt(br.readLine());
			
			dos.writeUTF(hak);
			dos.writeUTF(name);
			dos.writeInt(kor);
			dos.writeInt(eng);
			dos.writeInt(mat);
		} catch (IOException e) {
			System.out.println(e.toString());
		} catch (NumberFormatException e) {
			System.out.println(e.toString());
			System.out.println("������ ���ڸ� �Է� �����մϴ�.");
		} finally {
			if (dos != null) {
				try {
					dos.close();
				} catch (Exception e2) {

				}
			}
		}
	}
}
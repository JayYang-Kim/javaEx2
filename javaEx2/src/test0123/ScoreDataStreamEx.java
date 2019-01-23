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
					System.out.println("1.입력 2.출력 3.종료 =>");
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
		
		// 파일 내용이 없을 경우 종료
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
			// DataInputStream은 더 이상 파일에서 읽을 데이터가 없으면EOFException 예외가 발생
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
			// FileOutputStream() 있으면 지우고 만들고, 없으면 새로 생성
			// append
			dos = new DataOutputStream(new FileOutputStream(pathname, true));
			
			System.out.println("학번?");
			hak = br.readLine();
			
			System.out.println("이름?");
			name = br.readLine();
			
			System.out.println("국어?");
			kor = Integer.parseInt(br.readLine());
			
			System.out.println("영어?");
			eng = Integer.parseInt(br.readLine());
			
			System.out.println("수학?");
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
			System.out.println("점수는 숫자만 입력 가능합니다.");
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
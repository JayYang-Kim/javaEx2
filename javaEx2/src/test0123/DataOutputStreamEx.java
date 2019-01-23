package test0123;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class DataOutputStreamEx {

	public static void main(String[] args) {
		try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("ex.txt"));) {
			dos.writeUTF("대한민국");
			dos.writeInt(65);
			dos.writeChar('x');
			dos.writeUTF("서울");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		try (DataInputStream dis = new DataInputStream(new FileInputStream("ex.txt"));) {
			System.out.println(dis.readUTF());
			System.out.println(dis.readInt());
			System.out.println(dis.readChar());
			System.out.println(dis.readUTF());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}

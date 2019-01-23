package test0123;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class ObjectStreamEx {

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		
		map.put("서울", "1000만");
		map.put("경기", "1100만");
		
		String pathname = "ex.txt";
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		
		try {
			oos = new ObjectOutputStream(new FileOutputStream(pathname));
			oos.writeObject(map);
			//oos.close();
			
			ois = new ObjectInputStream(new FileInputStream(pathname));
			
			@SuppressWarnings("unchecked")
			Map<String, String> map2 = (Map<String, String>)ois.readObject();
			//ois.close();
			
			System.out.println(map2);
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
		}catch (IOException e) {
			System.out.println(e.toString());
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (Exception e2) {

				}
			}
			
			if (ois != null) {
				try {
					ois.close();
				} catch (Exception e2) {

				}
			}
		}

	}

}

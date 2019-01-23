package test0123;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

class UserVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String tel;
	private int age;
	
	public UserVO() {
		
	}
	
	public UserVO(String name, String tel, int age) {
		this.name = name;
		this.tel = tel;
		this.age = age;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}

public class ObjectStreamEx2 {

	public static void main(String[] args) {
		String pathname = "ex.txt";
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		
		try {
			oos = new ObjectOutputStream(new FileOutputStream(pathname));
			oos.writeObject(new UserVO("È«±æµ¿", "010", 25));
			oos.writeObject(new UserVO("±èÀÚ¹Ù", "010", 33));
			
			ois = new ObjectInputStream(new FileInputStream(pathname));
			
			UserVO vo;
			vo = (UserVO)ois.readObject();
			System.out.println(vo.getName() + ":" + vo.getTel() + ":" + vo.getAge());
			
			vo = (UserVO)ois.readObject();
			System.out.println(vo.getName() + ":" + vo.getTel() + ":" + vo.getAge());
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		} catch (IOException e) {
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

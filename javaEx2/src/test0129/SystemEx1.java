package test0129;

import java.util.Enumeration;
import java.util.Properties;

// �ý��ۿ� �ִ� ������ �˾Ƴ���
public class SystemEx1 {
	public static void main(String[] args) {

		Properties p = System.getProperties(); // HashTable(�θ�) => Map
		
		Enumeration<?> e = p.propertyNames(); // ? : �̷��� ĳ������ �ʿ䰡 ���� �� ����ǥ <?> ���
		// ���ʸ� ĳ���� �Ұ� (���� ��ġ�ؾ��Ѵ�)
		// �⺻�� �ڷ��� �״�� ����Ѵ�
		
		 while(e.hasMoreElements()) {
			 String key = (String) e.nextElement(); // object���̶� �ٿ�ĳ����
			 String value = p.getProperty(key);
			 System.out.println(key + " : " + value);
		 
		 }
	}
}

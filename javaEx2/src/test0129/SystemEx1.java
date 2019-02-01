package test0129;

import java.util.Enumeration;
import java.util.Properties;

// 시스템에 있는 정보를 알아낸다
public class SystemEx1 {
	public static void main(String[] args) {

		Properties p = System.getProperties(); // HashTable(부모) => Map
		
		Enumeration<?> e = p.propertyNames(); // ? : 미래에 캐스팅할 필요가 없을 때 물음표 <?> 사용
		// 제너릭 캐스팅 불가 (형이 일치해야한다)
		// 기본은 자료형 그대로 써야한다
		
		 while(e.hasMoreElements()) {
			 String key = (String) e.nextElement(); // object형이라 다운캐스팅
			 String value = p.getProperty(key);
			 System.out.println(key + " : " + value);
		 
		 }
	}
}

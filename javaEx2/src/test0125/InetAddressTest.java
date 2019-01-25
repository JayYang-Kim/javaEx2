package test0125;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class InetAddressTest {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String host;
		
		try {
			System.out.println("호스트 [www.naver.com]?");
			host = br.readLine();
			
			InetAddress ob = InetAddress.getByName(host);
			
			String ip = ob.getHostAddress();
			String hostName = ob.getHostName();
			
			System.out.println("호스트명 : " + hostName);
			System.out.println("아이피 : " + ip);
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

}

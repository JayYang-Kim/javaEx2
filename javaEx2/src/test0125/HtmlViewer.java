package test0125;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HtmlViewer {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String web, s;
		
		try {
			System.out.println("웹주소 [예 : https://www.naver.com]?");
			web = br.readLine();
			
			URL url = new URL(web);
			
			InputStream is = url.openStream();
			BufferedReader wbr = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			
			while ((s = wbr.readLine()) != null) {
				System.out.println(s);
			}
			
			wbr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

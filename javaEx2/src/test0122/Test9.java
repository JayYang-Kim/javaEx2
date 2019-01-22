package test0122;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Test9 {

	public static void main(String[] args) {
		// 폴더 목록 확인
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String pathname;
		
		try {
			System.out.println("확인할 폴더명?");
			pathname = br.readLine();
			
			File f = new File(pathname);
			
			// 폴더가 있는지 체크
			if (!f.exists()) {
				System.out.println(pathname + "는 존재하지 않습니다.");
				return;
			}
			
			// 폴더인지 체크
			if (!f.isDirectory()) { // is가 들어갈 경우, boolean 자료형
				System.out.println(pathname + "폴더가 아닙니다.");
				return;
			}
			
			File []list = f.listFiles();
			
			for(File a : list) {
				if (a.isDirectory()) { // 폴더인지 체크
					System.out.println(a.getName() + "폴더");
				} else if (a.isFile()) { // 파일인지 체크
					System.out.println(a.getName() + "\t" + a.length()); // 파일명, 파일 사이즈
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

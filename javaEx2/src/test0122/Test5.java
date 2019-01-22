package test0122;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test5 {

	public static void main(String[] args) {
		String appath = System.getProperty("user.dir");
		
		//System.out.println("현재 작업 경로 : " + appath);
		
		// 폴더와 폴더 구분자(폴더와 파일 구분자) : File.separator
		String pathname = appath + File.separator + "ex.txt";
		
		try {
			File f = new File(pathname);
			
			System.out.println("파일 정보");
			
			if (!f.exists()) { // 파일 또는 폴더 존재 여부
				System.out.println(pathname + "는 존재하지 않습니다.");
				return;
			}
			
			System.out.println("파일명 : " + f.getName());
			System.out.println("파일길이 : " + f.length()); // long 자료형
			
			System.out.println("절대경로 : " + f.getAbsolutePath()); // 경로\ex.txt
			System.out.println("표준경로 : " + f.getCanonicalPath()); // 표준(추상)경로
			
			System.out.println("파일 생성일 : " + f.lastModified()); // ms 단위 (long 자료형)
			System.out.println("파일 생성일 : " + new Date(f.lastModified()));
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String s = sdf.format(new Date(f.lastModified()));
			
			System.out.println("파일 생성일 : " + s);
			
			System.out.println("파일 경로 : " + f.getParent());
			
			System.out.println("읽기속성 : " + f.canRead());
			System.out.println("쓰기속성 : " + f.canWrite());
			
			s = f.getPath();
			System.out.println("확장자 : " + s.substring(s.lastIndexOf(".") + 1, s.length()));
		} catch (Exception e) {
			
		}

	}

}

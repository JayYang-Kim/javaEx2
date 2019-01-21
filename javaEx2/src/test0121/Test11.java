package test0121;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test11 {

	public static void main(String[] args) {
		// 텍스트 파일을 읽어 들여 텍스트 파일로 복사하기(2진 파일은 안됨)
		int data;
		String source, target;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		FileReader frd = null;
		FileWriter fwt = null;
		
		try {
			System.out.println("복사할 원본 파일명?");
			source = br.readLine();
			
			System.out.println("복사할 파일명?");
			target = br.readLine();
			
			frd = new FileReader(source);
			// Reader frd = new InputStreamReader(new FileInputStream(source)):
			fwt = new FileWriter(target);
			// Writer fwt = new OutputStreamWriter(new FileOutputStream(target)):
			
			while ((data = frd.read()) != -1) {
				fwt.write(data);
			}
			
			fwt.flush();
			
			System.out.println("텍스트 파일 복사 완료");
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (frd != null) {
				try {
					frd.close();
				} catch (Exception e2) {

				}
			}
			
			if (fwt != null) {
				try {
					fwt.close();
				} catch (Exception e2) {

				}
			}
		}
	}

}

package test0130;

import java.util.regex.Pattern;

// 정규표현식 
public class PatternEx2 {
	public static void main(String[] args) {

		String s, regex;

		// c로 시작하고 t로 끝나는 모든 문자
		regex = "c.*t"; // ct cat
		s = "ct";
		if (Pattern.matches(regex, s)) {
			System.out.println(regex + "=> " + s + ":O");
		} else {
			System.out.println(regex + "=> " + s + ":X");
		}

		// 자바로 끝나는 모든 문자
		regex = ".*자바$";
		s = "아 자바";
		if (Pattern.matches(regex, s)) {
			System.out.println(regex + "=> " + s + ":O");
		} else {
			System.out.println(regex + "=> " + s + ":X");
		}

		// 1이상의 한글
		regex = "^[\uac00-\ud7a3]+$"; // 유니코드 [가-힣]
		s = "자ㅋ바";
		if (Pattern.matches(regex, s)) {
			System.out.println(regex + "=> " + s + ":O");
		} else {
			System.out.println(regex + "=> " + s + ":X");
		}

		// 주민번호
		regex = "\\d{2}[01]\\d{1}[0-3]\\d{1}-?[1-8]\\d{6}";
		s = "901010-1000001";
		if (Pattern.matches(regex, s)) {
			System.out.println(regex + "=> " + s + ":O");
		} else {
			System.out.println(regex + "=> " + s + ":X");
		}

		// 영문자로 시작하고 하나 이상의 숫자 또는 특수문자를 포함한 5~10자
		regex = "^[a-zA-Z](?=.*[~!@#$%^&*]|.*[0-9]).{4,9}$";
		s = "aa1aa";
		if (Pattern.matches(regex, s)) {
			System.out.println(regex + "=> " + s + ":O");
		} else {
			System.out.println(regex + "=> " + s + ":X");
		}

	}
}

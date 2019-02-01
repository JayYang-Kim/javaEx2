package test0130;

import java.util.regex.Pattern;

// Á¤±ÔÇ¥Çö½Ä 
public class PatternEx2 {
	public static void main(String[] args) {

		String s, regex;

		// c·Î ½ÃÀÛÇÏ°í t·Î ³¡³ª´Â ¸ðµç ¹®ÀÚ
		regex = "c.*t"; // ct cat
		s = "ct";
		if (Pattern.matches(regex, s)) {
			System.out.println(regex + "=> " + s + ":O");
		} else {
			System.out.println(regex + "=> " + s + ":X");
		}

		// ÀÚ¹Ù·Î ³¡³ª´Â ¸ðµç ¹®ÀÚ
		regex = ".*ÀÚ¹Ù$";
		s = "¾Æ ÀÚ¹Ù";
		if (Pattern.matches(regex, s)) {
			System.out.println(regex + "=> " + s + ":O");
		} else {
			System.out.println(regex + "=> " + s + ":X");
		}

		// 1ÀÌ»óÀÇ ÇÑ±Û
		regex = "^[\uac00-\ud7a3]+$"; // À¯´ÏÄÚµå [°¡-ÆR]
		s = "ÀÚ¤»¹Ù";
		if (Pattern.matches(regex, s)) {
			System.out.println(regex + "=> " + s + ":O");
		} else {
			System.out.println(regex + "=> " + s + ":X");
		}

		// ÁÖ¹Î¹øÈ£
		regex = "\\d{2}[01]\\d{1}[0-3]\\d{1}-?[1-8]\\d{6}";
		s = "901010-1000001";
		if (Pattern.matches(regex, s)) {
			System.out.println(regex + "=> " + s + ":O");
		} else {
			System.out.println(regex + "=> " + s + ":X");
		}

		// ¿µ¹®ÀÚ·Î ½ÃÀÛÇÏ°í ÇÏ³ª ÀÌ»óÀÇ ¼ýÀÚ ¶Ç´Â Æ¯¼ö¹®ÀÚ¸¦ Æ÷ÇÔÇÑ 5~10ÀÚ
		regex = "^[a-zA-Z](?=.*[~!@#$%^&*]|.*[0-9]).{4,9}$";
		s = "aa1aa";
		if (Pattern.matches(regex, s)) {
			System.out.println(regex + "=> " + s + ":O");
		} else {
			System.out.println(regex + "=> " + s + ":X");
		}

	}
}

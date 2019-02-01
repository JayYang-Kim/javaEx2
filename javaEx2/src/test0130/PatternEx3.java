package test0130;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//정규표현식을 통해 replaceAll
public class PatternEx3 {
	public static String replaceAll(String str, String oldStr, String newStr) {
		StringBuffer sb = new StringBuffer();

		if (str == null || str.length() == 0) // null하고 비교하는 일이 생기면 str == null이 반드시 앞에 올 것
			return null;

		Pattern p = Pattern.compile(oldStr);
		Matcher m = p.matcher(str);

		while (m.find()) { // 우리나라 대한 //우리 대한 //민국 // 찾는다.
			m.appendReplacement(sb, newStr); // 우리나라 대한 중 newStr로 변경하여 sb에 담는다.
			
		}
		m.appendTail(sb);

		return sb.toString();
	}

	public static void main(String[] args) {
		String s = "우리나라 대한 우리 대한민국"; // 대한을 한문 대한으로
		s = replaceAll(s, "대한", "大韓");
		System.out.println(s);
	}
}

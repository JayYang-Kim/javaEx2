package test0130;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//����ǥ������ ���� replaceAll
public class PatternEx3 {
	public static String replaceAll(String str, String oldStr, String newStr) {
		StringBuffer sb = new StringBuffer();

		if (str == null || str.length() == 0) // null�ϰ� ���ϴ� ���� ����� str == null�� �ݵ�� �տ� �� ��
			return null;

		Pattern p = Pattern.compile(oldStr);
		Matcher m = p.matcher(str);

		while (m.find()) { // �츮���� ���� //�츮 ���� //�α� // ã�´�.
			m.appendReplacement(sb, newStr); // �츮���� ���� �� newStr�� �����Ͽ� sb�� ��´�.
			
		}
		m.appendTail(sb);

		return sb.toString();
	}

	public static void main(String[] args) {
		String s = "�츮���� ���� �츮 ���ѹα�"; // ������ �ѹ� ��������
		s = replaceAll(s, "����", "����");
		System.out.println(s);
	}
}

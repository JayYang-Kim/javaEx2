package test0130;

import java.util.regex.Pattern;

// ����ǥ���� 
public class PatternEx2 {
	public static void main(String[] args) {

		String s, regex;

		// c�� �����ϰ� t�� ������ ��� ����
		regex = "c.*t"; // ct cat
		s = "ct";
		if (Pattern.matches(regex, s)) {
			System.out.println(regex + "=> " + s + ":O");
		} else {
			System.out.println(regex + "=> " + s + ":X");
		}

		// �ڹٷ� ������ ��� ����
		regex = ".*�ڹ�$";
		s = "�� �ڹ�";
		if (Pattern.matches(regex, s)) {
			System.out.println(regex + "=> " + s + ":O");
		} else {
			System.out.println(regex + "=> " + s + ":X");
		}

		// 1�̻��� �ѱ�
		regex = "^[\uac00-\ud7a3]+$"; // �����ڵ� [��-�R]
		s = "�ڤ���";
		if (Pattern.matches(regex, s)) {
			System.out.println(regex + "=> " + s + ":O");
		} else {
			System.out.println(regex + "=> " + s + ":X");
		}

		// �ֹι�ȣ
		regex = "\\d{2}[01]\\d{1}[0-3]\\d{1}-?[1-8]\\d{6}";
		s = "901010-1000001";
		if (Pattern.matches(regex, s)) {
			System.out.println(regex + "=> " + s + ":O");
		} else {
			System.out.println(regex + "=> " + s + ":X");
		}

		// �����ڷ� �����ϰ� �ϳ� �̻��� ���� �Ǵ� Ư�����ڸ� ������ 5~10��
		regex = "^[a-zA-Z](?=.*[~!@#$%^&*]|.*[0-9]).{4,9}$";
		s = "aa1aa";
		if (Pattern.matches(regex, s)) {
			System.out.println(regex + "=> " + s + ":O");
		} else {
			System.out.println(regex + "=> " + s + ":X");
		}

	}
}

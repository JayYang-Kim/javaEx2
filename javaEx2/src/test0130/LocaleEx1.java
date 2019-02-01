package test0130;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

public class LocaleEx1 {
	public static void main(String[] args) {

		String s = Locale.getDefault().getDisplayName(); // getDefault() : locale��ü ����
		Locale l = Locale.getDefault();

		Locale l2 = new Locale("ko");
		Locale l3 = new Locale("ko", "KR");
		Locale l4 = new Locale("en", "US");

		System.out.println(s);
		System.out.println(l);
		System.out.println(l2);
		System.out.println(l3);
		System.out.println(l4);

		Calendar cal = Calendar.getInstance();
		s = String.format(l4, "%tF %tT", cal, cal); // ������ ����
		System.out.println(s);

		// ���� (�ȵ���̵� ���� ���� �� �ʿ�)
		s = String.format("%f", 3.14);
		System.out.println(s); // 3.140000

		s = String.format(Locale.FRANCE, "%f", 3.14);
		System.out.println(s); // 3,140000 �������� �Ҽ����� �޸� ���
		
		s = String.format(Locale.KOREA, "%f", 3.14);
		System.out.println(s); // 3.140000
		// ����

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", java.util.Locale.KOREA);
		// SimpleDateFormat locale ���� �� ����
		s = sdf.format(new Date());
		System.out.println(s);

		// locate����
		System.out.println("������ ���....(������ ����)");
		Locale list[] = SimpleDateFormat.getAvailableLocales();
		Set<String> set = new TreeSet<>();
		for (int i = 0; i < list.length; i++) {
			set.add(list[i].getDisplayName() + "\t\t\t" + list[i].toString());
		}

		for (String ss : set)
			System.out.println(ss);
	}

}

package net_ochat;

import java.io.Serializable;

//����, Ŭ���̾�Ʈ ����

// ��ü����ȭ
/*
 * ��ü ����ȭ�� �޸𸮿� ������ Ŭ���� ��ü�� ��� ������ ���� ���¸� �״�� ����
 * �켭 ���Ͽ� �����ϰų� ��Ʈ��ũ�� ���� ������ �� �ִ� ���
 */

/*
 * 100  client -> server : �α��� ��û
 *  
 * 110  server -> client : �α��� ����
 * 111  server -> client : �α��� ����� �ٸ� Ŭ���̾�Ʈ���� �˸�
 * 120  server -> client : �α��� ����(�г��� �ߺ�����)
 * 201  server -> client : �α׾ƿ� ����� �ٸ� Ŭ���̾�Ʈ���� �˸�
 */

public class Login implements Serializable { // ����ȭ
	private static final long serialVersionUID = 1L; // static�� ����ȭ���� ����
	
	private int code;
	private String userName;
	
	// �޼���� ����ȭ���� ����
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}

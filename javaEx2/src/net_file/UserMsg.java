package net_file;

import java.io.Serializable;

/*
 * code : 100 -> ���ϸ�
 * code : 200 -> ���ϱ���
 * code : 300 -> ���ϳ���
 */

public class UserMsg implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int code;
	private byte[] data=new byte[512];
	private int len;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public int getLen() {
		return len;
	}
	public void setLen(int len) {
		this.len = len;
	}
}

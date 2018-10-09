package com.hq.storeMS.service.activateCode.apiData;

public class ActivateCodeGenApiForm {
	// 随机生成激活码的个数
	private int count;
	// 源字符串
	private String source="qwertyuiopasdfghjklzxcvbnm0123456789";
	// 长度
	private int len;

	public static ActivateCodeGenApiForm newInstance() {
		return new ActivateCodeGenApiForm();
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}
}

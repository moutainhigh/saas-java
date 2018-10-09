package com.hq.thirdPartyServer.service.common;

public class RestResp<T> {

	private int code;

	private String tJson;

	private String tips;

	private String tListJson;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String gettJson() {
		return tJson;
	}

	public void settJson(String tJson) {
		this.tJson = tJson;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public String gettListJson() {
		return tListJson;
	}

	public void settListJson(String tListJson) {
		this.tListJson = tListJson;
	}

}

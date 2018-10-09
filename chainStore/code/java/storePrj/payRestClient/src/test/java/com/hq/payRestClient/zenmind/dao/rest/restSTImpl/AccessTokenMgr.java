package com.hq.payRestClient.zenmind.dao.rest.restSTImpl;

import com.hq.payRestClient.common.validate.info.ValidateInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class AccessTokenMgr {

	public static AccessTokenMgr getInstance() {
		return HotSwap.getInstance().getSingleton(AccessTokenMgr.class);
	}

	private String token = "";
	private long buserId = 0;
	private ValidateInfo validateInfo;

	public String getToken() {
		return this.token;
	}

	public void putToken(String token) {
		this.token = token;
	}

	public ValidateInfo getValidateInfo() {
		return this.validateInfo;
	}

	public void putValidateInfo(ValidateInfo info) {
		this.validateInfo = info;
	}

	public void setOpIdTL(Long buserId) {
		this.buserId = buserId;
	}

	public long getOpIdTL() {
		return this.buserId;
	}

	public void removeOpIdTL() {
		token = "";
		buserId = 0;
		validateInfo = null;
	}
}

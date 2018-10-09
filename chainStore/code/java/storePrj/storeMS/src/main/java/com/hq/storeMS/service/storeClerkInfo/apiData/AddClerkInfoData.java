package com.hq.storeMS.service.storeClerkInfo.apiData;

public class AddClerkInfoData {
	private long storeId;
	private long bUserId;

	private String verifyCode;// 验证码

	public static AddClerkInfoData newInstance() {
		return new AddClerkInfoData();
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getbUserId() {
		return bUserId;
	}

	public void setbUserId(long bUserId) {
		this.bUserId = bUserId;
	}
	
	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

}

package com.hq.storeMS.service.storeVip.data;

public class StoreVip {
	
	private boolean success;
	private String tips;

	public static StoreVip newInstance(){
		return new StoreVip();
	}

	public boolean isSuccess() {
		return success;
	}

	public StoreVip setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public String getTips() {
		return tips;
	}

	public StoreVip setTips(String tips) {
		this.tips = tips;
		return this;
	}
	
}

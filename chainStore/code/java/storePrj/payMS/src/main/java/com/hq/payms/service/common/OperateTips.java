package com.hq.payms.service.common;

public class OperateTips {
	private boolean success;
	private String tips;

	public static OperateTips newInstance() {
		return newInstance(true, "成功") ;
	}
	
	public static OperateTips newInstance(boolean success, String tips) {
		OperateTips operateTips = new OperateTips();
		operateTips.success = success;
		operateTips.tips = tips;
		return operateTips;
	}
	
	public static OperateTips newInstance(boolean success) {
		return newInstance(success, "") ;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
		if(success){
			this.tips="成功";
		}
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

}

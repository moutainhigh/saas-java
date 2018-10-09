package com.hq.chainStore.service.leaguerDetail.data;

public class MemberCardExist {
	private boolean exist;
	private String tips;
	
	public static MemberCardExist newInstance(boolean existP) {
		return new MemberCardExist();
	}
	public boolean isExist() {
		return exist;
	}
	public void setExist(boolean exist) {
		this.exist = exist;
	}
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
}

package com.hq.storeMS.service.specialData.apiData;


public class CUserSpecialData {
	private Long cuserId;

	public static CUserSpecialData newInstance() {
		return new CUserSpecialData();
	}

	public Long getCuserId() {
		return cuserId;
	}

	public void setCuserId(Long cuserId) {
		this.cuserId = cuserId;
	}


}

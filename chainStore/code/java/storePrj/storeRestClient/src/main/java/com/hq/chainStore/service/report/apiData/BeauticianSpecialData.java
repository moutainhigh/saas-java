package com.hq.chainStore.service.report.apiData;


public class BeauticianSpecialData {
	private Long beauticianId;

	public static BeauticianSpecialData newInstance() {
		return new BeauticianSpecialData();
	}

	public Long getBeauticianId() {
		return beauticianId;
	}

	public void setBeauticianId(Long beauticianId) {
		this.beauticianId = beauticianId;
	}

}

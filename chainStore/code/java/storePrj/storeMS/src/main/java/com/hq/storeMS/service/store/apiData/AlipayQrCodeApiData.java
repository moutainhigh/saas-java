package com.hq.storeMS.service.store.apiData;

public class AlipayQrCodeApiData {
	private long storeId;
	private String alipayImg;

	public static AlipayQrCodeApiData newInstance() {
		return new AlipayQrCodeApiData();
	}

	public String getAlipayImg() {
		return alipayImg;
	}

	public void setAlipayImg(String alipayImg) {
		this.alipayImg = alipayImg;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

}

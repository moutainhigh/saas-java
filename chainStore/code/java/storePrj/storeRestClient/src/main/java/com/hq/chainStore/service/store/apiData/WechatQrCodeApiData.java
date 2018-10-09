package com.hq.chainStore.service.store.apiData;

public class WechatQrCodeApiData {
	private long storeId;
	private String wechatImg;

	public static WechatQrCodeApiData newInstance() {
		return new WechatQrCodeApiData();
	}

	public String getWechatImg() {
		return wechatImg;
	}

	public void setWechatImg(String wechatImg) {
		this.wechatImg = wechatImg;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}


}

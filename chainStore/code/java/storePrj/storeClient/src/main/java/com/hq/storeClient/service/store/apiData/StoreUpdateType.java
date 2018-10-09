package com.hq.storeClient.service.store.apiData;

public enum StoreUpdateType {
	UpdateStoreInfo("修改店铺信息"),
	Submit4Check("提交店铺"),
	JoinStoreQrCode("生成加入店铺二维码"),
	AlipayQrCodeApiData("上传支付宝二维码"),
	WechatQrCodeApiData("上传微信二维码"),
	StoreUpdateStatusData("更新状态"),
	StoreUpdateChainData("店铺加盟连锁店"),
	;
	
	private String descript;
	
	private StoreUpdateType(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public static StoreUpdateType valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

	
}

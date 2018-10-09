package com.hq.chainStore.service.store.apiData;


public class StoreUpdateApiForm {

	private int updateType;

	private UpdateStoreInfoApiData updateStoreInfoApiData;

	private Submit4CheckApiData submit4CheckApiData;

	private JoinStoreQrCodeApiData joinStoreQrCodeApiData;

	private AlipayQrCodeApiData alipayQrCodeApiData;

	private WechatQrCodeApiData wechatQrCodeApiData;
	
	private StoreUpdateStatusData updateStatusData;

	public static StoreUpdateApiForm newInstance() {
		return new StoreUpdateApiForm();
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public void setUpdateTypeEnum(StoreUpdateType updateType) {
		this.updateType = updateType.ordinal();
	}

	public UpdateStoreInfoApiData getUpdateStoreInfoApiData() {
		return updateStoreInfoApiData;
	}

	public void setUpdateStoreInfoApiData(
			UpdateStoreInfoApiData updateStoreInfoApiData) {
		this.updateStoreInfoApiData = updateStoreInfoApiData;
	}

	public Submit4CheckApiData getSubmit4CheckApiData() {
		return submit4CheckApiData;
	}

	public void setSubmit4CheckApiData(Submit4CheckApiData submit4CheckApiData) {
		this.submit4CheckApiData = submit4CheckApiData;
	}

	public JoinStoreQrCodeApiData getJoinStoreQrCodeApiData() {
		return joinStoreQrCodeApiData;
	}

	public void setJoinStoreQrCodeApiData(
			JoinStoreQrCodeApiData joinStoreQrCodeApiData) {
		this.joinStoreQrCodeApiData = joinStoreQrCodeApiData;
	}

	public AlipayQrCodeApiData getAlipayQrCodeApiData() {
		return alipayQrCodeApiData;
	}

	public void setAlipayQrCodeApiData(AlipayQrCodeApiData alipayQrCodeApiData) {
		this.alipayQrCodeApiData = alipayQrCodeApiData;
	}

	public WechatQrCodeApiData getWechatQrCodeApiData() {
		return wechatQrCodeApiData;
	}

	public void setWechatQrCodeApiData(WechatQrCodeApiData wechatQrCodeApiData) {
		this.wechatQrCodeApiData = wechatQrCodeApiData;
	}

	public StoreUpdateStatusData getUpdateStatusData() {
		return updateStatusData;
	}

	public void setUpdateStatusData(StoreUpdateStatusData updateStatusData) {
		this.updateStatusData = updateStatusData;
	}

}

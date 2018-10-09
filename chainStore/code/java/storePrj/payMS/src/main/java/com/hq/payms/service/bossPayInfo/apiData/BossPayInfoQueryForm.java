package com.hq.payms.service.bossPayInfo.apiData;

import com.zenmind.common.StringFormatUtil;

public class BossPayInfoQueryForm {
	
	private long storeId;
	
	/* 微信支付(普通商户模式) */
	private String wxpayAppId; //appId
	private String wxpayMchId; //商户号
	private String wxpayKey; //API密钥
	private String wxpayCertPath; //商户证书
	
	/* 支付宝 */
	private String alipayAppId; // appId
	private String alipayPrivateKey; //应用私钥
	private String alipayDevPublicKey; //应用公钥
	private String alipayZfbPublicKey; //支付宝公钥
	
	private int pageItemCount;
	private int pageNo;
	
	public static BossPayInfoQueryForm newInstance() {
		BossPayInfoQueryForm params = new BossPayInfoQueryForm();
		params.pageItemCount = 20;
		params.pageNo = 1;
		return params;
	}
	
	public String getListId() {
		String format = "{}_{}_{}_{}_{}";
		return StringFormatUtil.format(format, storeId, wxpayAppId, alipayAppId, pageItemCount, pageNo);
	}
	
	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getAlipayAppId() {
		return alipayAppId;
	}

	public void setAlipayAppId(String alipayAppId) {
		this.alipayAppId = alipayAppId;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public void setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getWxpayAppId() {
		return wxpayAppId;
	}

	public void setWxpayAppId(String wxpayAppId) {
		this.wxpayAppId = wxpayAppId;
	}

	public String getWxpayMchId() {
		return wxpayMchId;
	}

	public void setWxpayMchId(String wxpayMchId) {
		this.wxpayMchId = wxpayMchId;
	}

	public String getWxpayKey() {
		return wxpayKey;
	}

	public void setWxpayKey(String wxpayKey) {
		this.wxpayKey = wxpayKey;
	}

	public String getWxpayCertPath() {
		return wxpayCertPath;
	}

	public void setWxpayCertPath(String wxpayCertPath) {
		this.wxpayCertPath = wxpayCertPath;
	}

	public String getAlipayPrivateKey() {
		return alipayPrivateKey;
	}

	public void setAlipayPrivateKey(String alipayPrivateKey) {
		this.alipayPrivateKey = alipayPrivateKey;
	}

	public String getAlipayDevPublicKey() {
		return alipayDevPublicKey;
	}

	public void setAlipayDevPublicKey(String alipayDevPublicKey) {
		this.alipayDevPublicKey = alipayDevPublicKey;
	}

	public String getAlipayZfbPublicKey() {
		return alipayZfbPublicKey;
	}

	public void setAlipayZfbPublicKey(String alipayZfbPublicKey) {
		this.alipayZfbPublicKey = alipayZfbPublicKey;
	}
	
	
}

package com.hq.payRestClient.service.bossPayInfo.apiData;

import com.hq.payRestClient.service.bossPayInfo.data.BossPayInfo;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class BossPayInfoAddApiForm {
	
	private long storeId;
	
	private int wxpayModel; //微信支付的模式，WxpayModelEnum
	
	/*** 微信支付(普通商户模式下的各商户信息) ***/
	private String wxpayAppId; //appId
	private String wxpayMchId; //商户号
	private String wxpayKey; //API密钥
	private String wxpayCertPath; //商户证书
	
	/*** 微信支付(服务商模式下的各子商户信息) ***/
	private String wxpaySubMchId; //微信支付分配的子商户号
	
	/*** 支付宝 ***/
	private String alipayAppId; // appId
	private String alipayPrivateKey; //应用私钥
	private String alipayDevPublicKey; //应用公钥
	private String alipayZfbPublicKey; //支付宝公钥
	
	public BossPayInfo toBossPayInfo() {
		BossPayInfo target = BossPayInfo.newInstance();
		FastBeanCopyer.getInstance().copy(this, target);
		return target;
	}

	public String getAlipayAppId() {
		return alipayAppId;
	}

	public void setAlipayAppId(String alipayAppId) {
		this.alipayAppId = alipayAppId;
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


	public long getStoreId() {
		return storeId;
	}


	public void setStoreId(long storeId) {
		this.storeId = storeId;
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

	public int getWxpayModel() {
		return wxpayModel;
	}

	public void setWxpayModel(int wxpayModel) {
		this.wxpayModel = wxpayModel;
	}

	public String getWxpaySubMchId() {
		return wxpaySubMchId;
	}

	public void setWxpaySubMchId(String wxpaySubMchId) {
		this.wxpaySubMchId = wxpaySubMchId;
	}
}

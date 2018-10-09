package com.hq.payms.service.bossPayInfo.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 存储各个商户的微信、支付宝的商户号、appid、密钥等信息 
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
@SynClass
@Table(name = "bossPayInfo")
public class BossPayInfo {
	
	@Id
	private long id;
	
	private long storeId;
	
	private int wxpayModel; //微信支付的模式，WxpayModelEnum
	
	/*** 微信支付(普通商户模式下的各商户信息) ***/
	private String wxpayAppId; //appId(此appId为主体公众号的)
	private String wxpayMchId; //商户号
	private String wxpayKey; //API密钥
	private String wxpayCertPath; //商户证书
	
	/*** 微信支付(服务商模式下的各子商户信息) ***/
	private String wxpaySubAppId; //微信分配的子商户公众账号ID(目前没用到)
	private String wxpaySubMchId; //微信支付分配的子商户号
	
	/*** 支付宝 ***/
	private String alipayAppId; // appId
	private String alipayPrivateKey; //应用私钥
	private String alipayDevPublicKey; //应用公钥
	private String alipayZfbPublicKey; //支付宝公钥
	
	private long createdTime; // 创建时间
	private long lastUpdateTime; // 最后修改时间
	private int ver; // 版本
	
	public static BossPayInfo newInstance() {
		BossPayInfo instance = new BossPayInfo();
		long curTime = System.currentTimeMillis();
		instance.createdTime = curTime;
		instance.lastUpdateTime = curTime;
		return instance;
	}

	public void incrVer() {
		this.ver = ver + 1;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getAlipayZfbPublicKey() {
		return alipayZfbPublicKey;
	}

	public void setAlipayZfbPublicKey(String alipayZfbPublicKey) {
		this.alipayZfbPublicKey = alipayZfbPublicKey;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public int getVer() {
		return ver;
	}

	public void setVer(int ver) {
		this.ver = ver;
	}

	public String getAlipayDevPublicKey() {
		return alipayDevPublicKey;
	}

	public void setAlipayDevPublicKey(String alipayDevPublicKey) {
		this.alipayDevPublicKey = alipayDevPublicKey;
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

	public String getWxpaySubAppId() {
		return wxpaySubAppId;
	}

	public void setWxpaySubAppId(String wxpaySubAppId) {
		this.wxpaySubAppId = wxpaySubAppId;
	}

	public String getWxpaySubMchId() {
		return wxpaySubMchId;
	}

	public void setWxpaySubMchId(String wxpaySubMchId) {
		this.wxpaySubMchId = wxpaySubMchId;
	}

	public int getWxpayModel() {
		return wxpayModel;
	}

	public void setWxpayModel(int wxpayModel) {
		this.wxpayModel = wxpayModel;
	}
	
}

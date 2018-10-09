package com.hq.payms.service.wxpay.data;

/**
 * 公共的请求参数 
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class CommonReq {
	protected String appid; //公众账号ID；小程序支付时是小程序的AppId
	protected String mch_id; //商户号
	protected String nonce_str; //微信sdk已处理
	protected String sign; //保证配置文件中api密钥不为空即可，微信sdk已处理
	protected String sign_type; //微信sdk已处理
	
	
	/*** 为区分当前支付模式的临时参数，正式发送请求前会清除 ***/
	protected int wxpayModel; //微信支付的模式，WxpayModelEnum
	
	/*** 普通商户模式，为支持动态切换普通商户而增加的两个临时参数, 正式发送请求前会清除 ***/
	protected String key; //商户API秘钥
	protected String cert_path; //商户证书
	
	/*** 服务商模式，子商户的额外参数 ***/
	protected String sub_mch_id; //子商户号(数据库中获取)
	protected String sub_appid; //非必填；子商户公众账号ID(数据库中获取)

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSub_mch_id() {
		return sub_mch_id;
	}

	public void setSub_mch_id(String sub_mch_id) {
		this.sub_mch_id = sub_mch_id;
	}

	public String getSub_appid() {
		return sub_appid;
	}

	public void setSub_appid(String sub_appid) {
		this.sub_appid = sub_appid;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCert_path() {
		return cert_path;
	}

	public void setCert_path(String cert_path) {
		this.cert_path = cert_path;
	}

	public int getWxpayModel() {
		return wxpayModel;
	}

	public void setWxpayModel(int wxpayModel) {
		this.wxpayModel = wxpayModel;
	}
	
}

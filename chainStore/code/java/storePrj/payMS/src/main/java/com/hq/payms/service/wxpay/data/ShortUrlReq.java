package com.hq.payms.service.wxpay.data;

/**
 * 长url转短url请求参数 
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class ShortUrlReq extends CommonReq{
	private String long_url ; //URL链接
	
	private String sub_appid; //非必填; 子商户公众账号ID

	public static ShortUrlReq newInstance() {
		ShortUrlReq instance = new ShortUrlReq();
		return instance;
	}
	
	public String getLong_url() {
		return long_url;
	}

	public void setLong_url(String long_url) {
		this.long_url = long_url;
	}

	public String getSub_appid() {
		return sub_appid;
	}

	public void setSub_appid(String sub_appid) {
		this.sub_appid = sub_appid;
	}
	
	
}

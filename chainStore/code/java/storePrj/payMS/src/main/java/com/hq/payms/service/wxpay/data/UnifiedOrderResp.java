package com.hq.payms.service.wxpay.data;
/**
 * 统一下单返回参数
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class UnifiedOrderResp extends CommonResp{  
    
    private String device_info;             //设备号  
    private String trade_type;              //交易类型  
    private String prepay_id;               //预支付交易会话标识; 微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时
    private String code_url;                //二维码链接  
    
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getPrepay_id() {
		return prepay_id;
	}
	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}
	public String getCode_url() {
		return code_url;
	}
	public void setCode_url(String code_url) {
		this.code_url = code_url;
	}
	
	@Override
	public String toString() {
		return super.toString() + "UnifiedOrderResp [return_code=" + return_code + ", return_msg=" + return_msg + ", appid=" + appid
				+ ", mch_id=" + mch_id + ", sub_appid=" + sub_appid + ", sub_mch_id=" + sub_mch_id + ", device_info="
				+ device_info + ", nonce_str=" + nonce_str + ", sign=" + sign + ", result_code=" + result_code
				+ ", err_code=" + err_code + ", err_code_des=" + err_code_des + ", trade_type=" + trade_type
				+ ", prepay_id=" + prepay_id + ", code_url=" + code_url + "]";
	}
    
}
package com.hq.payms.service.wxpay.data;

import com.hq.payms.common.config.PayMSCfgMgr;
import com.hq.payms.common.utils.CalendarUtil;

/**
 * 刷卡支付参数 
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class MicroPayReq extends CommonReq {
	
	private String body; //商品描述,例如:"智美通-购买消费"或"智美通-会员充值"
	private String total_fee; //订单总金额，只能为整数;单位为"分"
	private String out_trade_no; //商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一
	private String auth_code; //扫码支付授权码，设备读取用户微信中的条码或者二维码信息（注：用户刷卡条形码规则：18位纯数字，以10、11、12、13、14、15开头）
	
	private String spbill_create_ip; //APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
	
	private String device_info; //非必填;终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
	private String time_expire; //非必填；本次支付失效时间; 格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。注意：最短失效时间间隔需大于1分钟

	public static MicroPayReq newInstance() {
		MicroPayReq instance = new MicroPayReq();
		int wxpayTimeExpire = PayMSCfgMgr.getProp().getZmWXPayCfg().getWxpayTimeExpire();
		instance.time_expire = CalendarUtil.addMinutes2NowTime(wxpayTimeExpire / 60, "yyyyMMddHHmmss");
		return instance;
	}
	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getAuth_code() {
		return auth_code;
	}

	public void setAuth_code(String auth_code) {
		this.auth_code = auth_code;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}
	
	

}

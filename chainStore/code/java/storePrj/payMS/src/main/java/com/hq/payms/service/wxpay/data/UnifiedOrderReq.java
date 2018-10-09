package com.hq.payms.service.wxpay.data;

import com.hq.payms.common.config.PayMSCfgMgr;
import com.hq.payms.common.utils.CalendarUtil;
import com.hq.payms.zenmind.zmWxpay.common.ZmWXPayConstants;

/**
 * 统一下单（扫码支付）请求参数
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class UnifiedOrderReq extends CommonReq implements IntfNotifiableReq{
	
	private String notify_url; //接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数(配置文件中获取)
	private String trade_type; //JSAPI 公众号支付; NATIVE 扫码支付; APP APP支付
	private String spbill_create_ip; //APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
	
	private String body; //商品描述,例如:"智美通-购买消费"或"智美通-会员充值"
	private String total_fee; //订单总金额，只能为整数;单位为"分"
	private String out_trade_no; //商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一
	private String product_id; //trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。

	private String time_expire; //非必填；本次支付失效时间; 格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。注意：最短失效时间间隔需大于1分钟
	
	private String device_info; //非必填;终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
	private String detail; //非必填；商品详细描述，对于使用单品优惠的商户，改字段必须按照规范上传
	private String attach; //非必填；附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
	private String fee_type; //非必填；目前只支持CNY；符合ISO 4217标准的三位字母代码，默认人民币：CNY
	private String time_start; //非必填
	private String goods_tag; //非必填
	private String limit_pay; //非必填；no_credit--指定不能使用信用卡支付
	private String openid;  //非必填；trade_type=JSAPI，此参数必传，用户在主商户appid下的唯一标识。openid和sub_openid可以选传其中之一，如果选择传sub_openid,则必须传sub_appid。
	private String sub_openid; //非必填；trade_type=JSAPI，此参数必传，用户在主商户appid下的唯一标识。openid和sub_openid可以选传其中之一，如果选择传sub_openid,则必须传sub_appid。
	private String scene_info; //非必填; 该字段用于上报场景信息，目前支持上报实际门店信息。该字段为JSON对象数据，对象格式为{"store_info":{"id": "门店ID","name": "名称","area_code": "编码","address": "地址" }}
	
	public static UnifiedOrderReq newInstance() {
		UnifiedOrderReq instance = new UnifiedOrderReq();
		instance.trade_type = ZmWXPayConstants.TRADE_TYPE_NATIVE;
		int wxpayTimeExpire = PayMSCfgMgr.getProp().getZmWXPayCfg().getWxpayTimeExpire();
		instance.time_expire = CalendarUtil.addMinutes2NowTime(wxpayTimeExpire / 60, "yyyyMMddHHmmss");
		return instance;
	}


	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
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

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}

	public String getGoods_tag() {
		return goods_tag;
	}

	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}

	public String getLimit_pay() {
		return limit_pay;
	}

	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}


	public String getSub_openid() {
		return sub_openid;
	}

	public void setSub_openid(String sub_openid) {
		this.sub_openid = sub_openid;
	}

	public String getScene_info() {
		return scene_info;
	}

	public void setScene_info(String scene_info) {
		this.scene_info = scene_info;
	}
	
}

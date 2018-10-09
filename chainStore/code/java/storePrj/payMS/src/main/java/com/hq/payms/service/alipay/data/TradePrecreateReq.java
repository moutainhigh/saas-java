package com.hq.payms.service.alipay.data;

import java.util.ArrayList;
import java.util.List;

import com.alipay.demo.trade.model.GoodsDetail;
import com.hq.payms.common.config.PayMSCfgMgr;

public class TradePrecreateReq extends CommonReq implements IntfNotifiableReq {

	// (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
	// 需保证商户系统端不能重复，建议通过数据库sequence生成，
	private String outTradeNo;

	// (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店当面付扫码消费”
	private String subject;
	
	// (必填) 订单总金额，单位为元，不能超过1亿元
	// 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
	private String totalAmount;

	//(必填) 支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
	private String notifyUrl;
	
	
	// (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
	// 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
	private String undiscountableAmount;

	// 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
	private String storeId;;
	
	// 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"
	private String body;

	// 商户操作员编号，添加此参数可以为商户操作员做销售统计
	private String operatorId;;

	// 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。
	private String timeoutExpress;

	// 商品明细列表，需填写购买商品详细信息，
	List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();

	
	public static TradePrecreateReq newInstance() {
		TradePrecreateReq instance = new TradePrecreateReq();
		instance.timeoutExpress = PayMSCfgMgr.getProp().getZmAlipayCfg().getAlipayTimeoutExpress() / 60 + "m";
		return instance;
	}
	
	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getUndiscountableAmount() {
		return undiscountableAmount;
	}

	public void setUndiscountableAmount(String undiscountableAmount) {
		this.undiscountableAmount = undiscountableAmount;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getTimeoutExpress() {
		return timeoutExpress;
	}

	public void setTimeoutExpress(String timeoutExpress) {
		this.timeoutExpress = timeoutExpress;
	}

	public List<GoodsDetail> getGoodsDetailList() {
		return goodsDetailList;
	}

	public void setGoodsDetailList(List<GoodsDetail> goodsDetailList) {
		this.goodsDetailList = goodsDetailList;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

}

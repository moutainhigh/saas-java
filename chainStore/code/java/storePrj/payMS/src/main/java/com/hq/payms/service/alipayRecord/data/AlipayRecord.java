package com.hq.payms.service.alipayRecord.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.alipay.demo.trade.model.result.AlipayF2FPayResult;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.hq.payms.service.alipay.apiData.TradePayApiForm;
import com.hq.payms.service.alipay.apiData.TradePrecreateApiForm;
import com.hq.payms.service.alipay.data.TradePayReq;
import com.hq.payms.service.alipay.data.TradePrecreateReq;
import com.hq.payms.zenmind.zmAlipay.common.ZmAlipayConstants;
import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 支付宝支付记录 
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
@SynClass
@Table(name = "alipayRecord")
public class AlipayRecord {
	@Id
	private long id;
	//店铺id
	private long storeId;
	//订单号
	private long orderId;
	//订单号来源类型，用来区分如何回调；OrderOriginTypeEnum
	private int orderOriginType;
	//商户交易号，64个字符以内，只能包含字母、数字、下划线；需保证商户系统端不能重复，建议通过数据库sequence生成，
	private String outTradeNo;
	//订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店当面付扫码消费”
	private String subject;
	//订单总金额，单位为元，不能超过1亿元；如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
	private String totalAmount;
	//支付宝交易号
	private String tradeNo; 
	//支付成功时间
	private long paySuccessTime; 
	//交易状态: WAIT_BUYER_PAY（交易创建，等待买家付款）、TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）、TRADE_SUCCESS（交易支付成功）、TRADE_FINISHED（交易结束，不可退款）
	private String tradeStatus;
	//支付类型: AlipayTypeEnum
	private int payType; 
	//退款金额
	private String refundAmount; 
	/*
	 *  (可选，需要支持重复退货时必填) 商户退款请求号，相同支付宝交易号下的不同退款请求号对应同一笔交易的不同退款申请，
	 *  对于相同支付宝交易号下多笔相同商户退款请求号的退款交易，支付宝只会进行一次退款
	 *  标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传。
	 */
	private String outRequestNo;
	//回调通知storeMS成功时间
	private long callbackSuccessTime; 
	//回调通知storeMS的次数
	private int callbackCount; 
	//创建时间
	private long createdTime; 
	//最后修改时间
	private long lastUpdateTime; 
	//版本
	private int ver; 

	public static AlipayRecord newInstance() {
		AlipayRecord instance = new AlipayRecord();
		long curTime = System.currentTimeMillis();
		instance.tradeStatus = ZmAlipayConstants.TS_WAIT_BUYER_PAY;
		instance.createdTime = curTime;
		instance.lastUpdateTime = curTime;
		return instance;
	}

	public static AlipayRecord fromTradePrecreate(AlipayF2FPrecreateResult result, TradePrecreateReq reqParam, 
			TradePrecreateApiForm form) {
		AlipayRecord payRecord = AlipayRecord.newInstance();
		payRecord.setStoreId(form.getStoreId());
		payRecord.setOrderId(form.getOrderId());
		payRecord.setOrderOriginType(form.getOrderOriginType());
		payRecord.setOutTradeNo(reqParam.getOutTradeNo());
		payRecord.setTotalAmount(reqParam.getTotalAmount());
		payRecord.setPayType(AlipayTypeEnum.SM.ordinal());
		return payRecord;
	}
	
	public static AlipayRecord fromTradePay(AlipayF2FPayResult result, TradePayReq reqParam, TradePayApiForm form) {
		AlipayRecord payRecord = AlipayRecord.newInstance();
		payRecord.setStoreId(form.getStoreId());
		payRecord.setOrderId(form.getOrderId());
		payRecord.setOrderOriginType(form.getOrderOriginType());
		payRecord.setOutTradeNo(reqParam.getOutTradeNo());
		payRecord.setTotalAmount(reqParam.getTotalAmount());
		payRecord.setPayType(AlipayTypeEnum.TM.ordinal());
		return payRecord;
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

	public long getPaySuccessTime() {
		return paySuccessTime;
	}

	public void setPaySuccessTime(long paySuccessTime) {
		this.paySuccessTime = paySuccessTime;
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

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getOutRequestNo() {
		return outRequestNo;
	}

	public void setOutRequestNo(String outRequestNo) {
		this.outRequestNo = outRequestNo;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getCallbackSuccessTime() {
		return callbackSuccessTime;
	}

	public void setCallbackSuccessTime(long callbackSuccessTime) {
		this.callbackSuccessTime = callbackSuccessTime;
	}

	public int getCallbackCount() {
		return callbackCount;
	}

	public void setCallbackCount(int callbackCount) {
		this.callbackCount = callbackCount;
	}

	public int getOrderOriginType() {
		return orderOriginType;
	}

	public void setOrderOriginType(int orderOriginType) {
		this.orderOriginType = orderOriginType;
	}
	
}

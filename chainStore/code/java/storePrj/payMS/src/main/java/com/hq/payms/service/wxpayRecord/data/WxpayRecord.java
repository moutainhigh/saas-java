package com.hq.payms.service.wxpayRecord.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.payms.service.wxpay.apiData.MicroPayApiForm;
import com.hq.payms.service.wxpay.apiData.UnifiedOrderApiForm;
import com.hq.payms.service.wxpay.apiData.UnifiedOrderForMiniProgramApiForm;
import com.hq.payms.service.wxpay.data.MicroPayReq;
import com.hq.payms.service.wxpay.data.MicroPayResp;
import com.hq.payms.service.wxpay.data.UnifiedOrderReq;
import com.hq.payms.service.wxpay.data.UnifiedOrderResp;
import com.hq.payms.zenmind.zmWxpay.common.ZmWXPayConstants;
import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 微信支付记录 
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
@SynClass
@Table(name = "wxpayRecord")
public class WxpayRecord {
	@Id
	private long id;
	//子商户号
	@Deprecated
	private String sub_mch_id; 
	//店铺id
	private long storeId;
	//订单号
	private long orderId;
	//订单号来源类型，用来区分如何回调；OrderOriginTypeEnum
	private int orderOriginType;
	//小程序支付时，小程序的appId
	private String miniAppId;
	//商户系统内部订单号
	private String out_trade_no; 
	//订单总金额; 单位"分"
	private String total_fee; 
	//微信支付订单号; 申请退款时要用到
	private String transaction_id; 
	//预支付交易会话标识; 微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时
	private String prepay_id; 
	//交易状态
	private String trade_state; 
	//支付成功时间
	private long paySuccessTime; 
	//支付类型: WxpayTypeEnum
	private int payType; 
	//商户退款单号
	private String out_refund_no; 
	//申请退款金额 
	private String refund_fee; 
	//退款成功时间
	private long refundSuccessTime; 
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

	public static WxpayRecord newInstance() {
		WxpayRecord instance = new WxpayRecord();
		long curTime = System.currentTimeMillis();
		instance.createdTime = curTime;
		instance.lastUpdateTime = curTime;
		instance.trade_state = ZmWXPayConstants.TS_NOTPAY;
		return instance;
	}

	public static WxpayRecord fromUnifiedOrder(UnifiedOrderResp resp, UnifiedOrderReq reqParam, UnifiedOrderApiForm form) {
		WxpayRecord payRecord = WxpayRecord.newInstance();
		payRecord.setStoreId(form.getStoreId());
		payRecord.setOrderId(form.getOrderId());
		payRecord.setOrderOriginType(form.getOrderOriginType());
		payRecord.setOut_trade_no(reqParam.getOut_trade_no());
		payRecord.setTotal_fee(reqParam.getTotal_fee());
		payRecord.setPayType(WxpayTypeEnum.SM.ordinal());
		payRecord.setPrepay_id(resp.getPrepay_id());
		return payRecord;
	}
	
	public static WxpayRecord fromUnifiedOrderForMiniProgram(UnifiedOrderResp resp, UnifiedOrderReq reqParam,
			UnifiedOrderForMiniProgramApiForm form) {
		WxpayRecord payRecord = WxpayRecord.newInstance();
		payRecord.setMiniAppId(form.getAppid());
		payRecord.setStoreId(form.getStoreId());
		payRecord.setOrderId(form.getOrderId());
		payRecord.setOrderOriginType(form.getOrderOriginType());
		payRecord.setOut_trade_no(reqParam.getOut_trade_no());
		payRecord.setTotal_fee(reqParam.getTotal_fee());
		payRecord.setPayType(WxpayTypeEnum.MINIPROGRAM.ordinal());
		payRecord.setPrepay_id(resp.getPrepay_id());
		return payRecord;
	}
	
	public static WxpayRecord fromMicroPay(MicroPayResp resp, MicroPayReq reqParam, MicroPayApiForm form) {
		WxpayRecord payRecord = WxpayRecord.newInstance();
		payRecord.setStoreId(form.getStoreId());
		payRecord.setOrderId(form.getOrderId());
		payRecord.setOrderOriginType(form.getOrderOriginType());
		payRecord.setOut_trade_no(reqParam.getOut_trade_no());
		payRecord.setTotal_fee(reqParam.getTotal_fee());
//		payRecord.setSub_mch_id(reqParam.getSub_mch_id());
		payRecord.setPayType(WxpayTypeEnum.SK.ordinal());
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

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
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

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getSub_mch_id() {
		return sub_mch_id;
	}

	public void setSub_mch_id(String sub_mch_id) {
		this.sub_mch_id = sub_mch_id;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public String getOut_refund_no() {
		return out_refund_no;
	}

	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}

	public String getRefund_fee() {
		return refund_fee;
	}

	public void setRefund_fee(String refund_fee) {
		this.refund_fee = refund_fee;
	}

	public long getRefundSuccessTime() {
		return refundSuccessTime;
	}

	public void setRefundSuccessTime(long refundSuccessTime) {
		this.refundSuccessTime = refundSuccessTime;
	}

	public String getPrepay_id() {
		return prepay_id;
	}

	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}

	public String getTrade_state() {
		return trade_state;
	}

	public void setTrade_state(String trade_state) {
		this.trade_state = trade_state;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
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

	public String getMiniAppId() {
		return miniAppId;
	}

	public void setMiniAppId(String miniAppId) {
		this.miniAppId = miniAppId;
	}

	public int getOrderOriginType() {
		return orderOriginType;
	}

	public void setOrderOriginType(int orderOriginType) {
		this.orderOriginType = orderOriginType;
	}
	
}

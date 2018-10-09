package com.hq.payms.service.wxpay.data;

/**
 * 退款请求参数 
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class RefundReq extends CommonReq implements IntfNotifiableReq{
	
	private String transaction_id; //微信订单号 
	private String out_trade_no; //商户订单号 
	private String out_refund_no; //商户退款单号
	private String total_fee; //订单金额 
	private String refund_fee; //申请退款金额 
	
	private String notify_url; //退款结果通知url
	
	public static RefundReq newInstance() {
		RefundReq instance = new RefundReq();
		return instance;
	}
	
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getOut_refund_no() {
		return out_refund_no;
	}
	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	public String getRefund_fee() {
		return refund_fee;
	}
	public void setRefund_fee(String refund_fee) {
		this.refund_fee = refund_fee;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	} 
	
	
}

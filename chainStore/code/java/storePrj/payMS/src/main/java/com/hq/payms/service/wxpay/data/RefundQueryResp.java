package com.hq.payms.service.wxpay.data;

public class RefundQueryResp extends CommonResp {
	
	private String transaction_id; //微信订单号
	private String out_trade_no; //商户订单号
	private String total_fee; //订单金额
	private String settlement_total_fee; //应结订单金额; 当订单使用了免充值型优惠券后返回该参数，应结订单金额=订单金额-免充值优惠券金额。  
	private String fee_type; //货币种类; CNY 订单金额货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型 
	private String cash_fee; //现金支付金额;  现金支付金额，单位为分，只能为整数，详见支付金额 
	private String refund_count; //退款笔数; 当前返回退款笔数 
	
	//剩下的返回参数要另作处理,例如：out_refund_no_$n;
	
	
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
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	public String getSettlement_total_fee() {
		return settlement_total_fee;
	}
	public void setSettlement_total_fee(String settlement_total_fee) {
		this.settlement_total_fee = settlement_total_fee;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public String getCash_fee() {
		return cash_fee;
	}
	public void setCash_fee(String cash_fee) {
		this.cash_fee = cash_fee;
	}
	public String getRefund_count() {
		return refund_count;
	}
	public void setRefund_count(String refund_count) {
		this.refund_count = refund_count;
	}
	
	
}

package com.hq.payms.service.wxpay.data;

public class RefundQueryReq extends CommonReq {
	
	/*** 以下单号四选一；查询的优先级是： refund_id > out_refund_no > transaction_id > out_trade_no ***/
	private String transaction_id; //微信订单号 
	private String out_trade_no; //商户订单号 
	private String out_refund_no; //商户退款单号
	private String refund_id; //微信退款单号
	
	private String offset; //偏移量; 当部分退款次数超过10次时可使用，表示返回的查询结果从这个偏移量开始取记录 
	
	public static RefundQueryReq newInstance() {
		RefundQueryReq instance = new RefundQueryReq();
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

	public String getRefund_id() {
		return refund_id;
	}

	public void setRefund_id(String refund_id) {
		this.refund_id = refund_id;
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

}

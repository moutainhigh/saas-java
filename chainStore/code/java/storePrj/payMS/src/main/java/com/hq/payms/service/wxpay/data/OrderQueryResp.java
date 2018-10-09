package com.hq.payms.service.wxpay.data;

/**
 * 订单查询响应参数 
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class OrderQueryResp extends CommonResp{  
	
	private String device_info;
	private String openid;
	private String is_subscribe ;
	private String sub_openid;
	private String sub_is_subscribe;
	private String trade_type; //交易类型; 调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，MICROPAY
	 /*
	  * 交易状态： 
	  *  NOTPAY—未支付  USERPAYING--用户支付中
	  * SUCCESS—支付成功 
	  * CLOSED—已关闭 REFUND—转入退款   REVOKED—已撤销(刷卡支付) PAYERROR--支付失败(其他原因，如银行返回失败)
	  */
	private String trade_state;
	private String bank_type;
	private String detail;
	private String total_fee; //标价金额;订单总金额，单位为分  
	private String fee_type;
	private String settlement_total_fee; //应结订单金额; 当订单使用了免充值型优惠券后返回该参数，应结订单金额=订单金额-免充值优惠券金额。 
	private String cash_fee; //现金支付金额 ;现金支付金额订单现金支付金额
	private String cash_fee_type;
	private String coupon_fee; //代金券金额 ;“代金券或立减优惠”金额<=订单总金额，订单总金额-“代金券或立减优惠”金额=现金支付金额
	private String coupon_count; //代金券使用数量 ;代金券或立减优惠使用数量 
	private String transaction_id; //微信支付订单号 
	private String out_trade_no;  //商户订单号 
	private String attach;
	private String time_end;  //支付完成时间 
	private String trade_state_desc;
	
	
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getIs_subscribe() {
		return is_subscribe;
	}
	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}
	public String getSub_openid() {
		return sub_openid;
	}
	public void setSub_openid(String sub_openid) {
		this.sub_openid = sub_openid;
	}
	public String getSub_is_subscribe() {
		return sub_is_subscribe;
	}
	public void setSub_is_subscribe(String sub_is_subscribe) {
		this.sub_is_subscribe = sub_is_subscribe;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getTrade_state() {
		return trade_state;
	}
	public void setTrade_state(String trade_state) {
		this.trade_state = trade_state;
	}
	public String getBank_type() {
		return bank_type;
	}
	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public String getSettlement_total_fee() {
		return settlement_total_fee;
	}
	public void setSettlement_total_fee(String settlement_total_fee) {
		this.settlement_total_fee = settlement_total_fee;
	}
	public String getCash_fee() {
		return cash_fee;
	}
	public void setCash_fee(String cash_fee) {
		this.cash_fee = cash_fee;
	}
	public String getCash_fee_type() {
		return cash_fee_type;
	}
	public void setCash_fee_type(String cash_fee_type) {
		this.cash_fee_type = cash_fee_type;
	}
	public String getCoupon_fee() {
		return coupon_fee;
	}
	public void setCoupon_fee(String coupon_fee) {
		this.coupon_fee = coupon_fee;
	}
	public String getCoupon_count() {
		return coupon_count;
	}
	public void setCoupon_count(String coupon_count) {
		this.coupon_count = coupon_count;
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
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getTime_end() {
		return time_end;
	}
	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}
	public String getTrade_state_desc() {
		return trade_state_desc;
	}
	public void setTrade_state_desc(String trade_state_desc) {
		this.trade_state_desc = trade_state_desc;
	}
	
	@Override
	public String toString() {
		return super.toString() 
				+ "OrderQueryResp [device_info=" + device_info + ", openid=" + openid + ", is_subscribe=" + is_subscribe
				+ ", sub_openid=" + sub_openid + ", sub_is_subscribe=" + sub_is_subscribe + ", trade_type=" + trade_type
				+ ", trade_state=" + trade_state + ", bank_type=" + bank_type + ", detail=" + detail + ", total_fee="
				+ total_fee + ", fee_type=" + fee_type + ", settlement_total_fee=" + settlement_total_fee
				+ ", cash_fee=" + cash_fee + ", cash_fee_type=" + cash_fee_type + ", coupon_fee=" + coupon_fee
				+ ", coupon_count=" + coupon_count + ", transaction_id=" + transaction_id + ", out_trade_no="
				+ out_trade_no + ", attach=" + attach + ", time_end=" + time_end + ", trade_state_desc="
				+ trade_state_desc + "]";
	}
	
	public boolean isTradeSuccess() {
        return this.isResultSuccess()
				&& "SUCCESS".equals(this.getTrade_state());
    }
	
	public boolean isTradeClose() {
        return this.isResultSuccess()
				&& ( "CLOSED".equals(this.getTrade_state())
						|| "REFUND".equals(this.getTrade_state())
						|| "REVOKED".equals(this.getTrade_state())
						|| "PAYERROR".equals(this.getTrade_state()));
    }
	
}

package com.hq.payms.service.wxpay.data;

/**
 * 刷卡支付响应
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class MicroPayResp extends CommonResp{
	
	private String openid; //用户在商户appid 下的唯一标识
	private String is_subscribe; //用户是否关注公众账号，仅在公众账号类型支付有效，取值范围：Y或N;Y-关注;N-未关注
	private String sub_openid; //子商户appid下用户唯一标识，如需返回则请求时需要传sub_appid
	private String sub_is_subscribe; //用户是否关注子公众账号，仅在公众账号类型支付有效，取值范围：Y或N;Y-关注;N-未关注
	private String trade_type; //支付类型为MICROPAY(即扫码支付)
	private String bank_type; //银行类型，采用字符串类型的银行标识，值列表详见银行类型
	private String fee_type; //符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
	private String total_fee; //订单总金额，单位为分，只能为整数，详见支付金额
	private String cash_fee_type; //现金支付币种;符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
	private String cash_fee; //现金支付金额
	private String settlement_total_fee ; //应结订单金额;当订单使用了免充值型优惠券后返回该参数，应结订单金额=订单金额-免充值优惠券金额。 
	private String coupon_fee ; //代金券金额 
	private String transaction_id; //微信支付订单号
	private String out_trade_no; //商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一。
	private String attach; //商家数据包，原样返回
	private String time_end; //支付完成时间
	
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
	public String getBank_type() {
		return bank_type;
	}
	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	public String getCash_fee_type() {
		return cash_fee_type;
	}
	public void setCash_fee_type(String cash_fee_type) {
		this.cash_fee_type = cash_fee_type;
	}
	public String getCash_fee() {
		return cash_fee;
	}
	public void setCash_fee(String cash_fee) {
		this.cash_fee = cash_fee;
	}
	public String getSettlement_total_fee() {
		return settlement_total_fee;
	}
	public void setSettlement_total_fee(String settlement_total_fee) {
		this.settlement_total_fee = settlement_total_fee;
	}
	public String getCoupon_fee() {
		return coupon_fee;
	}
	public void setCoupon_fee(String coupon_fee) {
		this.coupon_fee = coupon_fee;
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
	
    @Override
	public String toString() {
		return super.toString() + "MicroPayResp [openid=" + openid + ", is_subscribe=" + is_subscribe + ", sub_openid=" + sub_openid
				+ ", sub_is_subscribe=" + sub_is_subscribe + ", trade_type=" + trade_type + ", bank_type=" + bank_type
				+ ", fee_type=" + fee_type + ", total_fee=" + total_fee + ", cash_fee_type=" + cash_fee_type
				+ ", cash_fee=" + cash_fee + ", settlement_total_fee=" + settlement_total_fee + ", coupon_fee="
				+ coupon_fee + ", transaction_id=" + transaction_id + ", out_trade_no=" + out_trade_no + ", attach="
				+ attach + ", time_end=" + time_end + "]";
	}
	public boolean isResultUnknown() { //支付结果未知,需要查询
        return "SUCCESS".equals(this.getReturn_code())
				&& ("SYSTEMERROR".equals(this.getErr_code()) 
						|| "BANKERROR".equals(this.getErr_code()) 
						|| "USERPAYING".equals(this.getErr_code()));
    }

}

package com.hq.payms.service.wxpay.data;

/**
 * 订单查询和订单关闭可共用此请求参数
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class OrderQueryOrCloseReq extends CommonReq{
	
	private String out_trade_no; //商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一
	
	public static OrderQueryOrCloseReq newInstance() {
		OrderQueryOrCloseReq instance = new OrderQueryOrCloseReq();
		return instance;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	
	
}

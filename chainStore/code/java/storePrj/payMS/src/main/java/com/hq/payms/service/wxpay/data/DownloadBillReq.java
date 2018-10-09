package com.hq.payms.service.wxpay.data;

/**
 * 下载对账单参数 
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class DownloadBillReq extends CommonReq{
	
	private String bill_date; //下载对账单的日期，格式：20140603 
 
	/*
	 * ALL，返回当日所有订单信息，默认值 
	 * SUCCESS，返回当日成功支付的订单
	 * REFUND，返回当日退款订单
	 * RECHARGE_REFUND，返回当日充值退款订单 
	 */
	private String bill_type;  //账单类型 
	
	public static DownloadBillReq newInstance() {
		DownloadBillReq instance = new DownloadBillReq();
		return instance;
	}

	public String getBill_date() {
		return bill_date;
	}

	public void setBill_date(String bill_date) {
		this.bill_date = bill_date;
	}

	public String getBill_type() {
		return bill_type;
	}

	public void setBill_type(String bill_type) {
		this.bill_type = bill_type;
	}
	
	
	
	

}

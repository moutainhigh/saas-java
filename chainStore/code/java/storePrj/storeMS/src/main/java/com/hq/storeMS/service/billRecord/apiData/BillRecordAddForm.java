package com.hq.storeMS.service.billRecord.apiData;

import com.hq.storeMS.service.billRecord.data.BillRecord;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class BillRecordAddForm {
	private String outTradeNo;//随机生成的订单号 
	private String tradeNo;//三方（微信）支付成功返回的交易流水号
	private long storeId;//店铺ID
	private long orderId;//订单ID
	private String leaguerId;//客户id
	private String leaguerName;//客户名
	private int payType;//支付方式 PayTypeEnum
	private int billType;//交易类型 BillTypeEnum
	private float amount;//交易金额

	public static BillRecordAddForm newInstance() {
		return new BillRecordAddForm();
	}
	
	public String getOutTradeNo() {
		return outTradeNo;
	}

	public BillRecordAddForm setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
		return this;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public BillRecordAddForm setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
		return this;
	}

	public long getStoreId() {
		return storeId;
	}

	public BillRecordAddForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public long getOrderId() {
		return orderId;
	}

	public BillRecordAddForm setOrderId(long orderId) {
		this.orderId = orderId;
		return this;
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public BillRecordAddForm setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
		return this;
	}

	public String getLeaguerName() {
		return leaguerName;
	}

	public BillRecordAddForm setLeaguerName(String leaguerName) {
		this.leaguerName = leaguerName;
		return this;
	}

	public int getPayType() {
		return payType;
	}

	public BillRecordAddForm setPayType(int payType) {
		this.payType = payType;
		return this;
	}

	public int getBillType() {
		return billType;
	}

	public BillRecordAddForm setBillType(int billType) {
		this.billType = billType;
		return this;
	}

	public float getAmount() {
		return amount;
	}

	public BillRecordAddForm setAmount(float amount) {
		this.amount = amount;
		return this;
	}

	public BillRecord toBillRecord(){
		BillRecord billRecord = BillRecord.newInstance();
		FastBeanCopyer.getInstance().copy(this, billRecord);
		return billRecord;
	}

}

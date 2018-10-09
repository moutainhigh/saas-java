package com.hq.storeMS.service.billRecord.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name="billRecord")
public class BillRecord {

	@Id
	private long id;
	private String outTradeNo;//随机生成的订单号 
	private String tradeNo;//交易成功的流水号
	private long storeId;//店铺id
	private long orderId;//对应订单id
	private String leaguerId;//客户id
	private String leaguerName;//客户名
	private int payType;//支付方式 PayTypeEnum
	private int billType;//交易类型 BillTypeEnum
	private float amount;//交易金额
	private int state;//状态 TradeStateEnum
	
	private long createdTime;
	private long lastUpdateTime;
	private int ver;
	
	public static BillRecord newInstance(){
		BillRecord billRecord = new BillRecord();
		billRecord.billType = BillTypeEnum.Income.ordinal();
		
		long curTime = System.currentTimeMillis();
		billRecord.createdTime = curTime;
		billRecord.lastUpdateTime = curTime;
		return billRecord;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
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

	public String getLeaguerId() {
		return leaguerId;
	}

	public void setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
	}

	public String getLeaguerName() {
		return leaguerName;
	}

	public void setLeaguerName(String leaguerName) {
		this.leaguerName = leaguerName;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public int getBillType() {
		return billType;
	}

	public void setBillType(int billType) {
		this.billType = billType;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
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
	
	public void incrVer() {
		this.ver = this.ver + 1;
	}
	
}

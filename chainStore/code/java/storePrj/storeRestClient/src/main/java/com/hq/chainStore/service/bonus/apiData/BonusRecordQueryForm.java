package com.hq.chainStore.service.bonus.apiData;

import com.zenmind.dao.rest.ReqMap;

public class BonusRecordQueryForm {
	private long storeId;
	private long maxTime;
	private long minTime;
	private long orderId;
	
	private String buserName;
	private String status;// 状态，如果需要查询多个，请用,号分割
	private String buyId;

	private int pageItemCount;
	private int pageNo;

	public static BonusRecordQueryForm newInstance() {
		BonusRecordQueryForm data = new BonusRecordQueryForm();
		data.storeId = 0;
		data.maxTime = 0;
		data.minTime = 0;
		data.status = "";
		data.buyId = "";
		data.orderId = 0;
		
		data.pageNo = 1;
		data.pageItemCount = 0;
		return data;
	}
	
	public ReqMap toReqMap(){
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("storeId", storeId).add("maxTime", maxTime)
			.add("minTime", minTime).add("status", status)
			.add("buyId", buyId).add("orderId", orderId)
			.add("buserName", buserName)
			;
		return reqMap;
	}
	
	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public void setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(long maxTime) {
		this.maxTime = maxTime;
	}

	public long getMinTime() {
		return minTime;
	}

	public void setMinTime(long minTime) {
		this.minTime = minTime;
	}

	public String getBuyId() {
		return buyId;
	}

	public void setBuyId(String buyId) {
		this.buyId = buyId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getBuserName() {
		return buserName;
	}

	public void setBuserName(String buserName) {
		this.buserName = buserName;
	}

}

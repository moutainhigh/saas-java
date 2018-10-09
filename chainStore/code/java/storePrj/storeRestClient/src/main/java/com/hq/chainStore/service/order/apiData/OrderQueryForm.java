package com.hq.chainStore.service.order.apiData;

import com.zenmind.dao.rest.ReqMap;

public class OrderQueryForm {
	private long storeId;
	private String numberOrName;
	private int orderType;
	// 状态，如果需要查询多个，请用,号分割
	private String status;
	private long minTime;
	private long maxTime;
	private int pageItemCount;
	private int pageNo;

	private String leaguerId;
	private long cuserId;
	private long buserId;

	/** =========================遗留的字段========================= **/
	private long beauticianId;
	private long productId;
	private int payType;

	public static OrderQueryForm newInstance() {
		OrderQueryForm params = new OrderQueryForm();
		return params;
	}

	public ReqMap toReqMap() {
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("storeId", storeId).add("numberOrName", numberOrName)
				.add("orderType", orderType).add("status", status)
				.add("maxTime", maxTime).add("minTime", minTime)
				.add("leaguerId", leaguerId).add("cuserId", cuserId).add("buserId", buserId);
		return reqMap;
	}

	public long getCuserId() {
		return cuserId;
	}

	public void setCuserId(long cuserId) {
		this.cuserId = cuserId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getBeauticianId() {
		return beauticianId;
	}

	public void setBeauticianId(long beauticianId) {
		this.beauticianId = beauticianId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public void setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(int status) {
		setStatus(String.valueOf(status));
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public long getMinTime() {
		return minTime;
	}

	public void setMinTime(long minTime) {
		this.minTime = minTime;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(long maxTime) {
		this.maxTime = maxTime;
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

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public String getNumberOrName() {
		return numberOrName;
	}

	public void setNumberOrName(String numberOrName) {
		this.numberOrName = numberOrName;
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}
}

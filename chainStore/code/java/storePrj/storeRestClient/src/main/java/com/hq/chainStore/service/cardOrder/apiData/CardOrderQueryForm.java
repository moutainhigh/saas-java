package com.hq.chainStore.service.cardOrder.apiData;

import com.zenmind.dao.rest.ReqMap;

public class CardOrderQueryForm {
	private long cuserId;
	private long storeId;
	private int payType;
	private String leaguerId;
	private int status;
	private int cardType;
	private String cardId;

	private long minTime;
	private long maxTime;

	private int pageItemCount;
	private int pageNo;

	public static CardOrderQueryForm newInstance() {
		CardOrderQueryForm params = new CardOrderQueryForm();
		params.cuserId = 0L;
		params.storeId = 0L;
		params.leaguerId = "";
		params.cardId = "";
		
		params.minTime = 0L;
		params.maxTime = 0L;
		
		params.pageItemCount = 0;
		params.pageNo = 1;
		return params;
	}
	
	public ReqMap toReqMap(){
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("cuserId", this.cuserId).add("storeId", this.storeId).add("payType", this.payType).add("leaguerId", this.leaguerId)
			.add("status", this.status).add("cardType", this.cardType).add("cardId", this.cardId).add("minTime", this.minTime)
			.add("maxTime", this.maxTime);
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCardType() {
		return cardType;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
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
	
}

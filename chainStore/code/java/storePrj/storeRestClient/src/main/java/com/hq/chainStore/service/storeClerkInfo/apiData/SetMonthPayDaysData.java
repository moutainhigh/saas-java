package com.hq.chainStore.service.storeClerkInfo.apiData;

public class SetMonthPayDaysData {

	private long storeId;
	//对应店铺工资月结天数
	private int monthPayDays;
	
	public static SetMonthPayDaysData newInstance(){
		return new SetMonthPayDaysData();
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public int getMonthPayDays() {
		return monthPayDays;
	}

	public void setMonthPayDays(int monthPayDays) {
		this.monthPayDays = monthPayDays;
	}
	
}

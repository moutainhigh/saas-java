package com.hq.storeMS.service.leaguerCard.apiData;

public class LeaguerCardQueryForm {

	private long storeId;
	private String leaguerNameOrPhone;// 手机或者名称
	private int sort;// 升/降序 对应sortEnum
	private int loadType;//LoadDataEnum
	private String cardTypeId;

	private int pageItemCount;
	private int pageNo;
 
	public static LeaguerCardQueryForm newInstance(){
		return new LeaguerCardQueryForm();
	}

	public long getStoreId() {
		return storeId;
	}

	public LeaguerCardQueryForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public String getLeaguerNameOrPhone() {
		return leaguerNameOrPhone;
	}

	public LeaguerCardQueryForm setLeaguerNameOrPhone(String leaguerNameOrPhone) {
		this.leaguerNameOrPhone = leaguerNameOrPhone;
		return this;
	}

	public int getSort() {
		return sort;
	}

	public LeaguerCardQueryForm setSort(int sort) {
		this.sort = sort;
		return this;
	}
	
	public int getLoadType() {
		return loadType;
	}

	public String getCardTypeId() {
		return cardTypeId;
	}

	public LeaguerCardQueryForm setCardTypeId(String cardTypeId) {
		this.cardTypeId = cardTypeId;
		return this;
	}

	public LeaguerCardQueryForm setLoadType(int loadType) {
		this.loadType = loadType;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public LeaguerCardQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public LeaguerCardQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}
	
}

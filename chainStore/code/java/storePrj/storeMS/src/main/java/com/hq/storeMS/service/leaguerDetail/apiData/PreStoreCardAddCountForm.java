package com.hq.storeMS.service.leaguerDetail.apiData;

public class PreStoreCardAddCountForm {
	//客户预存卡ID
	private String id;

	private int itemType;
	private String pgId;
	private int count;

	public static PreStoreCardAddCountForm newInstance() {
		return new PreStoreCardAddCountForm();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	public String getPgId() {
		return pgId;
	}

	public void setPgId(String pgId) {
		this.pgId = pgId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}

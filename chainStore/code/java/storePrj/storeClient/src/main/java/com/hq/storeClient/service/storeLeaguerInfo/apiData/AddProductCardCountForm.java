package com.hq.storeClient.service.storeLeaguerInfo.apiData;

public class AddProductCardCountForm {
	private String leaguerProductCardId;// 对应LeaguerProductCard id
	private int itemType;// 对应 ProductCardItemEnum
	private String pgId;// 项目/商品/套餐ID
	private int count;// 消费次数
	
	public static AddProductCardCountForm newInstance() {
		return new AddProductCardCountForm();
	}

	public String getLeaguerProductCardId() {
		return leaguerProductCardId;
	}

	public void setLeaguerProductCardId(String leaguerProductCardId) {
		this.leaguerProductCardId = leaguerProductCardId;
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

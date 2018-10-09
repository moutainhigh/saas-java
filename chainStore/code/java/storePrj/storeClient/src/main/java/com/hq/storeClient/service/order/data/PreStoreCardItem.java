package com.hq.storeClient.service.order.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class PreStoreCardItem {
	// id _preStore_{itemType}_{pgId}_{preStoreCardId}
	private String id;
	// 类型 ProductCardItemEnum
	private int itemType;
	// 项目/商品/套餐的ID
	private String pgId;
	// 客户预存卡的ID
	private String preStoreCardId;
	// 数量
	private int count;

	public static PreStoreCardItem newInstance() {
		PreStoreCardItem data = new PreStoreCardItem();
		return data;
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

	public String getPreStoreCardId() {
		return preStoreCardId;
	}

	public void setPreStoreCardId(String preStoreCardId) {
		this.preStoreCardId = preStoreCardId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}

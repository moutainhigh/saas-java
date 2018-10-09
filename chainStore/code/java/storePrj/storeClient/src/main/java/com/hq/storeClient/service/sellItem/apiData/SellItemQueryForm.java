package com.hq.storeClient.service.sellItem.apiData;

import java.util.ArrayList;
import java.util.List;

public class SellItemQueryForm {
	// 店铺ID
	private long storeId;
	
	private List<SellItemIdForm> sellItemIds = new ArrayList<SellItemIdForm>();

	public static SellItemQueryForm newInstance() {
		SellItemQueryForm data = new SellItemQueryForm();
		return data;
	}

	public SellItemQueryForm addSellItemIdForm(SellItemIdForm... sellItemIdForms) {
		for (SellItemIdForm sellItemIdForm : sellItemIdForms) {
			sellItemIds.add(sellItemIdForm);
		}
		return this;
	}

	public List<SellItemIdForm> getSellItemIds() {
		return sellItemIds;
	}

	public void setSellItemIds(List<SellItemIdForm> sellItemIds) {
		this.sellItemIds = sellItemIds;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

}

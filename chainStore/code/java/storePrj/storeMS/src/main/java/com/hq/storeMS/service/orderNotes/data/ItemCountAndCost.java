package com.hq.storeMS.service.orderNotes.data;

public class ItemCountAndCost {
	private String itemId;
	private float cost;
	private int count;

	public static ItemCountAndCost newInstance(String itemIdP) {
		ItemCountAndCost data = new ItemCountAndCost();
		data.itemId = itemIdP;
		return data;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}

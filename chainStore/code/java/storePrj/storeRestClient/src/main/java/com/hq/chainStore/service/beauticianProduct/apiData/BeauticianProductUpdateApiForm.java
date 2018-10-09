package com.hq.chainStore.service.beauticianProduct.apiData;

public class BeauticianProductUpdateApiForm {
	private int updateType;

	private AddForemost addForemost;
	private RemoveForemost removeForemost;

	public static BeauticianProductUpdateApiForm newInstance() {
		return new BeauticianProductUpdateApiForm();
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public AddForemost getAddForemost() {
		return addForemost;
	}

	public void setAddForemost(AddForemost addForemost) {
		this.addForemost = addForemost;
	}

	public RemoveForemost getRemoveForemost() {
		return removeForemost;
	}

	public void setRemoveForemost(RemoveForemost removeForemost) {
		this.removeForemost = removeForemost;
	}

}

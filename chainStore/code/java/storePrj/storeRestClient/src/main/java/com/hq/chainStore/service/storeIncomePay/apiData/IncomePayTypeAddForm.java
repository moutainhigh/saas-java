package com.hq.chainStore.service.storeIncomePay.apiData;

import com.hq.chainStore.service.incomePay.data.IncomePayCategoryEnum;

public class IncomePayTypeAddForm {
	private long index;
	private String name;
	/**
	 * 类别 {@link IncomePayCategoryEnum}
	 */
	private int category;


	public static IncomePayTypeAddForm newInstance() {
		return new IncomePayTypeAddForm();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getIndex() {
		return index;
	}

	public void setIndex(long index) {
		this.index = index;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	
}

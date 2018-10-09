package com.hq.storeMS.service.storeIncomePay.apiData;

import com.hq.storeMS.service.storeIncomePay.data.IncomePayType;

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

	public IncomePayType toIncomePayType() {
		IncomePayType data = IncomePayType.newInstance();
		data.setName(name);
		data.setId(String.valueOf(index));
		data.setCategory(category);
		return data;
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

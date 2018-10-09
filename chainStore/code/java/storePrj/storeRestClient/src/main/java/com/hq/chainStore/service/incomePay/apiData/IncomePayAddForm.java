package com.hq.chainStore.service.incomePay.apiData;

public class IncomePayAddForm {
	private long storeId;

	/**
	 * 类别 {@link com.hq.chainStore.service.incomePay.data.IncomePayCategoryEnum}
	 */
	private int category;

	private long incomePayTime;//收支时间

	private long money;//金额

	private String typeId;//分类id

	private long buserId;//人员id

	private String remark;//备注

	public static IncomePayAddForm newInstance() {
		return new IncomePayAddForm();
	}

	public long getStoreId() {
		return storeId;
	}

	public IncomePayAddForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public int getCategory() {
		return category;
	}

	public IncomePayAddForm setCategory(int category) {
		this.category = category;
		return this;
	}

	public long getIncomePayTime() {
		return incomePayTime;
	}

	public IncomePayAddForm setIncomePayTime(long incomePayTime) {
		this.incomePayTime = incomePayTime;
		return this;
	}

	public long getMoney() {
		return money;
	}

	public IncomePayAddForm setMoney(long money) {
		this.money = money;
		return this;
	}

	public String getTypeId() {
		return typeId;
	}

	public IncomePayAddForm setTypeId(String typeId) {
		this.typeId = typeId;
		return this;
	}

	public long getBuserId() {
		return buserId;
	}

	public IncomePayAddForm setBuserId(long buserId) {
		this.buserId = buserId;
		return this;
	}

	public String getRemark() {
		return remark;
	}

	public IncomePayAddForm setRemark(String remark) {
		this.remark = remark;
		return this;
	}

}

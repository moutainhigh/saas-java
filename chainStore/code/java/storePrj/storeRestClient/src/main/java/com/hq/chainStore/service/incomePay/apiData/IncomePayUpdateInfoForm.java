package com.hq.chainStore.service.incomePay.apiData;

/**
 * description：编辑收支 API 表单 author： Xander time： 2018/08/13 11:47
 */
public class IncomePayUpdateInfoForm {
	private long id;

	private long storeId;

	/**
	 * 类别 {@link com.hq.chainStore.service.incomePay.data.IncomePayCategoryEnum}
	 */
	private int category;

	private long incomePayTime;// 收支时间

	private long money;// 金额

	private String typeId;// 分类id

	private long buserId;// 人员id

	private String remark;// 备注

	public static IncomePayUpdateInfoForm newInstance() {
		IncomePayUpdateInfoForm instance = new IncomePayUpdateInfoForm();
		return instance;
	}

	public long getId() {
		return id;
	}

	public IncomePayUpdateInfoForm setId(long id) {
		this.id = id;
		return this;
	}

	public long getStoreId() {
		return storeId;
	}

	public IncomePayUpdateInfoForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public int getCategory() {
		return category;
	}

	public IncomePayUpdateInfoForm setCategory(int category) {
		this.category = category;
		return this;
	}

	public long getIncomePayTime() {
		return incomePayTime;
	}

	public IncomePayUpdateInfoForm setIncomePayTime(long incomePayTime) {
		this.incomePayTime = incomePayTime;
		return this;
	}

	public long getMoney() {
		return money;
	}

	public IncomePayUpdateInfoForm setMoney(long money) {
		this.money = money;
		return this;
	}

	public String getTypeId() {
		return typeId;
	}

	public IncomePayUpdateInfoForm setTypeId(String typeId) {
		this.typeId = typeId;
		return this;
	}

	public long getBuserId() {
		return buserId;
	}

	public IncomePayUpdateInfoForm setBuserId(long buserId) {
		this.buserId = buserId;
		return this;
	}

	public String getRemark() {
		return remark;
	}

	public IncomePayUpdateInfoForm setRemark(String remark) {
		this.remark = remark;
		return this;
	}
}

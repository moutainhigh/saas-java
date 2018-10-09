package com.hq.storeMS.service.storeIncomePay.data;

import com.hq.storeMS.service.incomePay.data.IncomePayCategoryEnum;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class IncomePayType {
	private String id;
	/**
     * 类别 {@link IncomePayCategoryEnum}
     */
    private int category;
	private String name;
	private int entityState;
	private long createTime;
	private long lastUpdateTime;

	public static IncomePayType newInstance() {
		IncomePayType data = new IncomePayType();
		long currentTimeMillis = System.currentTimeMillis();
		data.createTime = currentTimeMillis;
		data.lastUpdateTime = currentTimeMillis;
		return data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public int getEntityState() {
		return entityState;
	}

	public void setEntityState(int entityState) {
		this.entityState = entityState;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	
}

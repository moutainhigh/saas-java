package com.hq.storeMS.service.storeIncomePay.apiData;

import com.hq.storeMS.service.storeIncomePay.data.IncomePayType;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class IncomePayTypeUpdateForm {
	private String id;
	private String name;

	public static IncomePayTypeUpdateForm newInstance() {
		return new IncomePayTypeUpdateForm();
	}
	
	public void toIncomePayType(IncomePayType data) {
		FastBeanCopyer.getInstance().copy(this, data);
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

}

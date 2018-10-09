package com.hq.storeMS.service.storeCardInfo.apiData;

import com.hq.storeMS.service.storeCardInfo.data.PrdCardType;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class PrdCardTypeUpdateForm {
	private String id;
	private String name;

	public static PrdCardTypeUpdateForm newInstance() {
		return new PrdCardTypeUpdateForm();
	}
	
	public void updatePrdCardType(PrdCardType data) {
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

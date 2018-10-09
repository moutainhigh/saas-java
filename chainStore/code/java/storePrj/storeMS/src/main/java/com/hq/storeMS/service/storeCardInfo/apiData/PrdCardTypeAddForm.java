package com.hq.storeMS.service.storeCardInfo.apiData;

import com.hq.storeMS.service.storeCardInfo.data.PrdCardType;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class PrdCardTypeAddForm {
	private long index;
	private String name;

	public static PrdCardTypeAddForm newInstance() {
		return new PrdCardTypeAddForm();
	}

	public PrdCardType toPrdCardType() {
		PrdCardType data = PrdCardType.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		data.setId(String.valueOf(index));
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

}

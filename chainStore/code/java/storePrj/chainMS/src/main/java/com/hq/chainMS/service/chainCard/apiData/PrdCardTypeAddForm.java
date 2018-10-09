package com.hq.chainMS.service.chainCard.apiData;

import com.hq.chainMS.service.chainCard.data.PrdCardType;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class PrdCardTypeAddForm {
	private long index;
	private String name;

	public static PrdCardTypeAddForm newInstance() {
		return new PrdCardTypeAddForm();
	}

	public PrdCardType toPrdCardType(long chainId) {
		PrdCardType data = PrdCardType.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		data.setId(PrdCardType.generateId(chainId, index));
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

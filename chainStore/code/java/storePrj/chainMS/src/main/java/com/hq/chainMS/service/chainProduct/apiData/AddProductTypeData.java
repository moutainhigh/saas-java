package com.hq.chainMS.service.chainProduct.apiData;

import com.hq.chainMS.service.chainProduct.data.ProductType;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class AddProductTypeData {
	private long index;
	private String name;

	public static AddProductTypeData newInstance() {
		return new AddProductTypeData();
	}

	public ProductType toProductType(long chainId) {
		ProductType data = ProductType.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		data.setId(ProductType.generateId(chainId, index));
		return data;
	}

	public long getIndex() {
		return index;
	}

	public void setIndex(long index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

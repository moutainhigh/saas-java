package com.hq.storeMS.service.storeProductInfo.apiData;

import com.hq.storeMS.service.storeProductInfo.data.ProductType;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class AddProductTypeData {
	private int index;
	private long storeId;
	private String name;

	public static AddProductTypeData newInstance() {
		return new AddProductTypeData();
	}

	public ProductType toProductType() {
		ProductType data = ProductType.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		data.setId(String.valueOf(index));
		return data;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}

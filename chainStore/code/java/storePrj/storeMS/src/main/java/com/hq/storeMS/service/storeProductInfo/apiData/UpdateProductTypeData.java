package com.hq.storeMS.service.storeProductInfo.apiData;

import com.hq.storeMS.service.storeProductInfo.data.ProductType;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class UpdateProductTypeData {

	private String id;
	
	private long storeId;
	
	private String name;
	
	public static UpdateProductTypeData newInstance(){
		return new UpdateProductTypeData();
	}
	
	public void updateProductType(ProductType data) {
		FastBeanCopyer.getInstance().copy(this, data);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

}

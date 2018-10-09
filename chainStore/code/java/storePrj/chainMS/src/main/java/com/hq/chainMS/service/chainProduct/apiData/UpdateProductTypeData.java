package com.hq.chainMS.service.chainProduct.apiData;

import com.hq.chainMS.service.chainProduct.data.ProductType;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class UpdateProductTypeData {
	private String id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

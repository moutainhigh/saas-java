package com.hq.storeMS.service.storeGoods.apiData;

import com.hq.storeMS.service.storeGoods.data.GoodsType;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class GoodsTypeUpdateForm {
	private String id;
	private String name;

	public static GoodsTypeUpdateForm newInstance() {
		return new GoodsTypeUpdateForm();
	}
	
	public void updateGoodsType(GoodsType data) {
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

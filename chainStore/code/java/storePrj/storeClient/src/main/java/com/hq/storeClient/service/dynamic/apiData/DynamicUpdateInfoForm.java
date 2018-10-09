package com.hq.storeClient.service.dynamic.apiData;

import java.util.ArrayList;
import java.util.List;

import com.hq.storeClient.service.dynamic.data.Dynamic;
import com.hq.storeClient.service.dynamic.data.DynamicItem;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class DynamicUpdateInfoForm {
	// 文案
	private String docContent;
	// 图片列表
	private List<String> imgPaths = new ArrayList<String>();
	// 推广的产品列表
	private List<DynamicItem> dynamicItems = new ArrayList<DynamicItem>();

	public static DynamicUpdateInfoForm newInstance() {
		DynamicUpdateInfoForm data = new DynamicUpdateInfoForm();
		return data;
	}
	
	public void updateDynamic(Dynamic data) {
		FastBeanCopyer.getInstance().copy(this, data);
	}

	public String getDocContent() {
		return docContent;
	}

	public void setDocContent(String docContent) {
		this.docContent = docContent;
	}

	public List<String> getImgPaths() {
		return imgPaths;
	}

	public void setImgPaths(List<String> imgPaths) {
		this.imgPaths = imgPaths;
	}

	public List<DynamicItem> getDynamicItems() {
		return dynamicItems;
	}

	public void setDynamicItems(List<DynamicItem> dynamicItems) {
		this.dynamicItems = dynamicItems;
	}
}

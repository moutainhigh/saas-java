package com.hq.storeMS.service.dynamic.apiData;

import java.util.ArrayList;
import java.util.List;

import com.hq.storeMS.service.dynamic.data.Dynamic;
import com.hq.storeMS.service.dynamic.data.DynamicItem;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class DynamicAddForm {
	// 店铺ID
	private long storeId;
	// 创建者
	private long buserId;
	// 状态 DynamicStatusEnum
	private int status;
	// 文案
	private String docContent;
	// 图片列表
	private List<String> imgPaths = new ArrayList<String>();
	// 推广的产品列表
	private List<DynamicItem> dynamicItems = new ArrayList<DynamicItem>();
	
	/**************************以下字段，是在营销助手小程序里面需要加的**************************/
	// 智能营销助手小程序，表单提交场景下，为 submit 事件带上的 formId；
	private String formId;
	// 来源的小程序  MiniProgramEnum 
	private int miniProgram;

	public static DynamicAddForm newInstance() {
		DynamicAddForm data = new DynamicAddForm();
		return data;
	}

	public Dynamic toDynamic() {
		Dynamic data = Dynamic.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		return data;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public int getMiniProgram() {
		return miniProgram;
	}

	public void setMiniProgram(int miniProgram) {
		this.miniProgram = miniProgram;
	}
}

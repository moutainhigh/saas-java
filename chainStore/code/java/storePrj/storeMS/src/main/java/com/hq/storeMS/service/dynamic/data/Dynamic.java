package com.hq.storeMS.service.dynamic.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dao.classinfo.IndexField;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "dynamic")
public class Dynamic {
	@Id
	private long id;// 主键ID

	// 店铺ID
	private long storeId;
	// 创建者
	@IndexField
	private long buserId;
	// 状态 DynamicStatusEnum
	private int status;
	// 动态类型 DynamicTypeEnum
	private int type;

	// 实体状态
	private int entityState;
	// 智能营销助手小程序，表单提交场景下，为 submit 事件带上的 formId；
	private String formId;
	// 来源的小程序 MiniProgramEnum
	private int miniProgram;

	/******************************** 普通动态内容 ********************************/
	// 文案
	private String docContent;
	// 图片列表
	private List<String> imgPaths = new ArrayList<String>();
	// 推广的产品列表
	private List<DynamicItem> dynamicItems = new ArrayList<DynamicItem>();

	@IndexField
	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static Dynamic newInstance() {
		Dynamic data = new Dynamic();
		long currentTime = System.currentTimeMillis();
		data.createdTime = currentTime;
		data.lastUpdateTime = currentTime;
		return data;
	}

	public void incrVer() {
		this.ver = this.ver + 1;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public int getEntityState() {
		return entityState;
	}

	public void setEntityState(int entityState) {
		this.entityState = entityState;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public long getVer() {
		return ver;
	}

	public void setVer(long ver) {
		this.ver = ver;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}

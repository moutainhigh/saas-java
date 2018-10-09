package com.hq.storeMS.service.workFlowType.data;

import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dao.classinfo.IndexField;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "workFlowType")
public class WorkFlowType{
	@Id
	private long id; 
	// 组件名称
	@IndexField
	private String wfCompName;
	// 组件列表明细
	private List<WFCompInfo> wfCompInfos;

	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static WorkFlowType newInstance() {
		WorkFlowType data = new WorkFlowType();

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

	public String getWfCompName() {
		return wfCompName;
	}

	public void setWfCompName(String wfCompName) {
		this.wfCompName = wfCompName;
	}

	public List<WFCompInfo> getWfCompInfos() {
		return wfCompInfos;
	}

	public void setWfCompInfos(List<WFCompInfo> wfCompInfos) {
		this.wfCompInfos = wfCompInfos;
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

}

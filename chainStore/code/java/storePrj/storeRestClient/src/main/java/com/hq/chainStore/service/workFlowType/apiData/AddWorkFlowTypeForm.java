package com.hq.chainStore.service.workFlowType.apiData;

import java.util.List;

import com.hq.chainStore.service.workFlowType.data.WFCompInfo;

public class AddWorkFlowTypeForm {
	// 组件名称
	private String wfCompName;
	// 组件列表明细
	private List<WFCompInfo> wfCompInfos;

	public static AddWorkFlowTypeForm newInstance() {
		return new AddWorkFlowTypeForm();
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
}

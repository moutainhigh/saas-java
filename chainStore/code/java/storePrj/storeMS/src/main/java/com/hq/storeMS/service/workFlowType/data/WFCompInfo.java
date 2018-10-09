package com.hq.storeMS.service.workFlowType.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class WFCompInfo {
	//组件类型  WFCompEnum
	private int wfComp;
	//组件的位置  positionEnum
	private int index;
	//描述
	private String descipt;

	public static WFCompInfo newInstance() {
		return new WFCompInfo();
	}
	
	public static WFCompInfo newInstance(WFCompEnum wfEnum, int index) {
		WFCompInfo wfCompInfo = new WFCompInfo();
		wfCompInfo.index=index;
		wfCompInfo.wfComp=wfEnum.ordinal();
		wfCompInfo.descipt=wfEnum.getMark();
		return wfCompInfo;
	}

	public int getWfComp() {
		return wfComp;
	}

	public void setWfComp(int wfComp) {
		this.wfComp = wfComp;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getDescipt() {
		return descipt;
	}

	public void setDescipt(String descipt) {
		this.descipt = descipt;
	}
}

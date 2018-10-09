package com.hq.chainStore.service.buserDevice.data;

public class MCtrlUpdateCtrlStateForm {

	//控制状态 MClientCtrlStateEnum
	private int ctrlState;
	
	public static MCtrlUpdateCtrlStateForm newInstance() {
		return new MCtrlUpdateCtrlStateForm();
	}
	

	public int getCtrlState() {
		return ctrlState;
	}

	public void setCtrlState(int ctrlState) {
		this.ctrlState = ctrlState;
	}

	
	

}

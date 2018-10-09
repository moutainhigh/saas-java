package com.hq.storeMS.service.buserDevice.data;


public class MCtrlLockApiForm {
	
	//true:锁定 false:解锁
	private boolean isLock; 
	
	//是否厂家(解锁时可忽略)  true:厂家  false:用户
	private boolean isSupplier;
	
	public static MCtrlLockApiForm newInstance() {
		return new MCtrlLockApiForm();
	}
	
	/**
	 * 判断是否锁定，是否厂家锁定，组装MCtrlUpdateCtrlStateForm
	 * @return
	 */
	public MCtrlUpdateCtrlStateForm toMCtrlUpdateCtrlStateForm() {
		MCtrlUpdateCtrlStateForm ctrlStateForm = MCtrlUpdateCtrlStateForm.newInstance();
		if(this.isLock && !this.isSupplier){
			ctrlStateForm.setCtrlState(MClientCtrlStateEnum.Locked.ordinal());
		}
		if(this.isLock && this.isSupplier){
			ctrlStateForm.setCtrlState(MClientCtrlStateEnum.LockedBySupplier.ordinal());
		}
		if(!this.isLock){
			ctrlStateForm.setCtrlState(MClientCtrlStateEnum.Standby.ordinal());
		}
		return ctrlStateForm;
	}
	
	public boolean isLock() {
		return isLock;
	}

	public void setLock(boolean isLock) {
		this.isLock = isLock;
	}

	public boolean isSupplier() {
		return isSupplier;
	}

	public void setSupplier(boolean isSupplier) {
		this.isSupplier = isSupplier;
	}

}

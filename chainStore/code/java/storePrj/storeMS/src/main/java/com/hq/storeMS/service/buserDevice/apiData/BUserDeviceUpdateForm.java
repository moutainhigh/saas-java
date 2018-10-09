package com.hq.storeMS.service.buserDevice.apiData;



/** 
 * @ClassName: UpdateBUserDeviceForm 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author helen 
 * @date 2018年1月27日 上午11:11:59 
 *  
 */
public class BUserDeviceUpdateForm {
	
	private long buserId;

	private int updateType;
	
	private AddStoreDeviceData addStoreDeviceData;
		
	public static BUserDeviceUpdateForm newInstance(){
		return new BUserDeviceUpdateForm();
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public AddStoreDeviceData getAddStoreDeviceData() {
		return addStoreDeviceData;
	}

	public void setAddStoreDeviceData(AddStoreDeviceData addStoreDeviceData) {
		this.addStoreDeviceData = addStoreDeviceData;
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}
	
	
	
}

package com.hq.storeClient.service.buserDevice.apiData;

/** 
 * @ClassName: AddStoreDeviceData 
 * @Description: 分配店铺 Form 
 * @author helen 
 * @date 2018年1月27日 下午5:29:47 
 *  
 */
public class AddStoreDeviceData {
	
	private long buserId;
	
	private long storeId;
		
	public static AddStoreDeviceData newInstance(){
		return new AddStoreDeviceData();
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}


}

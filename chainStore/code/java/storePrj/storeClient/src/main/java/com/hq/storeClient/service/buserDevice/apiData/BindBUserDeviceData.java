package com.hq.storeClient.service.buserDevice.apiData;

/** 
 * @ClassName: BindBUserDeviceData 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author helen 
 * @date 2018年2月2日 上午11:13:35 
 *  
 */
public class BindBUserDeviceData {

	private long buserId;
	
	private long iotRecordId;//设备后台记录id
	
	public static BindBUserDeviceData newInstance(){
		return new BindBUserDeviceData();
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

	public long getIotRecordId() {
		return iotRecordId;
	}

	public void setIotRecordId(long iotRecordId) {
		this.iotRecordId = iotRecordId;
	}
	
	
}


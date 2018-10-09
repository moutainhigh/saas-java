package com.hq.storeMS.service.buserDevice.data;

import com.zenmind.dataSyn.annotation.SynClass;

/** 
 * @ClassName: Device 
 * @Description: 仪器实体类 
 * @author helen 
 * @date 2018年1月27日 上午8:59:04 
 *  
 */

@SynClass
public class DeviceInfo{

	private long id;//记录id
	
	private long iotRecordId;//设备后台记录ID;
		
	private long buserId;
	
	private long storeId;

	public static DeviceInfo newInstance(){
		return new DeviceInfo();
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getIotRecordId() {
		return iotRecordId;
	}

	public void setIotRecordId(long iotRecordId) {
		this.iotRecordId = iotRecordId;
	}

	
}

package com.hq.storeClient.service.buserDevice.apiData;

/** 
 * @ClassName: BindDeviceForm 
 * @Description:绑定仪器Form 
 * @author helen 
 * @date 2018年1月30日 下午1:59:36 
 *  
 */
public class BindDeviceForm {
		
	private String snCode;
	private long buserId;
	
	public static BindDeviceForm newInstance(){
		
		return new BindDeviceForm();
	}
	
	public String getSnCode() {
		return snCode;
	}

	public void setSnCode(String snCode) {
		this.snCode = snCode;
	}


	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

	
}

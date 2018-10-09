package com.hq.storeMS.service.buserDevice.apiData;

import com.hq.storeMS.service.buserDevice.data.BUserDevice;

/** 
 * @ClassName: BindBUserDeviceData 
 * @Description: app绑定仪器Form 
 * @author helen 
 * @date 2018年2月2日 上午11:13:35 
 *  
 */
public class BindBUserDeviceData {

	private long buserId;
	
	private long iotRecordId;//设备后台记录id
	
	private String salesman; //客户代表
	
	private String salesmanPhone;//客户代表手机号
	
	public static BindBUserDeviceData newInstance(){
		return new BindBUserDeviceData();
	}

	public BUserDevice toBUserDevice() {
		BUserDevice buserDevice = BUserDevice.newInstance();
		buserDevice.setBuserId(buserId);
		buserDevice.setSalesman(salesman);
		buserDevice.setSalesmanPhone(salesmanPhone);
		return buserDevice;
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

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public String getSalesmanPhone() {
		return salesmanPhone;
	}

	public void setSalesmanPhone(String salesmanPhone) {
		this.salesmanPhone = salesmanPhone;
	}
	
	
}


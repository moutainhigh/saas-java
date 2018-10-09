package com.hq.testClass.robot.BUserDevice;

import java.util.ArrayList;
import java.util.List;

import com.hq.chainStore.service.buserDevice.data.DeviceInfo;

/** 
 * @ClassName: BUserDeviceRobotData 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author helen 
 * @date 2018年1月29日 上午11:08:02 
 *  
 */
public class BUserDeviceRobotData {

	private int mark;
			
	private int index;
	
	private long buserId;
	
	private String salesman; //客户代表
	
	private String salesmanPhone;//客户代表手机号
	
	private List<DeviceInfo> deviceInfoList = new ArrayList<DeviceInfo>();
	
	private long createTime;
	
	private long lastUpdateTime;
	
	private Integer ver;
	
	public static BUserDeviceRobotData newInstance(int mark){
		BUserDeviceRobotData data = new BUserDeviceRobotData();
		data.setBuserId(mark);
		data.setIndex(0);
		data.setVer(0);
		DeviceInfo deviceInfo = DeviceInfo.newInstance();
			deviceInfo.setBuserId(881);
			deviceInfo.setIotRecordId(1);
		DeviceInfo deviceInfo2 = DeviceInfo.newInstance();
			deviceInfo2.setBuserId(201607);
			deviceInfo.setIotRecordId(2);
		data.setCreateTime(System.currentTimeMillis());
		data.getDeviceInfoList().add(deviceInfo);
		data.getDeviceInfoList().add(deviceInfo2);
		return data;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

	public List<DeviceInfo> getDeviceInfoList() {
		return deviceInfoList;
	}

	public void setDeviceInfoList(List<DeviceInfo> deviceInfoList) {
		this.deviceInfoList = deviceInfoList;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getVer() {
		return ver;
	}

	public void setVer(Integer ver) {
		this.ver = ver;
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

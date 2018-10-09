package com.hq.chainStore.service.buserDevice.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.common.dataSyn.bs.IntfSynData;

/** 
 * @ClassName: BUserDeviceData 
 * @Description: 用户绑定仪器 实体
 * @author helen 
 * @date 2018年1月27日 上午9:55:42 
 *  
 */

@Table(name="buserDevice")
public class BUserDevice implements IntfSynData{

	@Id
	private long id;
	
	private long index;
		
	private long buserId;
	
	//客户代表
	private String salesman;
	
	//手机号
	private String phone;
	
	//用户绑定的仪器列表
	private List<DeviceInfo> deviceList = new ArrayList<DeviceInfo>();
	
	private long createTime;
	
	private long lastUpdateTime;
	
	private Integer ver;
	
	public static BUserDevice newInstance(){
		BUserDevice buserDevice = new BUserDevice();
		buserDevice.createTime = System.currentTimeMillis();
		buserDevice.ver = 0;
		buserDevice.index = 0;
		return buserDevice;
	}
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getIndex() {
		return index;
	}


	public void setIndex(long index) {
		this.index = index;
	}


	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public long getBuserId() {
		return buserId;
	}



	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}


	public List<DeviceInfo> getDeviceList() {
		return deviceList;
	}


	public void setDeviceList(List<DeviceInfo> deviceList) {
		this.deviceList = deviceList;
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



	public void incrVer() {
		this.ver = this.ver + 1;
	}

	
	@Override
	public String toString() {
		return "BUserDevice [id=" + id + ", index=" + index + ", buserId="
				+ buserId + ", deviceList=" + deviceList + ", createTime="
				+ createTime + ", lastUpdateTime=" + lastUpdateTime + ", ver="
				+ ver + "]";
	}

	@Override
	public Object targetId() {
		return this.id;
	}

	@Override
	public long targetVer() {
		return this.ver;
	}
	
	
	
}

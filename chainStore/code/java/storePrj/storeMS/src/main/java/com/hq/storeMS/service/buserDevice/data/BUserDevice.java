package com.hq.storeMS.service.buserDevice.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dataSyn.annotation.SynClass;

/** 
 * @ClassName: BUserDeviceData 
 * @Description: 用户绑定仪器 实体
 * @author helen 
 * @date 2018年1月27日 上午9:55:42 
 *  
 */

@SynClass
@Table(name="buserDevice")
public class BUserDevice {

	@Id
	private long id;
	
	private long index;
		
	private long buserId;
	
	//用户绑定的仪器列表
	private List<DeviceInfo> deviceList = new ArrayList<DeviceInfo>();
	
	private String salesman; //客户代表
	
	private String salesmanPhone;//客户代表手机号
	
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

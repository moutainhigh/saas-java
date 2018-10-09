package com.hq.storeMS.service.buserDevice.data.vo;

import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 商户绑定设备信息VO
 * @Description:  提供给智美通平台管理后台的
 * @author: wujunwei 
 * @date: 2018年2月6日  
 * @version: v1.0  
 * @since: JDK 1.8
 */
@SynClass
public class BUserBindInfo {
	
	private long buserId; //商家id
	
	private String buserName; //商家姓名

	private long buserPhone; //商家账号
	
	private String storeArea; //店铺地区
	
	private String salesman; //客户代表
	
	private String salesmanPhone;//客户代表手机号
	
	private int bindDeviceCount; //绑定设备数

	public static BUserBindInfo newInstance(){
		return new BUserBindInfo();
	}
	
	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

	public String getBuserName() {
		return buserName;
	}

	public void setBuserName(String buserName) {
		this.buserName = buserName;
	}

	public long getBuserPhone() {
		return buserPhone;
	}

	public void setBuserPhone(long buserPhone) {
		this.buserPhone = buserPhone;
	}

	public String getStoreArea() {
		return storeArea;
	}

	public void setStoreArea(String storeArea) {
		this.storeArea = storeArea;
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

	public int getBindDeviceCount() {
		return bindDeviceCount;
	}

	public void setBindDeviceCount(int bindDeviceCount) {
		this.bindDeviceCount = bindDeviceCount;
	}
	
	
}

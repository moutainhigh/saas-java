package com.hq.customerMS.service.cuser.apiData;


import com.hq.customerMS.service.cuser.data.ReceiverAddress;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class CUserAddressUpdateForm {
	private String id;
	//收货人
	private String receiver;
	//收货人号码
	private String phone;
	//地区
	private String addressArea;
	//详细地址
	private String addressDetail;
	//默认地址标志 
	private int defaultFlag;
	//地区选择组件值
	private String addressPickerValue;
	
	public static CUserAddressUpdateForm newInstance() {
		CUserAddressUpdateForm data = new CUserAddressUpdateForm();
		return data;
	} 
	
	public ReceiverAddress toReceiverAddress() {
		ReceiverAddress data = ReceiverAddress.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		return data;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getReceiver() {
		return receiver;
	}
	
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAddressArea() {
		return addressArea;
	}

	public void setAddressArea(String addressArea) {
		this.addressArea = addressArea;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public int getDefaultFlag() {
		return defaultFlag;
	}
	
	public void setDefaultFlag(int defaultFlag) {
		this.defaultFlag = defaultFlag;
	}
	
	public String getAddressPickerValue() {
		return addressPickerValue;
	}
	
	public void setAddressPickerValue(String addressPickerValue) {
		this.addressPickerValue = addressPickerValue;
	}
	
	
}

package com.hq.chainStore.service.chain.apiData;

public class ChainUpdateInfoForm {
	private long chainId;
	// 名称
	private String name;
	// 店铺描述
	private String descript;
	// 区域
	private String area;
	// 地址
	private String address;
	// 联系电话
	private String contactNumber;
	// 联系人
	private String contacts;

	public static ChainUpdateInfoForm newInstance() {
		return new ChainUpdateInfoForm();
	}

	public long getChainId() {
		return chainId;
	}

	public void setChainId(long chainId) {
		this.chainId = chainId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

}

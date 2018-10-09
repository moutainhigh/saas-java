package com.hq.chainStore.service.store.apiData;

public class StoreAddApiForm {
	
	private long bossId;
	
	private String name;
	
	private String area;
	
	private String address;
	
	private String tel;
	
	private String descript;
	
	//推荐人
	private String recommender;
	
	private long recomPhone;
	
	private String company;
	
	private String companyArea;
	
	private String companyAddress;
	
	private String licenseImg;
	
	
	public static StoreAddApiForm newInstance(){
		return new StoreAddApiForm();
	}


	public long getBossId() {
		return bossId;
	}


	public StoreAddApiForm setBossId(long bossId) {
		this.bossId = bossId;
		return this;
	}


	public String getName() {
		return name;
	}


	public StoreAddApiForm setName(String name) {
		this.name = name;
		return this;
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


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getDescript() {
		return descript;
	}


	public void setDescript(String descript) {
		this.descript = descript;
	}


	public String getRecommender() {
		return recommender;
	}


	public void setRecommender(String recommender) {
		this.recommender = recommender;
	}


	public long getRecomPhone() {
		return recomPhone;
	}


	public void setRecomPhone(long recomPhone) {
		this.recomPhone = recomPhone;
	}


	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}


	public String getCompanyArea() {
		return companyArea;
	}


	public void setCompanyArea(String companyArea) {
		this.companyArea = companyArea;
	}


	public String getCompanyAddress() {
		return companyAddress;
	}


	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}


	public String getLicenseImg() {
		return licenseImg;
	}


	public void setLicenseImg(String licenseImg) {
		this.licenseImg = licenseImg;
	}

	
	
	
}

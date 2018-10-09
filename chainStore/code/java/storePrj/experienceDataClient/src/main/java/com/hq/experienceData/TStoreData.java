package com.hq.experienceData;

import java.util.ArrayList;
import java.util.List;

public class TStoreData {
	private String wechatPath;
	private String aliPath;
	private String name;
	private String area;
	private String address;
	private String tel;
	private String company;
	private String companyAddress;
	private String descript;
	
	private String recommender;
	private long recomPhone;
	private String companyArea;
	private String licenseImg;
	
	
	public static List<TStoreData> buildTStoreData(){
		List<TStoreData> list = new ArrayList<TStoreData>();
		
		TStoreData data1 = new TStoreData();
		data1.setName("体验店一号");
		data1.setArea("北京/东城区");
		data1.setAddress("北京市东城区42号");
		data1.setTel("010-56370068");
		data1.setCompany("北京智美通信息技术有限公司");
		data1.setCompanyAddress("北京市东城区42号");
		data1.setDescript("体验店店铺介绍");
		data1.setWechatPath("weChat.jpg");
		data1.setAliPath("aliPay.jpg");
		list.add(data1);
		
		TStoreData data2 = new TStoreData();
		data2.setName("体验店二号");
		data2.setArea("广州/天河区");
		data2.setAddress("天河区体育西路190号");
		data2.setTel("020-56340056");
		data2.setCompany("北京智美通信息技术有限公司");
		data2.setCompanyAddress("天河区体育西路190号");
		data2.setDescript("体验店二号店铺介绍");
		data2.setWechatPath("weChat.jpg");
		data2.setAliPath("aliPay.jpg");
		list.add(data2);
		
		TStoreData data3 = new TStoreData();
		data3.setName("体验店三号");
		data3.setArea("佛山/禅城区");
		data3.setAddress("佛山市禅城区88号");
		data3.setTel("030-56370543");
		data3.setCompany("北京智美通信息技术有限公司");
		data3.setCompanyAddress("佛山市禅城区88号");
		data3.setDescript("体验店三号店铺介绍");
		data3.setWechatPath("weChat.jpg");
		data3.setAliPath("aliPay.jpg");
		list.add(data3);
		
		return list;
	}
	
	public static TStoreData newInstance(long mark){
		TStoreData data = new TStoreData();
		data.setName("智美店"+mark+"号");
		data.setArea("广州/天河区");
		data.setAddress("天河区体育西路"+mark+"号");
		data.setTel("020-56340"+mark);
		data.setCompany("智美通"+mark+"号");
		data.setCompanyAddress("天河区体育西路"+mark+"号");
		data.setDescript("店铺介绍:医美光学");
		data.setWechatPath("weChat.jpg");
		data.setAliPath("aliPay.jpg");
		return data;
	} 

	public String getWechatPath() {
		return wechatPath;
	}

	public void setWechatPath(String wechatPath) {
		this.wechatPath = wechatPath;
	}

	public String getAliPath() {
		return aliPath;
	}

	public void setAliPath(String aliPath) {
		this.aliPath = aliPath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
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

	public String getCompanyArea() {
		return companyArea;
	}

	public void setCompanyArea(String companyArea) {
		this.companyArea = companyArea;
	}

	public String getLicenseImg() {
		return licenseImg;
	}

	public void setLicenseImg(String licenseImg) {
		this.licenseImg = licenseImg;
	}
}

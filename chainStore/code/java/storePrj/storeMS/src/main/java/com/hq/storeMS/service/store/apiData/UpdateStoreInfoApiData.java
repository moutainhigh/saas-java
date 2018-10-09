package com.hq.storeMS.service.store.apiData;

import java.util.ArrayList;
import java.util.List;

import com.hq.storeMS.service.store.data.Store;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class UpdateStoreInfoApiData {

	private long storeId;

	private String name;

	private String descript;

	private String area;

	private String address;

	private String tel;

	// 推荐人
	private String recommender;

	private long recomPhone;

	private String company;

	private String companyArea;

	private String companyAddress;

	private String licenseImg;

	// 微信二维码
	private String wechatRecommendImg;
	// 宣传图片 列表
	private List<String> disseminateImgs = new ArrayList<String>();

	public static UpdateStoreInfoApiData newInstance() {
		return new UpdateStoreInfoApiData();
	}

	public void update(Store store) {
		FastBeanCopyer.getInstance().copy(this, store);
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWechatRecommendImg() {
		return wechatRecommendImg;
	}

	public void setWechatRecommendImg(String wechatRecommendImg) {
		this.wechatRecommendImg = wechatRecommendImg;
	}

	public List<String> getDisseminateImgs() {
		return disseminateImgs;
	}

	public void setDisseminateImgs(List<String> disseminateImgs) {
		this.disseminateImgs = disseminateImgs;
	}

}

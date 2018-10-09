package com.hq.chainStore.service.saas.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.chainStore.service.store.data.StoreState;
import com.hq.common.dataSyn.bs.IntfSynData;

@Table(name = "op/store")
public class OPStore implements IntfSynData{

	@Id
	private long id;
	
	private long bossId;
	
	private String name;
	
	private long createdTime;
	
	private long lastUpdateTime;
	
	private String descript;
	
	private int state;
	
	private String clerkInfoId;
	
	private long ver;
	
	private String area;
	
	private String address;
	
	private String tel;
	
	//推荐人
	private String recommender;
	
	private long recomPhone;
	
	private String company;
	
	private String companyArea;
	
	private String companyAddress;
	
	private String licenseImg;
	
	// 加入店铺二维码
	private String joinStoreImg;
	// 微信支付二维码
	private String wechatImg;
	// 支付宝二维码
	private String alipayImg;
	// 微信二维码
	private String wechatRecommendImg;
	// 宣传图片 列表
	private List<String> disseminateImgs = new ArrayList<String>();
	
	public static OPStore New(long id, String name){
		OPStore store = new OPStore();
		store.id = id;
		store.name = name;
		long curTime = System.currentTimeMillis();
		
		store.createdTime = curTime;
		store.lastUpdateTime = curTime;
		return store;
	}
	
	@Override
	public Object targetId() {
		return this.id;
	}

	@Override
	public long targetVer() {
		return this.ver;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public long getVer() {
		return ver;
	}

	public void setVer(long ver) {
		this.ver = ver;
	}

	public long getBossId() {
		return bossId;
	}

	public void setBossId(long bossId) {
		this.bossId = bossId;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public StoreState getStateEnum() {
		return StoreState.valueOf(this.state);
	}
	
	public void setStateEnum(StoreState state) {
		this.state = state.ordinal();
	}

	public String getClerkInfoId() {
		return clerkInfoId;
	}

	public void setClerkInfoId(String clerkInfoId) {
		this.clerkInfoId = clerkInfoId;
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

	public String getJoinStoreImg() {
		return joinStoreImg;
	}

	public void setJoinStoreImg(String joinStoreImg) {
		this.joinStoreImg = joinStoreImg;
	}

	public String getWechatImg() {
		return wechatImg;
	}

	public void setWechatImg(String wechatImg) {
		this.wechatImg = wechatImg;
	}

	public String getAlipayImg() {
		return alipayImg;
	}

	public void setAlipayImg(String alipayImg) {
		this.alipayImg = alipayImg;
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

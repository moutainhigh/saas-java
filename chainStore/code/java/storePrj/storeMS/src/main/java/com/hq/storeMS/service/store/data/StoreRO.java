package com.hq.storeMS.service.store.data;

import java.util.List;
import java.util.Set;


public interface StoreRO {
	public long getId();
	public long getBossId();
	public String getName();
	public String getClerkInfoId();
	public long getCreatedTime();
	public long getLastUpdateTime();
	public long getVer();
	public int getState();
	public String getDescript();
	public String getArea();
	public String getAddress();
	public String getTel();
	public String getRecommender();
	public long getRecomPhone();
	public String getCompany();
	public String getCompanyArea();
	public String getCompanyAddress();
	public String getLicenseImg();
	public String getJoinStoreImg();
	public String getWechatImg();
	public String getAcodeImg();
	public String getAlipayImg();
	public String getWechatRecommendImg();
	public List<String> getDisseminateImgs();
	public Set<Long> getChainIds();
}

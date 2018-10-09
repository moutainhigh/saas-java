package com.hq.storeManagerMS.service.muser.apiData;

import com.hq.storeManagerMS.service.muser.data.MUser;
import com.zenmind.common.beanCopy.FastBeanCopyer;


public class MUserUpdateInfoApiData {
	private long muserId;

	private String name;

	private String headImg;

	private int gender;

	public static MUserUpdateInfoApiData newInstance() {
		return new MUserUpdateInfoApiData();
	}
	
	public void updateMUser(MUser data){
		FastBeanCopyer.getInstance().copy(this, data);
	}

	public long getMuserId() {
		return muserId;
	}

	public void setMuserId(long muserId) {
		this.muserId = muserId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

}

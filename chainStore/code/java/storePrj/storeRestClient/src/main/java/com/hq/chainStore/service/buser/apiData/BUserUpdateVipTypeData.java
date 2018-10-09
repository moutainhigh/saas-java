package com.hq.chainStore.service.buser.apiData;

public class BUserUpdateVipTypeData {
	//对应会员id buserId
	private long id;
	//会员类型
	private int vipType;

	public static BUserUpdateVipTypeData newInstance(){
		return new BUserUpdateVipTypeData();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getVipType() {
		return vipType;
	}

	public void setVipType(int vipType) {
		this.vipType = vipType;
	}

}

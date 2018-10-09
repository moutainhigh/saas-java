package com.hq.storeMS.service.storeConfig.data.leaguer;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class LeaguerOriginConfig {
	private int id;

	private String originName;

	public static LeaguerOriginConfig newInstance() {
		LeaguerOriginConfig data = new LeaguerOriginConfig();
		return data;
	}
	
	public static LeaguerOriginConfig newInstance(SysInitLeaguerOriginEnum originEnum) {
		LeaguerOriginConfig data = newInstance();
		data.originName = originEnum.getOriginName();
		data.id = originEnum.ordinal()+1;
		return data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

}

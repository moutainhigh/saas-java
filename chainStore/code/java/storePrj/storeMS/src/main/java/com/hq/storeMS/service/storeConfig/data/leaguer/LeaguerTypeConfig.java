package com.hq.storeMS.service.storeConfig.data.leaguer;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class LeaguerTypeConfig {
	private int id;
	// 类型名称
	private String typeName;
	//比较符 对应CompareEnum
	private int compare;
	// 最后到店的客户  天数20天、60天
	private int consumeDates;

	public static LeaguerTypeConfig newInstance() {
		LeaguerTypeConfig data = new LeaguerTypeConfig();
		return data;
	}
	
	public static LeaguerTypeConfig newInstance(SysInitLeaguerTypeEnum sysInitLeaguerTypeEnum) {
		LeaguerTypeConfig data = newInstance();
		data.id=sysInitLeaguerTypeEnum.ordinal()+1;
		data.typeName=sysInitLeaguerTypeEnum.getTypeName();
		data.consumeDates=sysInitLeaguerTypeEnum.getConsumeDates();
		data.compare = sysInitLeaguerTypeEnum.getCompare();
		return data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getConsumeDates() {
		return consumeDates;
	}

	public void setConsumeDates(int consumeDates) {
		this.consumeDates = consumeDates;
	}

	public int getCompare() {
		return compare;
	}

	public void setCompare(int compare) {
		this.compare = compare;
	}

}

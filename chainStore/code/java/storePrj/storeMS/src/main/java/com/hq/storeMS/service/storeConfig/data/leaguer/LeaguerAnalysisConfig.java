package com.hq.storeMS.service.storeConfig.data.leaguer;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class LeaguerAnalysisConfig {
	private int id;
	// 类型名称
	private String name;
	//阈值
	private long threshold;
	//比较符 对应 CompareEnum
	private int compare;

	public static LeaguerAnalysisConfig newInstance() {
		LeaguerAnalysisConfig data = new LeaguerAnalysisConfig();
		return data;
	}
	
	public static LeaguerAnalysisConfig newInstance(SysInitLeaguerAnalysisEnum sysInitLeaguerAnalysisEnum) {
		LeaguerAnalysisConfig data = newInstance();
		data.id=sysInitLeaguerAnalysisEnum.ordinal()+1;
		data.name=sysInitLeaguerAnalysisEnum.getName();
		data.threshold=sysInitLeaguerAnalysisEnum.getThreshold();
		data.compare = sysInitLeaguerAnalysisEnum.getCompare();
		return data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getThreshold() {
		return threshold;
	}

	public void setThreshold(long threshold) {
		this.threshold = threshold;
	}

	public int getCompare() {
		return compare;
	}

	public void setCompare(int compare) {
		this.compare = compare;
	}

}

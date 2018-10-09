package com.hq.storeClient.service.storeConfig.data.leaguer;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class LeaguerBirthdayConfig {
	// 天数
	private int count;
	// 是否启用  UseFlagEnum
	private int useFlag;

	public static LeaguerBirthdayConfig newInstance() {
		LeaguerBirthdayConfig data = new LeaguerBirthdayConfig();
		return data;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(int useFlag) {
		this.useFlag = useFlag;
	}

}

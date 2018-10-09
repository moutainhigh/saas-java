package com.hq.storeMS.service.storeConfig.data.leaguer;

import com.hq.storeMS.service.common.UseFlagEnum;
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
	
	public static LeaguerBirthdayConfig newInstance(SysInitBirthdayEnum birthdayEnum) {
		LeaguerBirthdayConfig data = newInstance();
		data.count = birthdayEnum.getCount();
		data.useFlag = UseFlagEnum.Enable.ordinal();
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

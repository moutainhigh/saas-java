package com.hq.payms.service.bossPayInfo.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class BossPayInfoCount {
	//总记录数
	private long count;
	
	public static BossPayInfoCount newInstance() {
		BossPayInfoCount countObj = new BossPayInfoCount();
		return countObj;
	}
	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

}

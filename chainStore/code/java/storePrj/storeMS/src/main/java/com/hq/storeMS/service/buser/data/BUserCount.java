package com.hq.storeMS.service.buser.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class BUserCount {
	//总记录数
	private long count;
	
	public static BUserCount newInstance() {
		BUserCount data = new BUserCount();
		return data;
	}
	
	public static BUserCount newInstance(long countP) {
		BUserCount data = newInstance();
		data.count = countP;
		return data;
	}
	
	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
}

package com.hq.storeClient.service.message.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class MsgUnReadCount {
	private long buserId;
	private int count;

	public static MsgUnReadCount newInstance() {
		MsgUnReadCount data = new MsgUnReadCount();
		return data;
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}

package com.hq.chainStore.pressTest.common;

import java.util.concurrent.atomic.AtomicLong;

import com.zenmind.common.StringFormatUtil;

public class JsonUtilRecorder {
	// 最长耗时
	private long maxCost;

	private AtomicLong tpsCount = new AtomicLong(0);
	private AtomicLong costCount = new AtomicLong(0);
	
	private static JsonUtilRecorder instance = new JsonUtilRecorder();
	
	public static JsonUtilRecorder getInstance(){
		return instance;
	}

	public void add(long costInMs) {
		tpsCount.incrementAndGet();
		costCount.addAndGet(costInMs);

		if (costInMs > this.maxCost) {
			this.maxCost = costInMs;
		}
	}
	
	
	public void reset() {
		tpsCount.set(0);
		costCount.set(0);
		this.maxCost = 0;
	}

	public String getInfo() {
		long costCoutTmp = costCount.get();
		long tpsCountTmp = tpsCount.get();

		long tps = costCoutTmp != 0 ? (tpsCountTmp * 1000 / costCoutTmp) : 0;
		long avgCost = tpsCountTmp != 0 ? (costCoutTmp / tpsCountTmp) : 0;
		String format = "tps:{} avgCost:{}ms maxCost:{}ms total:{}";
		String info = StringFormatUtil.format(format, tps, avgCost, this.maxCost, tpsCountTmp);
		return info;

	}
}

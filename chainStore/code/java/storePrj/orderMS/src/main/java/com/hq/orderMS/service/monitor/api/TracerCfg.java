package com.hq.orderMS.service.monitor.api;

import com.zenmind.common.json.JsonUtil;

public class TracerCfg {

	private int queueSize = 20;
	
	private int methodMinTime = 200;
	
	private int threadMinTime = 3000;

	public int getQueueSize() {
		return queueSize;
	}

	public void setQueueSize(int queueSize) {
		this.queueSize = queueSize;
	}

	public int getMethodMinTime() {
		return methodMinTime;
	}

	public void setMethodMinTime(int methodMinTime) {
		this.methodMinTime = methodMinTime;
	}

	public int getThreadMinTime() {
		return threadMinTime;
	}

	public void setThreadMinTime(int threadMinTime) {
		this.threadMinTime = threadMinTime;
	}
	
	public static void main(String[] args) {
		String json = JsonUtil.getInstance().toJson(new TracerCfg());
		System.out.println(json);
	}
	
	
	
}

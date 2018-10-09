package com.hq.taskMS.common.config;

public class StoreTaskMSCfgMgr {

	private static StoreTaskMSCfg instance;

	public static StoreTaskMSCfg getProp() {
		return instance;
	}

	public static void setCfg(StoreTaskMSCfg instanceP) {
		instance = instanceP;
	}
}

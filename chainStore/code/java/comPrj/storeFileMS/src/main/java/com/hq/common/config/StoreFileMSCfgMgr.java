package com.hq.common.config;

public class StoreFileMSCfgMgr {
	
	private static StoreFileMSCfg instance;

	public static StoreFileMSCfg getProp() {
		return instance;
	}

	public static void setCfg(StoreFileMSCfg instanceP) {
		instance = instanceP;
	}
}

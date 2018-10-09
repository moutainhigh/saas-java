package com.hq.common.config;

public class StoreLogMSCfgMgr {
	
	private static StoreLogMSCfg instance;

	public static StoreLogMSCfg getProp() {
		return instance;
	}

	public static void setCfg(StoreLogMSCfg instanceP) {
		instance = instanceP;
	}
}

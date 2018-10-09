package com.hq.storeMS.common.config;

public class StoreMSCfgMgr {
	
	private static StoreMSCfg instance;

	public static StoreMSCfg getProp() {
		return instance;
	}

	public static void setCfg(StoreMSCfg instanceP) {
		instance = instanceP;
	}
}

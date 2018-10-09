package com.hq.storeManagerMS.common.config;

public class StoreMngMSCfgMgr {
	
	private static StoreMngMSCfg instance;

	public static StoreMngMSCfg getProp() {
		return instance;
	}

	public static void setCfg(StoreMngMSCfg instanceP) {
		instance = instanceP;
	}
}

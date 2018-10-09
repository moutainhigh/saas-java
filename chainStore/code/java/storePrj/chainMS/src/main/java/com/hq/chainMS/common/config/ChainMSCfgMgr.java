package com.hq.chainMS.common.config;

public class ChainMSCfgMgr {
	
	private static ChainMSCfg instance;

	public static ChainMSCfg getProp() {
		return instance;
	}

	public static void setCfg(ChainMSCfg instanceP) {
		instance = instanceP;
	}
}

package com.hq.payms.common.config;

public class PayMSCfgMgr {
	
	private static PayMSCfg instance;

	public static PayMSCfg getProp() {
		return instance;
	}

	public static void setCfg(PayMSCfg instanceP) {
		instance = instanceP;
	}
}

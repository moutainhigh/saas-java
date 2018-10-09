package com.hq.customerMS.common.config;

public class CustomerMSCfgMgr {
	
	private static CustomerMSCfg instance;

	public static CustomerMSCfg getProp() {
		return instance;
	}

	public static void setCfg(CustomerMSCfg instanceP) {
		instance = instanceP;
	}
}

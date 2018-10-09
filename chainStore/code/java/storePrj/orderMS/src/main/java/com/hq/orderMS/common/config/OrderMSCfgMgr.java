package com.hq.orderMS.common.config;

public class OrderMSCfgMgr {
	
	private static OrderMSCfg instance;

	public static OrderMSCfg getProp() {
		return instance;
	}

	public static void setCfg(OrderMSCfg instanceP) {
		instance = instanceP;
	}
}

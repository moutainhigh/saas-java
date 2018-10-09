package com.hq.thirdPartyServer.common.config;

public class ThirdPartyServerCfgMgr {
	
	private static ThirdPartyServerCfg instance;

	public static ThirdPartyServerCfg getProp() {
		return instance;
	}

	public static void setCfg(ThirdPartyServerCfg instanceP) {
		instance = instanceP;
	}
}

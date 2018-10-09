package com.hq.thirdPartyClient.service.common;

public class ThirdPartyClientCfg {
	private static String thirdPartyServer = "http://localhost:9119/ws/v1";

	public static void init(String... services) {
		String[] ss = { "http://localhost:9119/ws/v1" };
		for (int i = 0; i < services.length && i < ss.length; i++) {
			ss[i] = services[i];
		}
		ThirdPartyClientCfg.thirdPartyServer = ss[0];
	}

	public static String getThirdPartyServer() {
		return thirdPartyServer;
	}

	public static void setThirdPartyServer(String thirdPartyServer) {
		ThirdPartyClientCfg.thirdPartyServer = thirdPartyServer;
	}
}

package com.hq.payRestClient.service.common;

public class PayClientCfg {
	private static String payServer = "http://localhost:9131/payms/ws/v1";

	public static void init(String serverUrl) {
		PayClientCfg.payServer = serverUrl;
	}

	public static String getPayServer() {
		return payServer;
	}
}

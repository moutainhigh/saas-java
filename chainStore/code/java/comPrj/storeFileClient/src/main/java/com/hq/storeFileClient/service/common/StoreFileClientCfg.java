package com.hq.storeFileClient.service.common;

public class StoreFileClientCfg {
	private static String storeFileServer = "http://localhost:9119/ws/v1";

	public static void init(String... services) {
		String[] ss = { "http://localhost:9119/ws/v1" };
		for (int i = 0; i < services.length && i < ss.length; i++) {
			ss[i] = services[i];
		}
		StoreFileClientCfg.storeFileServer = ss[0];
	}

	public static String getStoreFileServer() {
		return storeFileServer;
	}

	public static void setStoreFileServer(String storeFileServer) {
		StoreFileClientCfg.storeFileServer = storeFileServer;
	}
}

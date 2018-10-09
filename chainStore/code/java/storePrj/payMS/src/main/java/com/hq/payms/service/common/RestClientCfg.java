package com.hq.payms.service.common;

public class RestClientCfg {

	private static String storeService = "http://localhost:9114/ws/v1";
	private static String reportService = "http://localhost:9117/ws/v1";
	

	public static void init(String...services) {
		String[] ss={"http://localhost:9114/ws/v1", "http://localhost:9117/ws/v1"};
		for (int i = 0; i < services.length && i < ss.length; i++) {
			ss[i] = services[i];
		}
		RestClientCfg.storeService = ss[0];
		RestClientCfg.reportService = ss[1];
	}
	
	public static String getStoreService() {
		return storeService;
	}

	public static void setStoreService(String storeService) {
		RestClientCfg.storeService = storeService;
	}

	public static String getReportService() {
		return reportService;
	}

	public static void setReportService(String reportService) {
		RestClientCfg.reportService = reportService;
	}
}

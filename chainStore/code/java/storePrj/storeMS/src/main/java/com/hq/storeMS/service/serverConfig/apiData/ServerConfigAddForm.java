package com.hq.storeMS.service.serverConfig.apiData;

import java.util.HashMap;
import java.util.Map;

public class ServerConfigAddForm {
	// 应用名称 中文
	private String appNameCh;
	// 应用名称 英文
	private String appNameEn;
	// 应用版本
	private String appVersion;
	// 图片服务根路径
	private String imgHost;
	// storems服务根路径
	private String storeService;
	// orderms服务根路径
	private String orderService;
	// customerms服务根路径
	private String customerService;
	// 店铺推广链接
	private String generalizeUrl;
	// 其他属性
	private Map<String, String> attributeMap = new HashMap<String, String>();

	public static ServerConfigAddForm newInstance() {
		ServerConfigAddForm data = new ServerConfigAddForm();
		return data;
	}

	public String getAppNameCh() {
		return appNameCh;
	}

	public void setAppNameCh(String appNameCh) {
		this.appNameCh = appNameCh;
	}

	public String getAppNameEn() {
		return appNameEn;
	}

	public void setAppNameEn(String appNameEn) {
		this.appNameEn = appNameEn;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getStoreService() {
		return storeService;
	}

	public void setStoreService(String storeService) {
		this.storeService = storeService;
	}

	public String getOrderService() {
		return orderService;
	}

	public void setOrderService(String orderService) {
		this.orderService = orderService;
	}

	public String getCustomerService() {
		return customerService;
	}

	public void setCustomerService(String customerService) {
		this.customerService = customerService;
	}

	public Map<String, String> getAttributeMap() {
		return attributeMap;
	}

	public void setAttributeMap(Map<String, String> attributeMap) {
		this.attributeMap = attributeMap;
	}

	public String getImgHost() {
		return imgHost;
	}

	public void setImgHost(String imgHost) {
		this.imgHost = imgHost;
	}

	public String getGeneralizeUrl() {
		return generalizeUrl;
	}

	public void setGeneralizeUrl(String generalizeUrl) {
		this.generalizeUrl = generalizeUrl;
	}

}

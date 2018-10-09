package com.hq.storeClient.service.serverConfig.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dao.classinfo.IndexField;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "serverConfig")
public class ServerConfig {
	@Id
	private long id;

	// 应用名称 中文
	@IndexField
	private String appNameCh;
	// 应用名称 英文
	@IndexField
	private String appNameEn;
	// 应用版本
	@IndexField
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

	private long createdTime;
	private long lastUpdateTime;
	private int ver;

	public static ServerConfig newInstance() {
		ServerConfig data = new ServerConfig();
		return data;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public int getVer() {
		return ver;
	}

	public void setVer(int ver) {
		this.ver = ver;
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

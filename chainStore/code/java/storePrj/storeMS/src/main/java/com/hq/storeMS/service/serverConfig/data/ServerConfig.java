package com.hq.storeMS.service.serverConfig.data;

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
		long nowTime = System.currentTimeMillis();
		data.createdTime=nowTime;
		data.lastUpdateTime=nowTime;
		return data;
	}
	
	//根据环境，创建智美通APP的配置
	public static ServerConfig newZmtConfigByEnv(String baseUrl, String appVersion) {
		ServerConfig data = newInstance();
		data.appNameCh = "智美通";
		data.appNameEn = "zhimeitong";
		data.appVersion = appVersion;
		data.imgHost = baseUrl;
		data.storeService = baseUrl+"/storems/ws/v1";
		data.orderService = baseUrl+"/orderms/ws/v1";
		data.customerService = baseUrl+"/customerms/ws/v1";
		
		//加载遮盖的时间 单位：秒
		data.attributeMap.put("showTime", "7");
		//智美通 logo地址
		data.attributeMap.put("logoPath", "img/logo/store/zhimeitong-logo.png");
		//会员卡图片个数
		data.attributeMap.put("MembershipCardCount", "6");
		//次数图片个数
		data.attributeMap.put("ConsumptionCardCount", "5");
		//protocol 协议链接
		data.attributeMap.put("protocolUrl", baseUrl+"/protocol/index.html");
		data.attributeMap.put("devStoreService", "https://www.zhimeitimes.com/dev/storems/ws/v1");
		data.attributeMap.put("devImgHost", "https://www.zhimeitimes.com");
		return data;
	}

	public void incrVer() {
		this.ver = ver + 1;
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

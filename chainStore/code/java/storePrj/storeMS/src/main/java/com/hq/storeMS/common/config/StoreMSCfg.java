package com.hq.storeMS.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.dao.mongodb.MongodbCfg;
import com.zenmind.dao.redis.RedisConfig;
import com.zenmind.umeng.ModeTypeEnum;
import com.zenmind.umeng.UmengConfig;
import com.zenmind.wx.WxConfig;

/**
 * @author zenmind 自定义属性对应的实体类 spring
 *         boot会将配置文件中自定义的属性值，自动设置到该类对应的属性上，使用的使用直接注入该类即可 prefix用来指定自定义属性值的前缀
 */
@ConfigurationProperties(prefix = "storems.prop")
public class StoreMSCfg {
	private String envMark;

	private int hystrixTimeoutInMillions;
	private long verifyCodeExpireTime;// 验证码有效时长
	private boolean genDataEnabled = true;// 是否开启定时器
	private boolean smsCodeOpen = false;// 短信验证开关
	private boolean activateCodeOpen = false;// 激活码验证开关
	private boolean monitorEnabled = true;// Dao性能监控默认开关

	// 第三方微服务
	private String logoPath;
	private String fileHost;
	private String thirdPartyHost;
	private String orderHost;
	private String iotHost;
	private String payHost;
	private String chainHost;
	private String mngHost;
	private String customerHost;

	// 缓存配置
	private boolean redisEnabled = true;// Redis缓存开关
	private long timeOutInSeconds = 3600L;// redis缓存有效时间
	private long redisGuavaSize = 10000L;// 缓存个数
	private long redisGuavaDuration = 3600L;// 缓存时间
	private long storeGuavaSize = 10000L;// 店铺相关单体缓存个数
	private long storeGuavaDuration = 3600L;// 店铺相关单体缓存时间
	private long shardGuavaSize = 10000L;// 列表相关缓存个数
	private long shardGuavaDuration = 3600L;// 列表相关缓存时间

	// mogodb 配置
	private String mongoHost;
	private int mongoPort;
	private String mongoDbName;
	private int mongoConnectionsPerHost;
	private int mongoThreadsAllowedToBlockForConnectionMultiplier;
	private int mongoConnectTimeout;
	private int mongoHeartbeatSocketTimeout;
	private int mongoMaxWaitTime;
	private boolean mongoSocketKeepAlive;
	private int mongoSocketTimeout;
	private String mongoUserName;
	private String mongoPassword;
	private boolean autoDeleteIndex = true;

	// Rabbit logger 配置
	private boolean logEnabled = true;
	private String logHost;
	private int logPort;
	private String logUsername;
	private String logPassword;
	private String logExchange;
	private String logQueue;
	private String logRouteKey;
	private int logChannelCacheSize;
	private boolean logPesistent;

	// Rabbit task 配置
	private boolean taskEnabled = true;
	private String taskHost;
	private int taskPort;
	private String taskUsername;
	private String taskPassword;
	private String taskExchange;
	private String taskQueue;
	private String taskRouteKey;
	private int taskChannelCacheSize;
	private boolean taskPesistent;

	// Rabbit event 配置
	private boolean eventEnabled = true;
	private String eventHost;
	private int eventPort;
	private String eventUsername;
	private String eventPassword;
	private String eventExchange;
	private String eventQueue;
	private String eventRouteKey;
	private int eventChannelCacheSize;
	private boolean eventPesistent;

	// 微信接口配置
	private boolean wxEnabled;

	// 微信智美预约小程序配置
	private String wxAppId;
	private String wxSecret;
	private String wxAcodePage;
	
	private String wxTemplateId;
	private String wxPage;
	private int wxNoticeSenderNThread;
	
	// 微信主体公众号"智美通"配置
	private String wxMainAppId;
	private String wxMainSecret;
	
	// 微信小程序 医美智能助手
	private String wxMarketingAppId;
	private String wxMarketingSecret;

	// 友盟推送配置
	private boolean umengEnabled = true;
	private String appAndroidKey;
	private String appAndroidMasterSecret;
	private String appIosKey;
	private String appIosMasterSecret;
	private int modeTypeIndex;

	public MongodbCfg getMongodbCfg() {
		MongodbCfg mongodbCfg = new MongodbCfg();
		FastBeanCopyer.getInstance().copy(this, mongodbCfg);
		mongodbCfg.setAutoDeleteIndex(this.autoDeleteIndex);
		return mongodbCfg;
	}

	public WxConfig getWxConfig() {
		WxConfig wxConfig = WxConfig.newInstance();
		wxConfig.setAppid(wxAppId);
		wxConfig.setSecret(wxSecret);
		wxConfig.setOpen(wxEnabled);
		return wxConfig;
	}

	public RedisConfig getRedisConfig() {
		RedisConfig redisConfig = new RedisConfig();
		FastBeanCopyer.getInstance().copy(this, redisConfig);
		return redisConfig;
	}

	public UmengConfig getUmengConfig() {
		UmengConfig umengConfig = UmengConfig.newInstance();
		FastBeanCopyer.getInstance().copy(this, umengConfig);
		if (this.modeTypeIndex == 0) {
			umengConfig.setModeType(ModeTypeEnum.TESE_MODE);
		} else {
			umengConfig.setModeType(ModeTypeEnum.PRD_MODE);
		}
		return umengConfig;
	}

	public String getEnvMark() {
		return envMark;
	}

	public void setEnvMark(String envMark) {
		this.envMark = envMark;
	}

	public String getMongoHost() {
		return mongoHost;
	}

	public void setMongoHost(String mongoHost) {
		this.mongoHost = mongoHost;
	}

	public int getMongoPort() {
		return mongoPort;
	}

	public void setMongoPort(int mongoPort) {
		this.mongoPort = mongoPort;
	}

	public String getMongoDbName() {
		return mongoDbName;
	}

	public void setMongoDbName(String mongoDbName) {
		this.mongoDbName = mongoDbName;
	}

	public int getMongoConnectionsPerHost() {
		return mongoConnectionsPerHost;
	}

	public void setMongoConnectionsPerHost(int mongoConnectionsPerHost) {
		this.mongoConnectionsPerHost = mongoConnectionsPerHost;
	}

	public int getMongoThreadsAllowedToBlockForConnectionMultiplier() {
		return mongoThreadsAllowedToBlockForConnectionMultiplier;
	}

	public void setMongoThreadsAllowedToBlockForConnectionMultiplier(
			int mongoThreadsAllowedToBlockForConnectionMultiplier) {
		this.mongoThreadsAllowedToBlockForConnectionMultiplier = mongoThreadsAllowedToBlockForConnectionMultiplier;
	}

	public int getMongoConnectTimeout() {
		return mongoConnectTimeout;
	}

	public void setMongoConnectTimeout(int mongoConnectTimeout) {
		this.mongoConnectTimeout = mongoConnectTimeout;
	}

	public int getMongoHeartbeatSocketTimeout() {
		return mongoHeartbeatSocketTimeout;
	}

	public void setMongoHeartbeatSocketTimeout(int mongoHeartbeatSocketTimeout) {
		this.mongoHeartbeatSocketTimeout = mongoHeartbeatSocketTimeout;
	}

	public int getMongoMaxWaitTime() {
		return mongoMaxWaitTime;
	}

	public void setMongoMaxWaitTime(int mongoMaxWaitTime) {
		this.mongoMaxWaitTime = mongoMaxWaitTime;
	}

	public boolean isMongoSocketKeepAlive() {
		return mongoSocketKeepAlive;
	}

	public void setMongoSocketKeepAlive(boolean mongoSocketKeepAlive) {
		this.mongoSocketKeepAlive = mongoSocketKeepAlive;
	}

	public int getMongoSocketTimeout() {
		return mongoSocketTimeout;
	}

	public void setMongoSocketTimeout(int mongoSocketTimeout) {
		this.mongoSocketTimeout = mongoSocketTimeout;
	}

	public String getMongoUserName() {
		return mongoUserName;
	}

	public void setMongoUserName(String mongoUserName) {
		this.mongoUserName = mongoUserName;
	}

	public String getMongoPassword() {
		return mongoPassword;
	}

	public void setMongoPassword(String mongoPassword) {
		this.mongoPassword = mongoPassword;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public int getHystrixTimeoutInMillions() {
		return hystrixTimeoutInMillions;
	}

	public void setHystrixTimeoutInMillions(int hystrixTimeoutInMillions) {
		this.hystrixTimeoutInMillions = hystrixTimeoutInMillions;
	}

	public String getFileHost() {
		return fileHost;
	}

	public void setFileHost(String fileHost) {
		this.fileHost = fileHost;
	}

	public String getThirdPartyHost() {
		return thirdPartyHost;
	}

	public void setThirdPartyHost(String thirdPartyHost) {
		this.thirdPartyHost = thirdPartyHost;
	}

	public boolean isGenDataEnabled() {
		return genDataEnabled;
	}

	public void setGenDataEnabled(boolean genDataEnabled) {
		this.genDataEnabled = genDataEnabled;
	}

	public boolean isRedisEnabled() {
		return redisEnabled;
	}

	public void setRedisEnabled(boolean redisEnabled) {
		this.redisEnabled = redisEnabled;
	}

	public long getVerifyCodeExpireTime() {
		return verifyCodeExpireTime;
	}

	public void setVerifyCodeExpireTime(long verifyCodeExpireTime) {
		this.verifyCodeExpireTime = verifyCodeExpireTime;
	}

	public boolean isSmsCodeOpen() {
		return smsCodeOpen;
	}

	public void setSmsCodeOpen(boolean smsCodeOpen) {
		this.smsCodeOpen = smsCodeOpen;
	}

	public String getOrderHost() {
		return orderHost;
	}

	public void setOrderHost(String orderHost) {
		this.orderHost = orderHost;
	}

	public boolean isActivateCodeOpen() {
		return activateCodeOpen;
	}

	public void setActivateCodeOpen(boolean activateCodeOpen) {
		this.activateCodeOpen = activateCodeOpen;
	}

	public String getLogExchange() {
		return logExchange;
	}

	public void setLogExchange(String logExchange) {
		this.logExchange = logExchange;
	}

	public String getLogRouteKey() {
		return logRouteKey;
	}

	public void setLogRouteKey(String logRouteKey) {
		this.logRouteKey = logRouteKey;
	}

	public int getLogChannelCacheSize() {
		return logChannelCacheSize;
	}

	public void setLogChannelCacheSize(int logChannelCacheSize) {
		this.logChannelCacheSize = logChannelCacheSize;
	}

	public boolean isLogPesistent() {
		return logPesistent;
	}

	public void setLogPesistent(boolean logPesistent) {
		this.logPesistent = logPesistent;
	}

	public String getTaskExchange() {
		return taskExchange;
	}

	public void setTaskExchange(String taskExchange) {
		this.taskExchange = taskExchange;
	}

	public String getTaskRouteKey() {
		return taskRouteKey;
	}

	public void setTaskRouteKey(String taskRouteKey) {
		this.taskRouteKey = taskRouteKey;
	}

	public int getTaskChannelCacheSize() {
		return taskChannelCacheSize;
	}

	public void setTaskChannelCacheSize(int taskChannelCacheSize) {
		this.taskChannelCacheSize = taskChannelCacheSize;
	}

	public boolean isTaskPesistent() {
		return taskPesistent;
	}

	public void setTaskPesistent(boolean taskPesistent) {
		this.taskPesistent = taskPesistent;
	}

	public String getEventExchange() {
		return eventExchange;
	}

	public void setEventExchange(String eventExchange) {
		this.eventExchange = eventExchange;
	}

	public String getEventRouteKey() {
		return eventRouteKey;
	}

	public void setEventRouteKey(String eventRouteKey) {
		this.eventRouteKey = eventRouteKey;
	}

	public int getEventChannelCacheSize() {
		return eventChannelCacheSize;
	}

	public void setEventChannelCacheSize(int eventChannelCacheSize) {
		this.eventChannelCacheSize = eventChannelCacheSize;
	}

	public boolean isEventPesistent() {
		return eventPesistent;
	}

	public void setEventPesistent(boolean eventPesistent) {
		this.eventPesistent = eventPesistent;
	}

	public String getIotHost() {
		return iotHost;
	}

	public void setIotHost(String iotHost) {
		this.iotHost = iotHost;
	}

	public boolean isMonitorEnabled() {
		return monitorEnabled;
	}

	public void setMonitorEnabled(boolean monitorEnabled) {
		this.monitorEnabled = monitorEnabled;
	}

	public boolean isLogEnabled() {
		return logEnabled;
	}

	public void setLogEnabled(boolean logEnabled) {
		this.logEnabled = logEnabled;
	}

	public String getLogHost() {
		return logHost;
	}

	public void setLogHost(String logHost) {
		this.logHost = logHost;
	}

	public int getLogPort() {
		return logPort;
	}

	public void setLogPort(int logPort) {
		this.logPort = logPort;
	}

	public String getLogUsername() {
		return logUsername;
	}

	public void setLogUsername(String logUsername) {
		this.logUsername = logUsername;
	}

	public String getLogPassword() {
		return logPassword;
	}

	public void setLogPassword(String logPassword) {
		this.logPassword = logPassword;
	}

	public boolean isTaskEnabled() {
		return taskEnabled;
	}

	public void setTaskEnabled(boolean taskEnabled) {
		this.taskEnabled = taskEnabled;
	}

	public String getTaskHost() {
		return taskHost;
	}

	public void setTaskHost(String taskHost) {
		this.taskHost = taskHost;
	}

	public int getTaskPort() {
		return taskPort;
	}

	public void setTaskPort(int taskPort) {
		this.taskPort = taskPort;
	}

	public String getTaskUsername() {
		return taskUsername;
	}

	public void setTaskUsername(String taskUsername) {
		this.taskUsername = taskUsername;
	}

	public String getTaskPassword() {
		return taskPassword;
	}

	public void setTaskPassword(String taskPassword) {
		this.taskPassword = taskPassword;
	}

	public boolean isEventEnabled() {
		return eventEnabled;
	}

	public void setEventEnabled(boolean eventEnabled) {
		this.eventEnabled = eventEnabled;
	}

	public String getEventHost() {
		return eventHost;
	}

	public void setEventHost(String eventHost) {
		this.eventHost = eventHost;
	}

	public int getEventPort() {
		return eventPort;
	}

	public void setEventPort(int eventPort) {
		this.eventPort = eventPort;
	}

	public String getEventUsername() {
		return eventUsername;
	}

	public void setEventUsername(String eventUsername) {
		this.eventUsername = eventUsername;
	}

	public String getEventPassword() {
		return eventPassword;
	}

	public void setEventPassword(String eventPassword) {
		this.eventPassword = eventPassword;
	}

	public String getLogQueue() {
		return logQueue;
	}

	public void setLogQueue(String logQueue) {
		this.logQueue = logQueue;
	}

	public String getTaskQueue() {
		return taskQueue;
	}

	public void setTaskQueue(String taskQueue) {
		this.taskQueue = taskQueue;
	}

	public String getEventQueue() {
		return eventQueue;
	}

	public void setEventQueue(String eventQueue) {
		this.eventQueue = eventQueue;
	}

	public String getWxAppId() {
		return wxAppId;
	}

	public void setWxAppId(String wxAppId) {
		this.wxAppId = wxAppId;
	}

	public String getWxSecret() {
		return wxSecret;
	}

	public void setWxSecret(String wxSecret) {
		this.wxSecret = wxSecret;
	}

	public boolean isWxEnabled() {
		return wxEnabled;
	}

	public void setWxEnabled(boolean wxEnabled) {
		this.wxEnabled = wxEnabled;
	}

	public String getWxAcodePage() {
		return wxAcodePage;
	}

	public void setWxAcodePage(String wxAcodePage) {
		this.wxAcodePage = wxAcodePage;
	}

	public String getWxTemplateId() {
		return wxTemplateId;
	}

	public void setWxTemplateId(String wxTemplateId) {
		this.wxTemplateId = wxTemplateId;
	}

	public String getWxPage() {
		return wxPage;
	}

	public void setWxPage(String wxPage) {
		this.wxPage = wxPage;
	}

	public long getTimeOutInSeconds() {
		return timeOutInSeconds;
	}

	public void setTimeOutInSeconds(long timeOutInSeconds) {
		this.timeOutInSeconds = timeOutInSeconds;
	}

	public long getRedisGuavaSize() {
		return redisGuavaSize;
	}

	public void setRedisGuavaSize(long redisGuavaSize) {
		this.redisGuavaSize = redisGuavaSize;
	}

	public long getRedisGuavaDuration() {
		return redisGuavaDuration;
	}

	public void setRedisGuavaDuration(long redisGuavaDuration) {
		this.redisGuavaDuration = redisGuavaDuration;
	}

	public long getStoreGuavaSize() {
		return storeGuavaSize;
	}

	public void setStoreGuavaSize(long storeGuavaSize) {
		this.storeGuavaSize = storeGuavaSize;
	}

	public long getStoreGuavaDuration() {
		return storeGuavaDuration;
	}

	public void setStoreGuavaDuration(long storeGuavaDuration) {
		this.storeGuavaDuration = storeGuavaDuration;
	}

	public long getShardGuavaSize() {
		return shardGuavaSize;
	}

	public void setShardGuavaSize(long shardGuavaSize) {
		this.shardGuavaSize = shardGuavaSize;
	}

	public long getShardGuavaDuration() {
		return shardGuavaDuration;
	}

	public void setShardGuavaDuration(long shardGuavaDuration) {
		this.shardGuavaDuration = shardGuavaDuration;
	}

	public int getWxNoticeSenderNThread() {
		return wxNoticeSenderNThread;
	}

	public void setWxNoticeSenderNThread(int wxNoticeSenderNThread) {
		this.wxNoticeSenderNThread = wxNoticeSenderNThread;
	}

	public boolean isAutoDeleteIndex() {
		return autoDeleteIndex;
	}

	public void setAutoDeleteIndex(boolean autoDeleteIndex) {
		this.autoDeleteIndex = autoDeleteIndex;
	}

	public String getAppAndroidKey() {
		return appAndroidKey;
	}

	public void setAppAndroidKey(String appAndroidKey) {
		this.appAndroidKey = appAndroidKey;
	}

	public String getAppAndroidMasterSecret() {
		return appAndroidMasterSecret;
	}

	public void setAppAndroidMasterSecret(String appAndroidMasterSecret) {
		this.appAndroidMasterSecret = appAndroidMasterSecret;
	}

	public String getAppIosKey() {
		return appIosKey;
	}

	public void setAppIosKey(String appIosKey) {
		this.appIosKey = appIosKey;
	}

	public String getAppIosMasterSecret() {
		return appIosMasterSecret;
	}

	public void setAppIosMasterSecret(String appIosMasterSecret) {
		this.appIosMasterSecret = appIosMasterSecret;
	}

	public int getModeTypeIndex() {
		return modeTypeIndex;
	}

	public void setModeTypeIndex(int modeTypeIndex) {
		this.modeTypeIndex = modeTypeIndex;
	}

	public boolean isUmengEnabled() {
		return umengEnabled;
	}

	public void setUmengEnabled(boolean umengEnabled) {
		this.umengEnabled = umengEnabled;
	}

	public String getPayHost() {
		return payHost;
	}

	public void setPayHost(String payHost) {
		this.payHost = payHost;
	}

	public String getChainHost() {
		return chainHost;
	}

	public void setChainHost(String chainHost) {
		this.chainHost = chainHost;
	}

	public String getMngHost() {
		return mngHost;
	}

	public void setMngHost(String mngHost) {
		this.mngHost = mngHost;
	}

	public String getWxMainAppId() {
		return wxMainAppId;
	}

	public void setWxMainAppId(String wxMainAppId) {
		this.wxMainAppId = wxMainAppId;
	}

	public String getWxMainSecret() {
		return wxMainSecret;
	}

	public void setWxMainSecret(String wxMainSecret) {
		this.wxMainSecret = wxMainSecret;
	}

	public String getCustomerHost() {
		return customerHost;
	}

	public void setCustomerHost(String customerHost) {
		this.customerHost = customerHost;
	}

	public String getWxMarketingAppId() {
		return wxMarketingAppId;
	}

	public void setWxMarketingAppId(String wxMarketingAppId) {
		this.wxMarketingAppId = wxMarketingAppId;
	}

	public String getWxMarketingSecret() {
		return wxMarketingSecret;
	}

	public void setWxMarketingSecret(String wxMarketingSecret) {
		this.wxMarketingSecret = wxMarketingSecret;
	}
	
}
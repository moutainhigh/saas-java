package com.hq.payms.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.hq.payms.zenmind.zmAlipay.common.ZmAlipayCfg;
import com.hq.payms.zenmind.zmWxpay.common.ZmWXPayCfg;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.dao.mongodb.MongodbCfg;
import com.zenmind.dao.redis.RedisConfig;

/**
 * @author zenmind 自定义属性对应的实体类 spring
 *         boot会将配置文件中自定义的属性值，自动设置到该类对应的属性上，使用的使用直接注入该类即可 prefix用来指定自定义属性值的前缀
 */
@ConfigurationProperties(prefix = "payms.prop")
public class PayMSCfg {
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
	private String storeMSHost;
	private String storeMngMSHost;
	
	//微信支付配置
	private String wxpayAppId;
	private String wxpayMchId;
	private String wxpayKey; //API密钥,用于生成签名
	private String wxpayCertPath; //商户证书
	private String wxpayNotifyUrl; //支付结果通知url
	private String wxpayRefundNotifyUrl; //退款结果通知url
	
	private boolean wxpayUseSandbox = false; //是否沙箱环境
	private int wxpayHttpConnectTimeoutMs;
	private int wxpayHttpReadTimeoutMs;
	private int wxpayOrderQueryNThread; //订单支付结果查询线程数
	private int wxpayTimeExpire; //支付失效时间(单位为秒)
	private int wxpayAuthorityNotifyTimeout; //微信官方回调通知超时时间(单位为秒)
	private int wxpayCallbackMaxCount; //回调通知storeMS的最大次数
	
	private String wxpayCertPathPrefix; //商户证书本地路径前缀
	private String wxpayCertPathNetPrefix; //商户证书网络路径前缀
	
	//支付宝配置
	private String alipayOpenApiDomain; // 支付宝openapi域名
	private String alipayMcloudApiDomain; // 支付宝mcloudmonitor域名
	private String alipayPid; // 商户partner id
	private String alipayAppid; // 商户应用id

	private String alipayPrivateKey; // RSA私钥，用于对商户请求报文加签
	private String alipayDevPublicKey; // RSA公钥，仅用于验证开发者网关
	private String alipayZfbPublicKey; // 支付宝RSA公钥，用于验签支付宝应答
	private String alipaySignType; // 签名类型

	private int alipayMaxQueryRetry; // 最大查询次数
	private long alipayQueryDuration; // 查询间隔（毫秒）

	private int alipayMaxCancelRetry; // 最大撤销次数
	private long alipayCancelDuration; // 撤销间隔（毫秒）

	private long alipayHeartbeatDelay; // 交易保障线程第一次调度延迟（秒）
	private long alipayHeartbeatDuration; // 交易保障线程调度间隔（秒）
	
	private String alipayNotifyUrl; //支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置 
	private String alipayRefundNotifyUrl; //支付宝退款通知路径
	
	private int alipayHttpConnectTimeoutMs;
	private int alipayHttpReadTimeoutMs;
	
	private int alipayOrderQueryNThread; //订单支付结果查询线程数
	private int alipayTimeoutExpress;  //支付失效时间(单位为秒)
	private int alipayAuthorityNotifyTimeout; //官方回调通知超时时间(单位为秒)
	private int alipayCallbackMaxCount; //回调通知storeMS的最大次数
	

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

	public ZmWXPayCfg getZmWXPayCfg() {
		ZmWXPayCfg zmWXPayCfg = new ZmWXPayCfg();
		FastBeanCopyer.getInstance().copy(this, zmWXPayCfg);
		return zmWXPayCfg;
	}
	
	public ZmAlipayCfg getZmAlipayCfg() {
		ZmAlipayCfg zmAlipayCfg = new ZmAlipayCfg();
		FastBeanCopyer.getInstance().copy(this, zmAlipayCfg);
		return zmAlipayCfg;
	}

	public MongodbCfg getMongodbCfg() {
		MongodbCfg mongodbCfg = new MongodbCfg();
		FastBeanCopyer.getInstance().copy(this, mongodbCfg);
		mongodbCfg.setAutoDeleteIndex(this.autoDeleteIndex);
		return mongodbCfg;
	}

	public RedisConfig getRedisConfig() {
		RedisConfig redisConfig = new RedisConfig();
		FastBeanCopyer.getInstance().copy(this, redisConfig);
		return redisConfig;
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


	public String getWxpayCertPath() {
		return wxpayCertPath;
	}

	public void setWxpayCertPath(String wxpayCertPath) {
		this.wxpayCertPath = wxpayCertPath;
	}

	public String getWxpayAppId() {
		return wxpayAppId;
	}

	public void setWxpayAppId(String wxpayAppId) {
		this.wxpayAppId = wxpayAppId;
	}

	public String getWxpayMchId() {
		return wxpayMchId;
	}

	public void setWxpayMchId(String wxpayMchId) {
		this.wxpayMchId = wxpayMchId;
	}

	public String getWxpayKey() {
		return wxpayKey;
	}

	public void setWxpayKey(String wxpayKey) {
		this.wxpayKey = wxpayKey;
	}

	public int getWxpayHttpConnectTimeoutMs() {
		return wxpayHttpConnectTimeoutMs;
	}

	public void setWxpayHttpConnectTimeoutMs(int wxpayHttpConnectTimeoutMs) {
		this.wxpayHttpConnectTimeoutMs = wxpayHttpConnectTimeoutMs;
	}

	public int getWxpayHttpReadTimeoutMs() {
		return wxpayHttpReadTimeoutMs;
	}

	public void setWxpayHttpReadTimeoutMs(int wxpayHttpReadTimeoutMs) {
		this.wxpayHttpReadTimeoutMs = wxpayHttpReadTimeoutMs;
	}

	public String getWxpayNotifyUrl() {
		return wxpayNotifyUrl;
	}

	public void setWxpayNotifyUrl(String wxpayNotifyUrl) {
		this.wxpayNotifyUrl = wxpayNotifyUrl;
	}

	public boolean isWxpayUseSandbox() {
		return wxpayUseSandbox;
	}

	public void setWxpayUseSandbox(boolean wxpayUseSandbox) {
		this.wxpayUseSandbox = wxpayUseSandbox;
	}

	public String getWxpayRefundNotifyUrl() {
		return wxpayRefundNotifyUrl;
	}

	public void setWxpayRefundNotifyUrl(String wxpayRefundNotifyUrl) {
		this.wxpayRefundNotifyUrl = wxpayRefundNotifyUrl;
	}

	public String getAlipayOpenApiDomain() {
		return alipayOpenApiDomain;
	}

	public void setAlipayOpenApiDomain(String alipayOpenApiDomain) {
		this.alipayOpenApiDomain = alipayOpenApiDomain;
	}

	public String getAlipayMcloudApiDomain() {
		return alipayMcloudApiDomain;
	}

	public void setAlipayMcloudApiDomain(String alipayMcloudApiDomain) {
		this.alipayMcloudApiDomain = alipayMcloudApiDomain;
	}

	public String getAlipayPid() {
		return alipayPid;
	}

	public void setAlipayPid(String alipayPid) {
		this.alipayPid = alipayPid;
	}

	public String getAlipayAppid() {
		return alipayAppid;
	}

	public void setAlipayAppid(String alipayAppid) {
		this.alipayAppid = alipayAppid;
	}

	public String getAlipayPrivateKey() {
		return alipayPrivateKey;
	}

	public void setAlipayPrivateKey(String alipayPrivateKey) {
		this.alipayPrivateKey = alipayPrivateKey;
	}

	public String getAlipayDevPublicKey() {
		return alipayDevPublicKey;
	}

	public void setAlipayDevPublicKey(String alipayDevPublicKey) {
		this.alipayDevPublicKey = alipayDevPublicKey;
	}

	public String getAlipayZfbPublicKey() {
		return alipayZfbPublicKey;
	}

	public void setAlipayZfbPublicKey(String alipayZfbPublicKey) {
		this.alipayZfbPublicKey = alipayZfbPublicKey;
	}

	public String getAlipaySignType() {
		return alipaySignType;
	}

	public void setAlipaySignType(String alipaySignType) {
		this.alipaySignType = alipaySignType;
	}

	public int getAlipayMaxQueryRetry() {
		return alipayMaxQueryRetry;
	}

	public void setAlipayMaxQueryRetry(int alipayMaxQueryRetry) {
		this.alipayMaxQueryRetry = alipayMaxQueryRetry;
	}

	public long getAlipayQueryDuration() {
		return alipayQueryDuration;
	}

	public void setAlipayQueryDuration(long alipayQueryDuration) {
		this.alipayQueryDuration = alipayQueryDuration;
	}

	public int getAlipayMaxCancelRetry() {
		return alipayMaxCancelRetry;
	}

	public void setAlipayMaxCancelRetry(int alipayMaxCancelRetry) {
		this.alipayMaxCancelRetry = alipayMaxCancelRetry;
	}

	public long getAlipayCancelDuration() {
		return alipayCancelDuration;
	}

	public void setAlipayCancelDuration(long alipayCancelDuration) {
		this.alipayCancelDuration = alipayCancelDuration;
	}

	public long getAlipayHeartbeatDelay() {
		return alipayHeartbeatDelay;
	}

	public void setAlipayHeartbeatDelay(long alipayHeartbeatDelay) {
		this.alipayHeartbeatDelay = alipayHeartbeatDelay;
	}

	public long getAlipayHeartbeatDuration() {
		return alipayHeartbeatDuration;
	}

	public void setAlipayHeartbeatDuration(long alipayHeartbeatDuration) {
		this.alipayHeartbeatDuration = alipayHeartbeatDuration;
	}

	public String getAlipayNotifyUrl() {
		return alipayNotifyUrl;
	}

	public void setAlipayNotifyUrl(String alipayNotifyUrl) {
		this.alipayNotifyUrl = alipayNotifyUrl;
	}

	public int getWxpayOrderQueryNThread() {
		return wxpayOrderQueryNThread;
	}

	public void setWxpayOrderQueryNThread(int wxpayOrderQueryNThread) {
		this.wxpayOrderQueryNThread = wxpayOrderQueryNThread;
	}

	public boolean isAutoDeleteIndex() {
		return autoDeleteIndex;
	}

	public void setAutoDeleteIndex(boolean autoDeleteIndex) {
		this.autoDeleteIndex = autoDeleteIndex;
	}

	public int getWxpayTimeExpire() {
		return wxpayTimeExpire;
	}

	public void setWxpayTimeExpire(int wxpayTimeExpire) {
		this.wxpayTimeExpire = wxpayTimeExpire;
	}

	public int getAlipayTimeoutExpress() {
		return alipayTimeoutExpress;
	}

	public void setAlipayTimeoutExpress(int alipayTimeoutExpress) {
		this.alipayTimeoutExpress = alipayTimeoutExpress;
	}

	public int getAlipayHttpConnectTimeoutMs() {
		return alipayHttpConnectTimeoutMs;
	}

	public void setAlipayHttpConnectTimeoutMs(int alipayHttpConnectTimeoutMs) {
		this.alipayHttpConnectTimeoutMs = alipayHttpConnectTimeoutMs;
	}

	public int getAlipayHttpReadTimeoutMs() {
		return alipayHttpReadTimeoutMs;
	}

	public void setAlipayHttpReadTimeoutMs(int alipayHttpReadTimeoutMs) {
		this.alipayHttpReadTimeoutMs = alipayHttpReadTimeoutMs;
	}

	public int getAlipayOrderQueryNThread() {
		return alipayOrderQueryNThread;
	}

	public void setAlipayOrderQueryNThread(int alipayOrderQueryNThread) {
		this.alipayOrderQueryNThread = alipayOrderQueryNThread;
	}

	public int getWxpayAuthorityNotifyTimeout() {
		return wxpayAuthorityNotifyTimeout;
	}

	public void setWxpayAuthorityNotifyTimeout(int wxpayAuthorityNotifyTimeout) {
		this.wxpayAuthorityNotifyTimeout = wxpayAuthorityNotifyTimeout;
	}

	public int getAlipayAuthorityNotifyTimeout() {
		return alipayAuthorityNotifyTimeout;
	}

	public void setAlipayAuthorityNotifyTimeout(int alipayAuthorityNotifyTimeout) {
		this.alipayAuthorityNotifyTimeout = alipayAuthorityNotifyTimeout;
	}

	public String getStoreMSHost() {
		return storeMSHost;
	}

	public void setStoreMSHost(String storeMSHost) {
		this.storeMSHost = storeMSHost;
	}

	public String getAlipayRefundNotifyUrl() {
		return alipayRefundNotifyUrl;
	}

	public void setAlipayRefundNotifyUrl(String alipayRefundNotifyUrl) {
		this.alipayRefundNotifyUrl = alipayRefundNotifyUrl;
	}

	public String getWxpayCertPathPrefix() {
		return wxpayCertPathPrefix;
	}

	public void setWxpayCertPathPrefix(String wxpayCertPathPrefix) {
		this.wxpayCertPathPrefix = wxpayCertPathPrefix;
	}

	public String getWxpayCertPathNetPrefix() {
		return wxpayCertPathNetPrefix;
	}

	public void setWxpayCertPathNetPrefix(String wxpayCertPathNetPrefix) {
		this.wxpayCertPathNetPrefix = wxpayCertPathNetPrefix;
	}

	public int getWxpayCallbackMaxCount() {
		return wxpayCallbackMaxCount;
	}

	public void setWxpayCallbackMaxCount(int wxpayCallbackMaxCount) {
		this.wxpayCallbackMaxCount = wxpayCallbackMaxCount;
	}

	public int getAlipayCallbackMaxCount() {
		return alipayCallbackMaxCount;
	}

	public void setAlipayCallbackMaxCount(int alipayCallbackMaxCount) {
		this.alipayCallbackMaxCount = alipayCallbackMaxCount;
	}

	public String getStoreMngMSHost() {
		return storeMngMSHost;
	}

	public void setStoreMngMSHost(String storeMngMSHost) {
		this.storeMngMSHost = storeMngMSHost;
	}

}
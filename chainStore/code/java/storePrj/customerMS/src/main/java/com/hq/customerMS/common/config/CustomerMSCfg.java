package com.hq.customerMS.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.dao.mongodb.MongodbCfg;
import com.zenmind.dao.redis.RedisConfig;

/**
 * @author zenmind 自定义属性对应的实体类 spring
 *         boot会将配置文件中自定义的属性值，自动设置到该类对应的属性上，使用的使用直接注入该类即可 prefix用来指定自定义属性值的前缀
 */
@ConfigurationProperties(prefix = "customerms.prop")
public class CustomerMSCfg {
	private String envMark;
	
	private String fileHost;
	private String thirdPartyHost;
	private String storeMSHost;

	private int hystrixTimeoutInMillions;
	private long verifyCodeExpireTime;// 验证码有效时长
	private boolean smsCodeOpen = true;// 短信验证开关
	private String wxMarketingAppId;
	
	//缓存配置
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

	// Rabbit logger 配置
	private boolean logEnabled = true;// rabbitmq日志开关
	private String logHost;
	private int logPort;
	private String logUserName;
	private String logPassword;
	private String logExchange;
	private String logQueue;
	private String logRouteKey;
	private int logChannelCacheSize;
	private boolean logPesistent;

	public MongodbCfg getMongodbCfg() {
		MongodbCfg mongodbCfg = new MongodbCfg();
		FastBeanCopyer.getInstance().copy(this, mongodbCfg);
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

	public int getHystrixTimeoutInMillions() {
		return hystrixTimeoutInMillions;
	}

	public void setHystrixTimeoutInMillions(int hystrixTimeoutInMillions) {
		this.hystrixTimeoutInMillions = hystrixTimeoutInMillions;
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

	public String getThirdPartyHost() {
		return thirdPartyHost;
	}

	public void setThirdPartyHost(String thirdPartyHost) {
		this.thirdPartyHost = thirdPartyHost;
	}


	public boolean isLogEnabled() {
		return logEnabled;
	}

	public void setLogEnabled(boolean logEnabled) {
		this.logEnabled = logEnabled;
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

	public String getLogUserName() {
		return logUserName;
	}

	public void setLogUserName(String logUserName) {
		this.logUserName = logUserName;
	}

	public String getLogPassword() {
		return logPassword;
	}

	public void setLogPassword(String logPassword) {
		this.logPassword = logPassword;
	}

	public String getLogExchange() {
		return logExchange;
	}

	public void setLogExchange(String logExchange) {
		this.logExchange = logExchange;
	}

	public String getLogQueue() {
		return logQueue;
	}

	public void setLogQueue(String logQueue) {
		this.logQueue = logQueue;
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

	public String getStoreMSHost() {
		return storeMSHost;
	}

	public void setStoreMSHost(String storeMSHost) {
		this.storeMSHost = storeMSHost;
	}

	public String getFileHost() {
		return fileHost;
	}

	public void setFileHost(String fileHost) {
		this.fileHost = fileHost;
	}

	public boolean isRedisEnabled() {
		return redisEnabled;
	}

	public void setRedisEnabled(boolean redisEnabled) {
		this.redisEnabled = redisEnabled;
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

	public String getWxMarketingAppId() {
		return wxMarketingAppId;
	}

	public void setWxMarketingAppId(String wxMarketingAppId) {
		this.wxMarketingAppId = wxMarketingAppId;
	}
}
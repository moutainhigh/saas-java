package com.hq.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zenmind 自定义属性对应的实体类 spring
 *         boot会将配置文件中自定义的属性值，自动设置到该类对应的属性上，使用的使用直接注入该类即可 prefix用来指定自定义属性值的前缀
 */
@ConfigurationProperties(prefix = "storelogms.prop")
public class StoreLogMSCfg {
	private String envMark;

	// Rabbit logger 配置
	private String logHost;
	private int logPort;
	private String logUsername;
	private String logPassword;
	private String logExchange;
	private String logQueue;
	private String logRouteKey;
	private int logChannelCacheSize;
	private boolean logPesistent;

	public String getEnvMark() {
		return envMark;
	}

	public void setEnvMark(String envMark) {
		this.envMark = envMark;
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

}
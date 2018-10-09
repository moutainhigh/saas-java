package com.hq.taskMS.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zenmind 自定义属性对应的实体类 spring
 *         boot会将配置文件中自定义的属性值，自动设置到该类对应的属性上，使用的使用直接注入该类即可 prefix用来指定自定义属性值的前缀
 */
@ConfigurationProperties(prefix = "storetaskms.prop")
public class StoreTaskMSCfg {
	private String envMark;

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

	public String getEnvMark() {
		return envMark;
	}

	public void setEnvMark(String envMark) {
		this.envMark = envMark;
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

	public String getTaskExchange() {
		return taskExchange;
	}

	public void setTaskExchange(String taskExchange) {
		this.taskExchange = taskExchange;
	}

	public String getTaskQueue() {
		return taskQueue;
	}

	public void setTaskQueue(String taskQueue) {
		this.taskQueue = taskQueue;
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

	public boolean isTaskEnabled() {
		return taskEnabled;
	}

	public void setTaskEnabled(boolean taskEnabled) {
		this.taskEnabled = taskEnabled;
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

	public String getEventExchange() {
		return eventExchange;
	}

	public void setEventExchange(String eventExchange) {
		this.eventExchange = eventExchange;
	}

	public String getEventQueue() {
		return eventQueue;
	}

	public void setEventQueue(String eventQueue) {
		this.eventQueue = eventQueue;
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

}
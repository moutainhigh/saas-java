package com.hq.thirdPartyServer.common.config;

import java.io.UnsupportedEncodingException;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zenmind 自定义属性对应的实体类 spring
 *         boot会将配置文件中自定义的属性值，自动设置到该类对应的属性上，使用的使用直接注入该类即可 prefix用来指定自定义属性值的前缀
 */
@ConfigurationProperties(prefix = "thirdPartyServer.prop")
public class ThirdPartyServerCfg {
	private String envMark;
	// 短信服务商切换 1:阿里 2:云片网
	private int smsSwitch = 1;

	// 阿里云短信appkey
	private String appKey;
	// 阿里云短信appSecret
	private String appSecret;
	// 注册验证码模板编号
	private String regCode;
	// 邀请码模板编号
	private String inviteMessage;

	// 云片网应用apikey
	private String apikey;
	// 智美通验证码 国内与国际的短信模板
	private String zmtCodeTemplate;
	private String zmtIntlCodeTemplate;
	private String zmtGatCodeTemplate;

	// 智美客验证码 国内与国际的短信模板
	private String zmkCodeTemplate;
	private String zmkIntlCodeTemplate;
	private String zmkGatCodeTemplate;

	// 智美预约验证码 国内与国际的短信模板
	private String zmyyCodeTemplate;
	private String zmyyIntlCodeTemplate;
	private String zmyyGatCodeTemplate;

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

	public String getEnvMark() {
		return envMark;
	}

	public void setEnvMark(String envMark) {
		this.envMark = envMark;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getRegCode() {
		return regCode;
	}

	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}

	public String getInviteMessage() {
		return inviteMessage;
	}

	public void setInviteMessage(String inviteMessage) {
		this.inviteMessage = inviteMessage;
	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

	public String getZmtCodeTemplate() {
		return zmtCodeTemplate;
	}

	public void setZmtCodeTemplate(String zmtCodeTemplate) {
		try {
			this.zmtCodeTemplate = new String(zmtCodeTemplate.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			this.zmtCodeTemplate = zmtCodeTemplate;
		}
	}

	public String getZmkCodeTemplate() {
		return zmkCodeTemplate;
	}

	public void setZmkCodeTemplate(String zmkCodeTemplate) {
		try {
			this.zmkCodeTemplate = new String(zmkCodeTemplate.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			this.zmkCodeTemplate = zmkCodeTemplate;
		}
	}

	public int getSmsSwitch() {
		return smsSwitch;
	}

	public void setSmsSwitch(int smsSwitch) {
		this.smsSwitch = smsSwitch;
	}

	public String getZmtIntlCodeTemplate() {
		return zmtIntlCodeTemplate;
	}

	public void setZmtIntlCodeTemplate(String zmtIntlCodeTemplate) {
		try {
			this.zmtIntlCodeTemplate = new String(zmtIntlCodeTemplate.getBytes("ISO-8859-1"), "UTF-8");
		} catch (Exception e) {
			this.zmtIntlCodeTemplate = zmtIntlCodeTemplate;
			e.printStackTrace();
		}
	}

	public String getZmkIntlCodeTemplate() {
		return zmkIntlCodeTemplate;
	}

	public void setZmkIntlCodeTemplate(String zmkIntlCodeTemplate) {
		try {
			this.zmkIntlCodeTemplate = new String(zmkIntlCodeTemplate.getBytes("ISO-8859-1"), "UTF-8");
		} catch (Exception e) {
			this.zmkIntlCodeTemplate = zmkIntlCodeTemplate;
			e.printStackTrace();
		}
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

	public String getZmyyCodeTemplate() {
		return zmyyCodeTemplate;
	}

	public void setZmyyCodeTemplate(String zmyyCodeTemplate) {
		try {
			this.zmyyCodeTemplate = new String(zmyyCodeTemplate.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			this.zmyyCodeTemplate = zmyyCodeTemplate;
		}
	}

	public String getZmyyIntlCodeTemplate() {
		return zmyyIntlCodeTemplate;
	}

	public void setZmyyIntlCodeTemplate(String zmyyIntlCodeTemplate) {
		try {
			this.zmyyIntlCodeTemplate = new String(zmyyIntlCodeTemplate.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			this.zmyyIntlCodeTemplate = zmyyIntlCodeTemplate;
		}
	}

	public String getZmtGatCodeTemplate() {
		return zmtGatCodeTemplate;
	}

	public void setZmtGatCodeTemplate(String zmtGatCodeTemplate) {
		try {
			this.zmtGatCodeTemplate = new String(zmtGatCodeTemplate.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			this.zmtGatCodeTemplate = zmtGatCodeTemplate;
		}
	}

	public String getZmkGatCodeTemplate() {
		return zmkGatCodeTemplate;
	}

	public void setZmkGatCodeTemplate(String zmkGatCodeTemplate) {
		try {
			this.zmkGatCodeTemplate = new String(zmkGatCodeTemplate.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			this.zmkGatCodeTemplate = zmkGatCodeTemplate;
		}
	}

	public String getZmyyGatCodeTemplate() {
		return zmyyGatCodeTemplate;
	}

	public void setZmyyGatCodeTemplate(String zmyyGatCodeTemplate) {
		try {
			this.zmyyGatCodeTemplate = new String(zmyyGatCodeTemplate.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			this.zmyyGatCodeTemplate = zmyyGatCodeTemplate;
		}
	}

}
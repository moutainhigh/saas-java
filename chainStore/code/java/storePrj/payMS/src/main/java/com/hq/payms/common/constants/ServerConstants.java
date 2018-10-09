package com.hq.payms.common.constants;

public class ServerConstants {
	public static final int appId = 1; //暂时设置为1
	
	public static final long Account_Cache_Num = 7000;
	public static final String APP_NAME = "";
	public static final String LOGIN_ACCOUNT = "LOGIN_ACCOUNT";
	// Session保存7天 单位：毫秒
	public static final long SESSION_EFFECTIVE_TIME = 1000L * 60 * 60 * 24 * 7;
	public static final String INIT_PASSWORD = "123456";
	public static final long MAX_COUNT = 1000000L;
	public static final String JOIN_SYMBOL = "_";
	public static final String COMMA_SYMBOL = ",";
	//redis缓存时间一个小时  单位：秒   
	public static final long CACHE_EFFECTIVE_TIME = 60L * 60;
	
}


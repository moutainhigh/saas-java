package com.hq.storeManagerMS.common.constants;

public class ServerConstants {
	public static final int appId = 1;
	public static final String appName = "storeMngMS";
	
	public static final long Account_Cache_Num = 7000;
	//短信签名
	public static final String APP_NAME = "智美通";
	
	public static final String LOGIN_ACCOUNT = "LOGIN_ACCOUNT";
	
	// Session保存7天 单位：毫秒
	public static final long SESSION_EFFECTIVE_TIME = 1000L * 60 * 60 * 24 * 7;

	//随机生成数字字符串的原始串
	public static final String NUMBER_RANDOM = "0123456789";
	
	//短信内容  有效时间
	public static final int EFFICTIVE_TIME = 10;
	
	public static final long MAX_COUNT = 1000000L;
	
	public static final int PAGE_ITEM_COUNT = 100000;
	
	//查询结构redis缓存时间 单位：秒   一个小时
	public static final long CACHE_EFFECTIVE_TIME = 60L * 60;
	
	//商务手机号
	public static final long BUSINESS_PHONE = 13810798248L;
}

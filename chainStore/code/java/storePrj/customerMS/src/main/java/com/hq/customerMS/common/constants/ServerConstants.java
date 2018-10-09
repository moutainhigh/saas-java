package com.hq.customerMS.common.constants;

public class ServerConstants {
	public static final int appId = 1; //暂时设置为1
	public static final String appName = "CustomerMS";
	
	public static final long Account_Cache_Num = 7000;
	//短信签名
	public static final String APP_NAME = "智美客";
	
	public static final String LOGIN_ACCOUNT = "LOGIN_ACCOUNT";
	//随机生成数字字符串的原始串
	public static final String NUMBER_RANDOM = "0123456789";
	
	// Session保存7天 单位：毫秒
	public static final long SESSION_EFFECTIVE_TIME = 1000L * 60 * 60 * 24 * 7;
	
	
	public static final String STORE_CLERKINFO_ID_SUFFFIX = "_sci";
	public static final String STORE_DISCOUNTCARD_ID_SUFFFIX = "_dis";
	public static final String STORE_MEMBERSHIPCARD_ID_SUFFFIX = "_mem";
	public static final String STORE_PRODUCTCARD_ID_SUFFFIX = "_prd";
	public static final String STORE_LEAGUERPRDCARD_ID_SUFFFIX = "_leaguerPrdCard";
	
	public static final String APPID = "zhimeike";
	
	public static final String JOIN_SYMBOL = "_";
	
	//短信内容  有效时间
	public static final int EFFICTIVE_TIME = 10;
	
	//一个buser最多能关联的店铺数量
	public static final int MAX_OWN_STORE = 100;
	
	//查询结构redis缓存时间 单位：秒   一个小时
	public static final long CACHE_EFFECTIVE_TIME = 60L * 60;
	
	public static final int PAGE_ITEM_COUNT = 100000;
	
	public static final String ID_FORMAT = "{}_{}";
}

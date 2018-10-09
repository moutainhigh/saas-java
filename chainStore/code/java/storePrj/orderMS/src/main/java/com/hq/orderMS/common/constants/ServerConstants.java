package com.hq.orderMS.common.constants;

public class ServerConstants {
	// 逗号字符
	public static final char COMMA = ',';

	public static final long Account_Cache_Num = 7000;

	public static final String LOGIN_ACCOUNT = "LOGIN_ACCOUNT";

	// Session保存7天 单位：毫秒
	public static final long SESSION_EFFECTIVE_TIME = 1000L * 60 * 60 * 24 * 7;

	// 查询结构redis缓存时间 单位：秒 一个小时
	public static final long CACHE_EFFECTIVE_TIME = 60L * 60;

	public static final String JOIN_SYMBOL = "_";

	public static final int PAGE_ITEM_COUNT = 100000;

	public static final String df_format = "yyyy-MM-dd HH:mm:ss";
	public static final String df_format_nosplit = "yyyyMMddHHmmss";
	public static final String df_short = "yyyy-MM-dd";
	public static final String df_short_nosplit = "yyyyMMdd";
	public static final String df_yy_mm = "yyyy-MM";

	// 订单赠送项ID
	public static final String ORDER_DONATION_ITEM_ID_SUFFFIX = "_donate";
	// 订单购买项ID
	public static final String ORDER_BUY_ITEM_ID_SUFFFIX = "_buy";
	// 订单划卡项ID
	public static final String ORDER_DELIMITCARD_ITEM_ID_SUFFFIX = "_delimitCard";
	// 订单预存卡消费ID
	public static final String ORDER_PRESTORE_ITEM_ID_SUFFFIX = "_preStore";

	// 常用日期时间
	public static final long ONE_HOUR = 3600L * 1000;
	public static final long ONE_DAY = ONE_HOUR * 24;
	public static final long ONE_MONTH = ONE_DAY * 30;
	public static final long HALF_YEAR = ONE_MONTH * 6;
	public static final long ONE_YEAR = ONE_MONTH * 12;
}

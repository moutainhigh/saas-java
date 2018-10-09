package com.hq.chainMS.common.constants;

public class ServerConstants {
	public static final int appId = 1;
	public static final String appName = "ChainMS";
	
	public static final long Account_Cache_Num = 7000;

	public static final String LOGIN_ACCOUNT = "LOGIN_ACCOUNT";

	// Redis缓存时长
	public static final long CACHE_EFFECTIVE_TIME = 60L * 60;

	// Session保存7天 单位：毫秒
	public static final long SESSION_EFFECTIVE_TIME = 1000L * 60 * 60 * 24 * 7;

	public static final long MAX_COUNT = 1000000L;

	public static final int PAGE_ITEM_COUNT = 100000;

	// 初始化密码
	public static final String INIT_PASSWRD = "123456";

	// 店铺店员工ID
	public static final String STORE_CLERK_ID_SUFFIX = "_sci";
	// 连锁店员工ID
	public static final String CHAIN_CLERK_ID_SUFFIX = "_cci";
	// 会员卡ID
	public static final String CHAIN_MEMBERSHIP_CARD_ID_SUFFIX = "_cmci";
	// 次卡ID
	public static final String CHAIN_PRODUCT_CARD_ID_SUFFIX = "_cpci";
	// 次卡类型ID
	public static final String CHAIN_PRODUCT_CARD_TYPE_ID_SUFFIX = "_cpcti";
	// 客户次卡ID
	public static final String CHAIN_LEAGUER_PRDCARD_ID_SUFFIX = "_clpi";
	// 商品ID
	public static final String CHAIN_GOODS_ID_SUFFIX = "_cgi";
	// 商品类型ID
	public static final String CHAIN_GOODS_TYPE_ID_SUFFIX = "_cgti";
	// 项目ID
	public static final String CHAIN_PRODUCT_ID_SUFFIX = "_cpi";
	// 项目类型ID
	public static final String CHAIN_PRODUCT_TYPE_ID_SUFFIX = "_cpti";
	// 套餐ID
	public static final String CHAIN_PACKAGE_PROJECT_ID_SUFFIX = "_cppi";
	// 套餐类型ID
	public static final String CHAIN_PACKAGE_PROJECT_TYPE_ID_SUFFIX = "_cppti";

	// 编号前缀
	public static final String GOODS_SUFFIX = "G";
	public static final String PRODUCT_SUFFIX = "P";
	public static final String PACKAGE_SUFFIX = "T";
	public static final String PRDCARD_SUFFIX = "C";

	// 日期格式
	public static final String DF_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DF_FORMAT_NOSPLIT = "yyyyMMddHHmmss";
	public static final String DF_SHORT = "yyyy-MM-dd";
	public static final String DF_SHORT_NOSPLIT = "yyyyMMdd";
	public static final String DF_YY_MM = "yyyy-MM";

	// 常用日期时间
	public static final long ONE_HOUR = 3600L * 1000;
	public static final long ONE_DAY = ONE_HOUR * 24;
	public static final long ONE_MONTH = ONE_DAY * 30;
	public static final long HALF_YEAR = ONE_MONTH * 6;
	public static final long ONE_YEAR = ONE_MONTH * 12;


	//逗号字符
	public static final char COMMA = ',';
}

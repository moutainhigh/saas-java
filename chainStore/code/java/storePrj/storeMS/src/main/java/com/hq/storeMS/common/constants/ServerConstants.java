package com.hq.storeMS.common.constants;

public class ServerConstants {
	public static final int appId = 1;
	
	public static final String appName = "storeMS";
	
	//连锁店商品项目套餐次卡会员卡的ID前缀
	public static final String CHAIN_ID_SUFFFIX = "_c";
	
	public static final String ZERO = "0";
	//逗号字符
	public static final char COMMA = ',';
	// 折扣基数
	public static final int DISCOUNT_NUM = 10;
	// 最大店铺数
	public static final int MAX_STORE_NUM = 10;

	public static final long Account_Cache_Num = 7000;
	// 短信签名
	public static final String APP_NAME = "智美通";

	public static final String LOGIN_ACCOUNT = "LOGIN_ACCOUNT";

	// Session保存7天 单位：毫秒
	public static final long SESSION_EFFECTIVE_TIME = 1000L * 60 * 60 * 24 * 7;

	// 店铺员工ID
	public static final String STORE_CLERKINFO_ID_SUFFFIX = "_sci";
	// 卡包优惠卷ID
	public static final String STORE_DISCOUNTCARD_ID_SUFFFIX = "_dis";
	// 卡包会员卡ID
	public static final String STORE_MEMBERSHIPCARD_ID_SUFFFIX = "_mem";
	// 卡包次卡ID
	public static final String STORE_PRODUCTCARD_ID_SUFFFIX = "_prd";
	// 客户会员卡ID
	public static final String STORE_LEAGUERPRDCARD_ID_SUFFFIX = "_leaguerPrdCard";
	//客户连锁店会员卡ID
	public static final String CHAIN_LEAGUERPRDCARD_ID_SUFFFIX = "_chainPrdCard";
	
	//编号前缀
	public static final String GOODS_SUFFIX= "G";
	public static final String PRODUCT_SUFFIX = "P";
	public static final String PACKAGE_SUFFIX = "T";
	public static final String PRDCARD_SUFFIX = "C";

	public static final String APPID = "zhimeitong";

	public static final String INIT_PASSWORD = "123456";
	// 随机生成数字字符串的原始串
	public static final String NUMBER_RANDOM = "0123456789";
	// 短信内容 有效时间
	public static final int EFFICTIVE_TIME = 10;

	public static final long MAX_COUNT = 1000000L;

	public static final int PAGE_ITEM_COUNT = 100000;

	public static final String JOIN_SYMBOL = "_";
	public static final String COMMA_SYMBOL = ",";

	public static final String ID_FORMAT = "{}_{}";

	// 一个buser最多能关联的店铺数量
	public static final int MAX_OWN_STORE = 100;

	// 查询结构redis缓存时间 单位：秒 一个小时
	public static final long CACHE_EFFECTIVE_TIME = 60L * 60;

	// 商品默认图
	public static final String GOODS_DEFAULT_PATH = "img/logo/goods/goodList.png";
	// 项目默认图
	public static final String PRODUCT_DEFAULT_PATH = "img/logo/product/pore.png";
	// 套餐默认图
	public static final String PACKAGE_DEFAULT_PATH = "img/logo/package/default.png";

	public static final String df_format = "yyyy-MM-dd HH:mm:ss";
	public static final String df_format_nosplit = "yyyyMMddHHmmss";
	public static final String df_short = "yyyy-MM-dd";
	public static final String df_short_nosplit = "yyyyMMdd";
	public static final String df_yy_mm = "yyyy-MM";

	// 体验账号 原始数据的老板手机号码
	public static final long TEST_DATA_PHONE = 13660623953L;
	// 商务手机号
	public static final long BUSINESS_PHONE = 4008955182L;

	// 永久有效期
	public static final int FOREVER_INT_VALUE = -1;
	public static final long FOREVER_LONG_VALUE = -1L;
	//无效类型
	public static final int NONE_TYPE = -1;

	// 常用日期时间
	public static final long ONE_HOUR = 3600L * 1000;
	public static final long ONE_DAY = ONE_HOUR * 24;
	public static final long ONE_MONTH = ONE_DAY * 30;
	public static final long HALF_YEAR = ONE_MONTH * 6;
	public static final long ONE_YEAR = ONE_MONTH * 12;
}

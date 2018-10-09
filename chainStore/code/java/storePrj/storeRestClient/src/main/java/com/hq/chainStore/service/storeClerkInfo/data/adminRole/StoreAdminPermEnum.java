package com.hq.chainStore.service.storeClerkInfo.data.adminRole;

import com.zenmind.common.StringFormatUtil;

public enum StoreAdminPermEnum {
	BOSS("store:*:*:{}","老板"),
	CLERK_ADMIN("store:clerk:*:{}","员工管理"),
	PRODUCT_ADMIN("store:product:*:{}","项目管理"),
	APPOINTMENT_ADMIN("store:appointment:*:{}","预约管理"),
	SALARY_ADMIN("store:salary:*:{}","工资管理"),
	LEAGUER_ADMIN("store:leaguer:*:{}","客户管理"),
	ORDER_ADMIN("store:order:*:{}","订单管理"),
	CASHIER_ADMIN("store:cashier:*:{}","收银管理"),
	MATERIAL_ADMIN("store:material:*:{}","耗材管理"),
	REPORT_ADMIN("store:report:*:{}","数据统计"),
	CARD_ADMIN("store:card:*:{}","卡包管理"),
	GOODS_ADMIN("store:goods:*:{}","商品管理"),
	BONUS_ADMIN("store:bonus:*:{}","提成管理"),
	PURCHASE_ADMIN("store:purchase:*:{}","开单收银"),
	RECHARGE_ADMIN("store:recharge:*:{}","会员充值"),
	DEVICE_ADMIN("store:device:*:{}","仪器管理"),
	ARREARAGE_ADMIN("store:arrearage:*:{}","欠款管理"),
	PHONE_ADMIN("store:phone:*:{}","客户电话可见"),
	PACKAGE_ADMIN("store:package:*:{}","套餐管理"),
	SYNDATA_ADMIN("store:syndata:*:{}","数据获取"),
	INCOME_PAY_ADMIN("store:syndata:*:{}","收入支出"),
	STORE_CONFIG_ADMIN("store:config:*:{}","管理设置"),
	DAYSNAPSHOT_ADMIN("store:daySnapshot:*:{}","交班日结"),
	OPLOG_ADMIN("store:oplog:*:{}","操作日志"),
	;
	
	//权限模板{} 会被storeId代替
	private String perm;
	
	//权限描述
	private String descript;
	
	private StoreAdminPermEnum(String permP, String descriptP){
		this.perm = permP;
		this.descript = descriptP;
	}

	public String getPerm(long storeId) {
		return StringFormatUtil.format(perm, storeId);
	}

	public String getDescript() {
		return descript;
	}
	
	public static StoreAdminPermEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
	
	
	
}

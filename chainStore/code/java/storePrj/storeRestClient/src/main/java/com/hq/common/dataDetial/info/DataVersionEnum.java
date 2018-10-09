package com.hq.common.dataDetial.info;

public enum DataVersionEnum {
	LeaguerDetail,//客户
	GoodsDetail,//商品
	ProductDetail,//项目
	MembershipCardDetail,//会员卡
	ProductCardDetail,//次卡
	PackageProjectDetail,//套餐
	Appointment,//预约
	WorkflowData,//店务
	Order,//订单
	Arrearage,//欠款
	OrderNotes,//订单附属信息
	StoreBUser,//店铺用户信息
	;
	
	public static DataVersionEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}

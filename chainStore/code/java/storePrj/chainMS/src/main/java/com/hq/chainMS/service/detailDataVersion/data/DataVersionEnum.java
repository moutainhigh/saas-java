package com.hq.chainMS.service.detailDataVersion.data;

public enum DataVersionEnum {
	GoodsDetail,//商品
	ProductDetail,//项目
	MembershipCardDetail,//会员卡
	ProductCardDetail,//次卡
	PackageProjectDetail,//套餐
	Order,//订单
	;
	
	public static DataVersionEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}

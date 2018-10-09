package com.hq.customerMS.common.log;

public enum LogModule {
	Tmp("临时"),
	StartInfo("初始化信息"),
	CUser("用户"),
	Appointment("预约"),
	Sms("发短信"),
	Store("店铺"),
	StoreProductInfo("项目"),
	Image("图片上传"),
	BUser("B端用户"),
	StoreBeauticianInfo("服务人员"),
	LeaguerDetail("客户详情"),
	StoreCardInfo("次卡"),
	Logger("第三方应用日志"),
	AreaCode("国际区号"),
	StoreGoods("商品"),
	GoodsDetail("商品详情"),
	StorePackageProject("套餐"),
	Order("订单"),
	OrderDetail("订单详情"),
	MembershipCardDetail("会员卡详情"),
	ProductCardDetail("次卡详情"),
	StoreConfig("店铺设置"),
	ProductDetail("项目详情"),
	StoreClerkInfo("店员信息"),
	Pay("支付"),
	StoreLeaguerInfo("店铺客户"),
	WeChat("微信"),
	;
	
	private String name;
	
	private LogModule(String nameP){
		this.name = nameP;
	}

	public String getName() {
		return name;
	}
	
}

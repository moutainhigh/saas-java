package com.hq.chainMS.common.log;

public enum LogModule {
	Tmp("临时"),
	StartInfo("启动信息"),
	Img("图片上传"),
	AreaCode("手机区号"),
	Sms("发短信"),
	RedisDao("Redis性能监控"),
	MongoDao("mongodb性能监控"),
	ThreadTracer("线程性能跟踪"),
	Chain("连锁店"),
	BUser("智美通用户"),
	ChainUser("连锁店系统用户"),
	ChainClerk("连锁店员工"),
	Store("智美通店铺"),
	ChainGoods("连锁店商品"),
	GoodsDetail("商品详情"),
	ChainProduct("连锁店项目"),
	ProductDetail("项目详情"),
	DetailDataVersion("数据版本号管理"),
	ChainPackageProject("连锁店套餐管理"),
	PackageProjectDetail("套餐详情"),
	MembershipCardDetail("会员卡详情"),
	ProductCardDetail("次卡详情"),
	ChainCard("连锁店卡包"),
	SellProduct("分配产品适用店铺"),
	StoreClerk("店铺员工"),
	BUserMessage("B端消息中心"),
	DataReport("数据报表"),
	StoreConfig("店铺配置"),
	;
	
	private String name;
	
	private LogModule(String nameP){
		this.name = nameP;
	}

	public String getName() {
		return name;
	}
	
}

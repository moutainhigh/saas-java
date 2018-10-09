package com.hq.common.dataSyn.info;

public enum DataSynType {
	CUser,
	Store,
	Order,
	BUser, 
	StoreClerkInfo,
	OPUser,
	StoreLeaguerInfo,
	Appointment,
	StoreBeauticianInfo,
	StoreProductInfo,
	Message,
	StoreMaterialInfo,
	MaterialRecords,
	SpecialData,
	ClerkSalary,
	StoreCardInfo,
	LeaguerAffair,
	CardRecord,
	MaterialReport,
	OrderReport,
	OrderComment,
	BeauticianProduct,
	Schedule,
	CardOrder,
	AppVersion,
	StoreVipType,
	VipRechargeRecord,
	ServerConfig,
	StoreGoods,
	WorkFlowType,
	WorkFlowData,
	StoreOrder,
	BonusRecord,
	EUser,
	BUserDevice,
	Arrearage,
	AreaCode,
	LeaguerDetail,
	DetailDataVersion,
	GoodsDetail,
	ProductDetail,
	StoreConfig,
	StorePackageProject,
	BillRecord,
	ChainGoods,
	Chain,
	ChainProduct,
	ChainPackageProject,
	ChainCard,
	StoreIncomePay,
	;
	
	
	public static DataSynType valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}

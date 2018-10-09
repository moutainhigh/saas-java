package com.hq.storeClient.service.storeChain.data;

public enum StoreChainItemType {
	Goods("商品"),
	Product("项目"),
	PackagePrj("套餐"),
	MemberCard("会员卡"),
	ProductCard("次卡"),
	
	;

	private String descript;

	private StoreChainItemType(String descript) {
		this.descript = descript;
	}

	public String getDescript() {
		return descript;
	}

	public static StoreChainItemType valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}

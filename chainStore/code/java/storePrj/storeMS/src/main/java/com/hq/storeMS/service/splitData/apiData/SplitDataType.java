package com.hq.storeMS.service.splitData.apiData;

public enum SplitDataType {
	SplitStoreLeaguer("拆分店铺客户数据"),
	SplitStoreCard("拆分店铺卡包数据"),
	SplitStoreGoods("拆分店铺商品数据"),
	SplitStoreProduct("拆分店铺项目数据"),
	SplitProductCardDetail("拆分次卡数据"),
	SplitLeaguerProductCardDetail("拆分客户次卡数据"),
	SplitWorkFlowData("重组工作流数据"),
	SplitLeaguerPrdCardItem("重装客户次卡信息"),
	SplitStoreCardForPrdCard("初始化店铺次卡分类信息"),
	UpdateLeagerPrdCardStatus("更新客户次卡的状态信息"),
	AddOutsiderLeager("添加散客男女数据"),
	SellProductType("分类数据整理"),
	UpdateStoreConfig("更新店铺配置数据"),
	;
	
	private String mark;
	
	private SplitDataType(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}
	
	public static SplitDataType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}

package com.hq.chainStore.service.storeGoods.apiData;

public enum StoreGoodsUpdateType {

	AddGoods("添加商品"),
	UpdateGoods("修改商品信息"), 
	RemoveGoods("删除商品"), 
	
	UpdateGoodsState("修改商品状态"),
	BatchUpdateGoodsState("批量修改商品状态"),
	
	AddGoodsType("添加商品分类"), 
	RemoveGoodsType("删除商品分类"), 
	UpdateGoodsType("修改商品分类"),
	
	AddGoodsToTop("商品置顶"),
	CancelGoodsFromTop("取消置顶"),
	
	AddListFromExcel("从Excel批量添加商品"),
	AddListFromStore("从店铺批量添加商品"),

	PullGoodsFromChain("拉取连锁店商品"),
	CancelChainGoods("取消获取连锁店商品"),
	BatchPullGoodsFromChain("批量拉取连锁店商品"),
	BatchCancelChainGoods("批量取消连锁店商品"),
	;
	
	private String descript;
	
	private StoreGoodsUpdateType(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public static StoreGoodsUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}

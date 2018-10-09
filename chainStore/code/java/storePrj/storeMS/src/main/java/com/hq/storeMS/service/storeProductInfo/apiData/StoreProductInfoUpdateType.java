package com.hq.storeMS.service.storeProductInfo.apiData;

public enum StoreProductInfoUpdateType {
	AddProductInfo("添加项目"),
	UpdateProductInfo("修改项目信息"), 
	RemoveProductInfo("删除项目"), 
	UpdateProductState("修改项目状态"),
	BatchUpdateProductState("批量修改项目状态"),
	
	AddProductType("添加项目分类"), 
	UpdateProductType("修改项目分类"),
	RemoveProductType("删除项目分类"), 
	
	AddProductToTop("项目置顶"),
	CancelProductFromTop("取消置顶"),
	
	AddPackedProductInfo("添加打包项目"),//已过时
	UpdatePackedProductInfo("修改打包项目信息"),//已过时
	UpdatePackedProductState("修改打包项目状态"),//已过时
	RemovePackedProductInfo("删除打包项目"),//已过时
	UpdateProductBeautician("修改项目医美师"),//已过时
	UpdateProductMaterial("修改项目耗材"),//已过时
	
	AddListFromExcel("从excel批量导入项目"),
	AddListFromStore("从店铺批量导入项目"),
	
	/*************************连锁店数据同步**************************************/
	PullProductFromChain("拉取连锁店项目"),
	CancelChainProduct("取消获取连锁店项目"),
	BatchPullProductFromChain("批量拉取连锁店项目"),
	BatchCancelChainProduct("批量取消连锁店项目"),
	;
	
	private String descript;
	
	private StoreProductInfoUpdateType(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public static StoreProductInfoUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}

package com.hq.storeClient.service.storeProductInfo.apiData;

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
	
	AddProductInfoList("批量添加项目"),
	
	/*******************遗留字段********************************/
	AddPackedProductInfo("添加打包项目"),
	UpdatePackedProductInfo("修改打包项目信息"),
	UpdatePackedProductState("修改打包项目状态"),
	RemovePackedProductInfo("删除打包项目"),
	
	UpdateProductBeautician("修改项目医美师"),
	UpdateProductMaterial("修改项目耗材"),
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

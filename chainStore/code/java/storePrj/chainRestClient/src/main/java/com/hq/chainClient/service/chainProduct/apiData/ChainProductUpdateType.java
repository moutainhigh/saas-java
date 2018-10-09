package com.hq.chainClient.service.chainProduct.apiData;

public enum ChainProductUpdateType {
	AddProductInfo("添加项目"),
	UpdateProductInfo("修改项目信息"), 
	RemoveProductInfo("删除项目"), 
	UpdateProductState("修改项目状态"),
	BatchUpdateProductState("批量修改项目状态"),
	
	AddProductType("添加项目分类"), 
	UpdateProductType("修改项目分类"),
	RemoveProductType("删除项目分类"), 
	
	;
	
	private String descript;
	
	private ChainProductUpdateType(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public static ChainProductUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}

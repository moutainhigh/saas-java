package com.hq.chainMS.service.chainGoods.apiData;

public enum ChainGoodsUpdateType {
	AddGoods("添加商品"),
	UpdateGoods("修改商品信息"), 
	RemoveGoods("删除商品"), 
	UpdateGoodsState("修改商品状态"),
	BatchUpdateGoodsState("批量修改商品状态"),
	
	AddGoodsType("添加商品分类"), 
	RemoveGoodsType("删除商品分类"), 
	UpdateGoodsType("修改商品分类"),
	
	AllotStore("分配到店"),
	BatchAllotStore("批量分配到店"),
	;
	
	private String descript;
	
	private ChainGoodsUpdateType(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public static ChainGoodsUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}

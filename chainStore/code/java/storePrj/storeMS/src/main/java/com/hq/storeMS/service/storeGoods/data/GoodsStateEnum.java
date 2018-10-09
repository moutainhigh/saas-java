package com.hq.storeMS.service.storeGoods.data;

public enum GoodsStateEnum {
	Open("已上架"),
	Close("已下架"),
	Remove("删除"),
	;
	
	private String descript;
	
	private GoodsStateEnum(String descript) {
		this.descript = descript;
	}

	public String getDescript() {
		return descript;
	}

	public static GoodsStateEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}

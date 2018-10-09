package com.hq.storeMS.service.store.data;

public enum StoreOperateEnum {
	JoinChainHandler("加盟连锁店"),
	RelieveStore("解除店铺连锁店"),
	;
	
	private String descript;
	
	private StoreOperateEnum(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public static StoreOperateEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

	
}

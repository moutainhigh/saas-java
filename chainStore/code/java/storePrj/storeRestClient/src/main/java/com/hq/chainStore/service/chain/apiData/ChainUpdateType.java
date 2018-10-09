package com.hq.chainStore.service.chain.apiData;

public enum ChainUpdateType {
	UpdateChainInfo("修改店铺信息"),
	
	ApplyChain("申请加盟连锁店"),
	HandleApplyChain("处理加盟申请"),
	BatchHandleApplyChain("批量处理加盟申请"),
	
	RelieveStore("解除店铺关联"),
	;

	private String descript;

	private ChainUpdateType(String descript) {
		this.descript = descript;
	}

	public String getDescript() {
		return descript;
	}

	public static ChainUpdateType valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

}

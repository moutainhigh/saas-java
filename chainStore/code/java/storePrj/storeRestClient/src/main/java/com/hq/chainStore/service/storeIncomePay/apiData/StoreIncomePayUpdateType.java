package com.hq.chainStore.service.storeIncomePay.apiData;

public enum StoreIncomePayUpdateType {

	AddIncomePayType("添加收支分类"), 
	RemoveIncomePayType("删除收支分类"), 
	UpdateIncomePayType("修改收支分类"),
	;
	private String descript;
	
	private StoreIncomePayUpdateType(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public static StoreIncomePayUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}

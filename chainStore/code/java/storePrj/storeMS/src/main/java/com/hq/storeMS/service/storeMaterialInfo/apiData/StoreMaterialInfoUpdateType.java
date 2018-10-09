package com.hq.storeMS.service.storeMaterialInfo.apiData;

public enum StoreMaterialInfoUpdateType {

	AddMaterialInfo("添加耗材"),    
	UpdateMaterialInfo("修改耗材信息"), 
	RemoveMaterial("删除耗材"), 
	RemoveMaterialInventory("更新库存"), 
	;
	
	private String descript;
	
	private StoreMaterialInfoUpdateType(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public static StoreMaterialInfoUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}

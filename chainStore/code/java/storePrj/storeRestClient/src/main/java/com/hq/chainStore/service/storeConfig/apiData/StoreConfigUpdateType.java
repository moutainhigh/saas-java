package com.hq.chainStore.service.storeConfig.apiData;

public enum StoreConfigUpdateType {
	UpdateBaseAttribute("设置客户基础属性"),
	
	AddExpandAttribute("添加客户扩展属性"),
	SortExpandAttribute("扩展属性升降操作"),
	UpdateExpandAttribute("更新扩展属性信息"),
	StatusExpandAttribute("设置属性启用必填信息"),
	
	AddLeaguerOrigin("添加客户来源"), 
	RemoveLeaguerOrigin("删除客户来源"), 
	UpdateLeaguerOrigin("修改客户来源"),
	
	UpdateLeaguerType("设置客户类型"),
	
	UpdateAppointTime("设置预约时间段"),
	
	AddCancelReason("添加取消原因"),
	RemoveCancelReason("删除取消原因"),
	UpdateCancelReason("更新取消原因"),
	;
	
	private String descript;
	
	private StoreConfigUpdateType(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public static StoreConfigUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}

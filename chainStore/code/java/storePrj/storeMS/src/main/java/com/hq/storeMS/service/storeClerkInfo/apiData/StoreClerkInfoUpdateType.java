package com.hq.storeMS.service.storeClerkInfo.apiData;

public enum StoreClerkInfoUpdateType {
	AddStoreAdminRole("添加岗位"),
	UpdateStoreAdminRole("更新岗位信息"),
	ApplyClerk("申请加入店铺"),
	HandleApplyClerk("处理员工申请"),
	AddClerk("添加员工"), 
	ReomveRoleOfClerk("移除员工岗位"),
	AddRoleSet2Clerk("赋予员工岗位"),
	ReomveClerk("删除员工"),
	SetMonthPayDays("设置店铺工资月结天数"),
	HandleGroupApplyClerk("批量审核员工"),
	RemoveStoreAdminRole("删除岗位信息"),
	;
	
	private String descript;
	
	private StoreClerkInfoUpdateType(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public static StoreClerkInfoUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}

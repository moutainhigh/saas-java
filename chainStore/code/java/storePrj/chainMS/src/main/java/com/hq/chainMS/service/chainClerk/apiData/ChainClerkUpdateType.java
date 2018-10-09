package com.hq.chainMS.service.chainClerk.apiData;

public enum ChainClerkUpdateType {
	AddAdminRole("添加岗位"), 
	UpdateAdminRole("更新岗位信息"), 
	RemoveAdminRole("删除岗位信息"),

	AddClerk("添加员工"), 
	ReomveClerk("删除员工"), 

	SaveRoleSet2Clerk("赋予员工角色"),
	UpdateClerk("修改用户基本信息"),
	
	AllotStore("分配到店"),
	BatchAllotStore("批量分配到店"),
	
	;

	private String descript;

	private ChainClerkUpdateType(String descript) {
		this.descript = descript;
	}

	public String getDescript() {
		return descript;
	}

	public static ChainClerkUpdateType valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}

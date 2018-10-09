package com.hq.storeMS.service.opuser.data.adminRole;

public enum OPAdminPermEnum {
	
	OP_SUPER("op:*:*:*","saas平台超级管理员"),
	OP_ADMIN("op:admin:*:*","saas平台管理员"),
	OP_STORE("op:store:*:*","saas平台加盟店管理"),
	OP_STORE_CHECKER("op:store:checker:*","saas平台加盟店审核"),
	OP_STORE_MACHINE("op:store:machine:*","saas平台仪器管理"),
	
	;
	
	private String perm;
	
	//权限描述
	private String descript;
	
	private OPAdminPermEnum(String permP, String descriptP){
		this.perm = permP;
		this.descript = descriptP;
	}

	public String getPerm() {
		return perm;
	}

	public String getDescript() {
		return descript;
	}
	
	public static OPAdminPermEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
	
	
	
}

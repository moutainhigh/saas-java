package com.hq.storeManagerMS.service.muser.data;

/**
 * 系统固有的角色
 */
public enum SysAdminEnum {
	ADMIN("admin", "honkon2018admin"), 
	
	;

	private String account;
	private String password;

	private SysAdminEnum(String accountP, String passwordP) {
		this.account = accountP;
		this.password = passwordP;
	}

	public static SysAdminEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

	public String getAccount() {
		return account;
	}

	public String getPassword() {
		return password;
	}

}

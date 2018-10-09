package com.hq.chainClient.service.chainClerk.data.adminRole;

import com.zenmind.common.StringFormatUtil;

public enum AdminPermEnum {
	BOSS("chain:*:*:{}","老板"),
	CHAIN_CLERK_ADMIN("chain:chainClerk:*:{}","连锁店员工管理"),
	CARD_ADMIN("chain:card:*:{}","会员卡管理"),
	SELL_PRODUCT_ADMIN("chain:sellProduct:*:{}","产品库管理"),
	CHAIN_ADMIN("chain:chain:*:{}","店铺管理"),
	;
	
	//权限模板{} 会被chainId代替
	private String perm;
	
	//权限描述
	private String descript;
	
	private AdminPermEnum(String permP, String descriptP){
		this.perm = permP;
		this.descript = descriptP;
	}

	public String getPerm(long chainId) {
		return StringFormatUtil.format(perm, chainId);
	}

	public String getDescript() {
		return descript;
	}
	
	public static AdminPermEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
	
	
	
}

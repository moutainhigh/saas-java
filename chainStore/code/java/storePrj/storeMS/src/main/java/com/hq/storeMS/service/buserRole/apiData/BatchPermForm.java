package com.hq.storeMS.service.buserRole.apiData;

import java.util.HashSet;
import java.util.Set;

public class BatchPermForm {
	// 用户ID
	private Set<Long> buserIds=new HashSet<Long>();
	//StoreAdminPermEnum
	private Set<Integer> perms=new HashSet<Integer>();
	//OperateEnum
	private int operate;

    public static BatchPermForm newInstance() {
        BatchPermForm data = new BatchPermForm();
        return data;
    }
    
    public OperateEnum getOperateEnum() {
    	return OperateEnum.valueOf(operate);
    }

	public Set<Long> getBuserIds() {
		return buserIds;
	}

	public void setBuserIds(Set<Long> buserIds) {
		this.buserIds = buserIds;
	}

	public Set<Integer> getPerms() {
		return perms;
	}

	public void setPerms(Set<Integer> perms) {
		this.perms = perms;
	}

	public int getOperate() {
		return operate;
	}

	public void setOperate(int operate) {
		this.operate = operate;
	}
}

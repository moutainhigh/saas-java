package com.hq.chainStore.service.storeClerkInfo.data;

public enum ApplyState {

	Pending,  //审核中
	Approved, //审核通过
	Rejected, //审核被拒绝
	;
	
	public static ApplyState valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
	
	
}

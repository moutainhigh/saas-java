package com.hq.chainStore.service.store.data;

public enum StoreState {
	New,
	Checking,
	CheckFail,
	Open,
	Close,
	;
	public static StoreState valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}

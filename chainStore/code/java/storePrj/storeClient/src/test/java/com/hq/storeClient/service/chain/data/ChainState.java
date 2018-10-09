package com.hq.storeClient.service.chain.data;

public enum ChainState {
	Open,
	Close,
	;
	public static ChainState valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}

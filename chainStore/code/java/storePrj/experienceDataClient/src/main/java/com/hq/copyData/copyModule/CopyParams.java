package com.hq.copyData.copyModule;

import com.hq.chainStore.service.buser.data.BUser;

public class CopyParams {
	private long sourceStoreId;
	private long targetStoreId;

	private BUser sourceBoss;
	private BUser targetBoss;
	
	public static CopyParams newInstance() {
		CopyParams data = new CopyParams();
		return data;
	}

	public long getSourceStoreId() {
		return sourceStoreId;
	}

	public CopyParams setSourceStoreId(long sourceStoreId) {
		this.sourceStoreId = sourceStoreId;
		return this;
	}

	public long getTargetStoreId() {
		return targetStoreId;
	}

	public CopyParams setTargetStoreId(long targetStoreId) {
		this.targetStoreId = targetStoreId;
		return this;
	}

	public BUser getSourceBoss() {
		return sourceBoss;
	}

	public CopyParams setSourceBoss(BUser boss) {
		this.sourceBoss = boss;
		return this;
	}

	public BUser getTargetBoss() {
		return targetBoss;
	}

	public CopyParams setTargetBoss(BUser newBoss) {
		this.targetBoss = newBoss;
		return this;
	}
}

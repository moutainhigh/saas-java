package com.hq.copyData.copyModule;

public abstract class AbstractCopyModule {
	protected CopyParams params;

	// 具体实现的拷贝业务逻辑
	public abstract void copy();

	public void copyDataWithParam(CopyParams paramsP) {
		setParams(paramsP);
		copy();
	}

	public CopyParams getParams() {
		return params;
	}

	public void setParams(CopyParams params) {
		this.params = params;
	}

	public long getSourceBossId() {
		return params.getSourceBoss().getId();
	}

	public long getTargetBossId() {
		return params.getTargetBoss().getId();
	}

	public long getSourceStoreId() {
		return params.getSourceStoreId();
	}

	public long getTargetStoreId() {
		return params.getTargetStoreId();
	}
}

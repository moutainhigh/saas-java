package com.hq.chainStore.service.storeVip.data;

import javax.persistence.Table;

import com.hq.common.dataSyn.bs.IntfSynData;

@Table(name = "storeVip")
public class StoreVip implements IntfSynData{
	
	private boolean success;
	private String tips;

	public static StoreVip newInstance(){
		return new StoreVip();
	}

	public boolean isSuccess() {
		return success;
	}

	public StoreVip setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public String getTips() {
		return tips;
	}

	public StoreVip setTips(String tips) {
		this.tips = tips;
		return this;
	}

	@Override
	public Object targetId() {
		return null;
	}

	@Override
	public long targetVer() {
		return 0;
	}
	
}

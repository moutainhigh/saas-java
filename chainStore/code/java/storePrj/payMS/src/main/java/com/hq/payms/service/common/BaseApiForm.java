package com.hq.payms.service.common;

public class BaseApiForm {
	
	//storeId用于查询商户账号、密钥等信息; 
	private long storeId; 
	
	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
	
	public boolean isValid() {
		return this.storeId > 0L;
	}
	
}

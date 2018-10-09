package com.hq.storeMS.service.storePackageProject.apiData;

public class PackageCancelForm {
	private long chainId;
	private String id;

	public static PackageCancelForm newInstance() {
		return new PackageCancelForm();
	}

	public long getChainId() {
		return chainId;
	}

	public void setChainId(long chainId) {
		this.chainId = chainId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}

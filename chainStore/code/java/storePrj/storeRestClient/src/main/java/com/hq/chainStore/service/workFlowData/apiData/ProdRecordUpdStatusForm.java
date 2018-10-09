package com.hq.chainStore.service.workFlowData.apiData;

public class ProdRecordUpdStatusForm {
	private String id;
	private int status;
	
	/********************************遗留字段********************************/
	private String prodId;

	public static ProdRecordUpdStatusForm newInstance() {
		return new ProdRecordUpdStatusForm();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}

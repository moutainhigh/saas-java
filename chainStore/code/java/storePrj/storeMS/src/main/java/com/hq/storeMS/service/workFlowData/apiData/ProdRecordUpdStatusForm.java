package com.hq.storeMS.service.workFlowData.apiData;

import com.hq.storeMS.service.workFlowData.data.ProdRecord;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class ProdRecordUpdStatusForm {
	private String id;
	private int status;
	
	/********************************遗留字段********************************/
	private String prodId;

	public static ProdRecordUpdStatusForm newInstance() {
		return new ProdRecordUpdStatusForm();
	}

	public void updateProdRecord(ProdRecord data) {
		FastBeanCopyer.getInstance().copy(this, data);
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

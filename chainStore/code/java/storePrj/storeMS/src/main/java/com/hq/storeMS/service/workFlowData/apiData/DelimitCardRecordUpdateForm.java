package com.hq.storeMS.service.workFlowData.apiData;

import com.hq.storeMS.service.workFlowData.data.DelimitCardRecord;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class DelimitCardRecordUpdateForm {
	private String delimitCardId;
	// 抵消次数
	private int count;

	public static DelimitCardRecordUpdateForm newInstance() {
		return new DelimitCardRecordUpdateForm();
	}

	public void updateDelimitCardRecord(DelimitCardRecord data) {
		FastBeanCopyer.getInstance().copy(this, data);
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDelimitCardId() {
		return delimitCardId;
	}

	public void setDelimitCardId(String delimitCardId) {
		this.delimitCardId = delimitCardId;
	}
}

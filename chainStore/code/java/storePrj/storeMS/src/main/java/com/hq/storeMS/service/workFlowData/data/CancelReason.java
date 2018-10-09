package com.hq.storeMS.service.workFlowData.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class CancelReason {
	// 操作员ID
	private long operatorId;
	// 操作员名称
	private String operator;
	// 作废原因
	private String cancelReason;

	public static CancelReason newInstance() {
		return new CancelReason();
	}

	public long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(long operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

}

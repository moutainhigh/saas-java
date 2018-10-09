package com.hq.storeMS.service.appointment.data;

import com.zenmind.dataSyn.annotation.SynClass;

@Deprecated
@SynClass
public class OperationNote {
	private int status;
	private long beauticianId;
	private String beauticianName;
	private int origin;
	private long operatorId;
	private String operatorName;
	private long updateTime;
	private String mark;

	public static OperationNote newInstance() {
		OperationNote data = new OperationNote();
		data.updateTime = System.currentTimeMillis();
		return data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getBeauticianId() {
		return beauticianId;
	}

	public void setBeauticianId(long beauticianId) {
		this.beauticianId = beauticianId;
	}

	public String getBeauticianName() {
		return beauticianName;
	}

	public void setBeauticianName(String beauticianName) {
		this.beauticianName = beauticianName;
	}

	public int getOrigin() {
		return origin;
	}

	public void setOrigin(int origin) {
		this.origin = origin;
	}

	public long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(long operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "OperationNote [status=" + status + ", beauticianId="
				+ beauticianId + ", beauticianName=" + beauticianName
				+ ", origin=" + origin + ", operatorId=" + operatorId
				+ ", operatorName=" + operatorName + ", updateTime="
				+ updateTime + ", mark=" + mark + "]";
	}

}

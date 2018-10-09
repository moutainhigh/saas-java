package com.hq.storeManagerMS.service.muser.apiData;

import com.hq.storeManagerMS.service.muser.data.MUser;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class MUserUpdateStatusApiData {
	private long muserId;
	private int status;

	public static MUserUpdateStatusApiData newInstance() {
		return new MUserUpdateStatusApiData();
	}

	public void updateMUser(MUser data) {
		FastBeanCopyer.getInstance().copy(this, data);
	}

	public long getMuserId() {
		return muserId;
	}

	public void setMuserId(long muserId) {
		this.muserId = muserId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}

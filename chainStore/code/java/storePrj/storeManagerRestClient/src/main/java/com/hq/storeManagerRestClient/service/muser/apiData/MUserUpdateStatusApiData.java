package com.hq.storeManagerRestClient.service.muser.apiData;


public class MUserUpdateStatusApiData {
	private long muserId;
	private int status;

	public static MUserUpdateStatusApiData newInstance() {
		return new MUserUpdateStatusApiData();
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

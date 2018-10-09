package com.hq.storeMS.service.opuser.apiData;

import com.hq.storeMS.service.opuser.data.OPUser;

public class OPUserUpdateInfoApiData {
	
	private long opuserId;
	
	private String name;
	
	public static OPUserUpdateInfoApiData newInstance(){
		return new OPUserUpdateInfoApiData();
	}

	public long getOpuserId() {
		return opuserId;
	}

	public void setOpuserId(long opuserId) {
		this.opuserId = opuserId;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void update(OPUser opuser){
		opuser.setName(this.name);
	}

	
	
}

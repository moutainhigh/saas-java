package com.hq.chainStore.service.buserDevice.apiData;

public class MClientUpdateBandingForm {
	
	//绑定系统 MClientBandingSystemEnum
	private int bandingSystem;
	//绑定账户
	private String bandingAccount;
	
	
	
	public static MClientUpdateBandingForm newInstance() {
		return new MClientUpdateBandingForm();
	}

	public int getBandingSystem() {
		return bandingSystem;
	}

	public void setBandingSystem(int bandingSystem) {
		this.bandingSystem = bandingSystem;
	}

	public String getBandingAccount() {
		return bandingAccount;
	}

	public void setBandingAccount(String bandingAccount) {
		this.bandingAccount = bandingAccount;
	}
	
	
}

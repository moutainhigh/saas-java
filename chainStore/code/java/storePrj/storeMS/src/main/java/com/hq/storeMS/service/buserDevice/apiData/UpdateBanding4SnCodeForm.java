package com.hq.storeMS.service.buserDevice.apiData;

import com.zenmind.common.beanCopy.FastBeanCopyer;

/***
 * B->iot
 */

public class UpdateBanding4SnCodeForm {
		//sn
		private String snCode;
		//绑定系统 MClientBandingSystemEnum
		private int bandingSystem;
		//绑定账户
		private String bandingAccount;
		
		
		public static UpdateBanding4SnCodeForm newInstance() {
			return new UpdateBanding4SnCodeForm();
		}
		
		public MClientUpdateBandingForm toMClientUpdateBandingForm() {
			MClientUpdateBandingForm form = MClientUpdateBandingForm.newInstance();
			FastBeanCopyer.getInstance().copy(this, form);
			return form;
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

		public String getSnCode() {
			return snCode;
		}

		public void setSnCode(String snCode) {
			this.snCode = snCode;
		}
		
		
}

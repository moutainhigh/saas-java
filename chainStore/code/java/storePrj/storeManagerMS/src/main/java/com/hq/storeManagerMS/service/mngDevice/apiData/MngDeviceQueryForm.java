package com.hq.storeManagerMS.service.mngDevice.apiData;

public class MngDeviceQueryForm {
		//SN
		private String snCode;
		//绑定账户
		private String bandingAccount;
		
		//每页条数
		private int pageItemCount;
		//页号
		private int pageNo;
		
		public static MngDeviceQueryForm newInstance() {
			MngDeviceQueryForm params = new MngDeviceQueryForm();
			params.pageItemCount = 20;
			params.pageNo = 1;
			return params;
		}
		
		public String getSnCode() {
			return snCode;
		}
		public void setSnCode(String snCode) {
			this.snCode = snCode;
		}
		public String getBandingAccount() {
			return bandingAccount;
		}
		public void setBandingAccount(String bandingAccount) {
			this.bandingAccount = bandingAccount;
		}
		public int getPageItemCount() {
			return pageItemCount;
		}
		public void setPageItemCount(int pageItemCount) {
			this.pageItemCount = pageItemCount;
		}
		public int getPageNo() {
			return pageNo;
		}
		public void setPageNo(int pageNo) {
			this.pageNo = pageNo;
		}
		
}

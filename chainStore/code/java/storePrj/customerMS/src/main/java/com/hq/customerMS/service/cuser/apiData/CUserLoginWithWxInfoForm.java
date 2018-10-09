package com.hq.customerMS.service.cuser.apiData;

import com.hq.storeClient.service.wxJscode.apiData.DecryptWxAppletForm;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class CUserLoginWithWxInfoForm {
	private String jscode;
	private String encryptedData;
	private String iv;

	public static CUserLoginWithWxInfoForm newInstance() {
		CUserLoginWithWxInfoForm data = new CUserLoginWithWxInfoForm();
		return data;
	}

	public DecryptWxAppletForm toDecryptWxAppletForm() {
		DecryptWxAppletForm data = DecryptWxAppletForm.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		return data;
	}

	public String getJscode() {
		return jscode;
	}

	public void setJscode(String jscode) {
		this.jscode = jscode;
	}

	public String getEncryptedData() {
		return encryptedData;
	}

	public void setEncryptedData(String encryptedData) {
		this.encryptedData = encryptedData;
	}

	public String getIv() {
		return iv;
	}

	public void setIv(String iv) {
		this.iv = iv;
	}

}

package com.hq.storeMS.service.bossPayInfo.bs;

import com.hq.payRestClient.service.bossPayInfo.apiData.BossPayInfoAddApiForm;
import com.hq.payRestClient.service.bossPayInfo.data.BossPayInfo;
import com.hq.payRestClient.service.bossPayInfo.data.BossPayInfoRestDAO;
import com.hq.storeFileClient.service.file.data.FileResp;
import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.validate.AppIdThreadLocal;
import com.hq.storeMS.common.validate.ValidateInfoThreadLocal;
import com.hq.storeMS.common.validate.info.ValidateInfo;
import com.hq.storeMS.service.bossPayInfo.data.CertFileRestDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class BossPayInfoDataHolder {

	public static BossPayInfoDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(BossPayInfoDataHolder.class);
	}
	
	public BossPayInfo add(BossPayInfoAddApiForm form){
		AppIdThreadLocal.getInstance().set(getValidateInfo());
		BossPayInfo data = BossPayInfoRestDAO.getInstance().add(form);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
	public BossPayInfo update(long id, BossPayInfoAddApiForm updateForm){
		AppIdThreadLocal.getInstance().set(getValidateInfo());
		BossPayInfo data = BossPayInfoRestDAO.getInstance().update(id, updateForm);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

	public BossPayInfo get(long id) {
		AppIdThreadLocal.getInstance().set(getValidateInfo());
		BossPayInfo data = BossPayInfoRestDAO.getInstance().get(id);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

	public BossPayInfo findByStoreId(long storeId){
		AppIdThreadLocal.getInstance().set(getValidateInfo());
		BossPayInfo data = BossPayInfoRestDAO.getInstance().findByStoreId(storeId);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

	public FileResp uploadCertFile(Object postParam) {
		AppIdThreadLocal.getInstance().set(getValidateInfo());
		FileResp data = CertFileRestDAO.getInstance().uploadCertFile(postParam);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
	private ValidateInfo getValidateInfo() {
		ValidateInfo info = ValidateInfo.newInstance();
		info.setAppId(ServerConstants.appId);
		info.setBossId(ValidateInfoThreadLocal.getInstance().getBossId());
		return info;
	}
}

package com.hq.storeMS.service.charge.bs;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.validate.AppIdThreadLocal;
import com.hq.storeMS.service.charge.data.ChargeCacheDAO;
import com.hq.storeManagerRestClient.common.restClientResp.PageResp;
import com.hq.storeManagerRestClient.service.charge.apiData.ChargeAddForm;
import com.hq.storeManagerRestClient.service.charge.apiData.ChargeQueryForm;
import com.hq.storeManagerRestClient.service.charge.apiData.ChargeUpdateApiForm;
import com.hq.storeManagerRestClient.service.charge.bs.ChargeClientMgr;
import com.hq.storeManagerRestClient.service.charge.data.Charge;
import com.zenmind.common.hotSwap.HotSwap;

public class ChargeDataHolder {
	
	public static ChargeDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(ChargeDataHolder.class);
	}
	
	public PageResp<Charge> findChargePageInfo(ChargeQueryForm queryForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		PageResp<Charge> data = ChargeClientMgr.getInstance().findChargePageInfo(queryForm);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
	public Charge addCharge(ChargeAddForm formInfo) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		Charge data = ChargeClientMgr.getInstance().addCharge(formInfo);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
	public Charge updateCharge(long chargeId, ChargeUpdateApiForm updateForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		Charge data = ChargeClientMgr.getInstance().updateCharge(chargeId, updateForm);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
	public Charge get(long id) {
		Charge data = ChargeCacheDAO.getInstance().get(id);
		if(data != null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = ChargeClientMgr.getInstance().getCharge(id);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

}

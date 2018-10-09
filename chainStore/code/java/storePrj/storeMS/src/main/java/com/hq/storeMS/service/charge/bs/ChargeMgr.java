package com.hq.storeMS.service.charge.bs;

import com.hq.storeManagerRestClient.common.restClientResp.PageResp;
import com.hq.storeManagerRestClient.service.charge.apiData.ChargeAddForm;
import com.hq.storeManagerRestClient.service.charge.apiData.ChargeQueryForm;
import com.hq.storeManagerRestClient.service.charge.apiData.ChargeUpdateApiForm;
import com.hq.storeManagerRestClient.service.charge.data.Charge;
import com.zenmind.common.hotSwap.HotSwap;

public class ChargeMgr {

	public static ChargeMgr getInstance(){
		return HotSwap.getInstance().getSingleton(ChargeMgr.class);
	}
	
	public PageResp<Charge> findChargePageInfo(ChargeQueryForm queryForm) {
		return ChargeDataHolder.getInstance().findChargePageInfo(queryForm);
	}
	
	public Charge addCharge(ChargeAddForm formInfo){
		return ChargeDataHolder.getInstance().addCharge(formInfo);
	}
	
	public Charge getCharge(long id){
		return ChargeDataHolder.getInstance().get(id);
	}
	
	public Charge updateCharge(long chargeId, ChargeUpdateApiForm updateForm){
		return ChargeDataHolder.getInstance().updateCharge(chargeId, updateForm);
	}
	
}

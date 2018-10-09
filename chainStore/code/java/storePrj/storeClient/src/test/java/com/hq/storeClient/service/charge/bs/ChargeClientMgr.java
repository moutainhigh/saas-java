package com.hq.storeClient.service.charge.bs;

import com.hq.storeClient.common.exception.AppNameAuthUtils;
import com.hq.storeClient.common.exception.AppNameEnum;
import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.charge.apiData.ChargeAddForm;
import com.hq.storeClient.service.charge.apiData.ChargeQueryForm;
import com.hq.storeClient.service.charge.apiData.ChargeUpdateApiForm;
import com.hq.storeClient.service.charge.apiData.ChargeUpdateInfoForm;
import com.hq.storeClient.service.charge.apiData.ChargeUpdateStatusForm;
import com.hq.storeClient.service.charge.apiData.ChargeUpdateType;
import com.hq.storeClient.service.charge.data.Charge;
import com.hq.storeClient.service.charge.data.ChargeDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class ChargeClientMgr {

    public static ChargeClientMgr getInstance() {
        return HotSwap.getInstance().getSingleton(ChargeClientMgr.class);
    }

    public PageResp<Charge> findChargePageInfo(ChargeQueryForm queryForm) {
    	AppNameAuthUtils.getInstance().checkMS(AppNameEnum.StoreMngMS);
        final String findPath = "findChargePageInfo";
        return ChargeDAO.getInstance().findByCond(findPath, queryForm);
    }

    public Charge getCharge(long chargeId) {
    	AppNameAuthUtils.getInstance().checkMS(AppNameEnum.StoreMngMS);
        return ChargeDAO.getInstance().get(chargeId);
    }

    public void deleteCharge(long chargeId) {
    	AppNameAuthUtils.getInstance().checkMS(AppNameEnum.StoreMngMS);
        ChargeDAO.getInstance().delete(chargeId);
    }

    public Charge updateCharge(long chargeId, ChargeUpdateApiForm updateForm) {
    	AppNameAuthUtils.getInstance().checkMS(AppNameEnum.StoreMngMS);
        return ChargeDAO.getInstance().updateWithResp(chargeId, updateForm);
    }

    public Charge updateChargeInfo(long chargeId, ChargeUpdateInfoForm inputForm) {
    	ChargeUpdateApiForm updateForm = ChargeUpdateApiForm.newInstance();
    	updateForm.setUpdateType(ChargeUpdateType.UpdateInfo.ordinal());
    	updateForm.setChargeUpdateInfoForm(inputForm);
    	return updateCharge(chargeId, updateForm);
    }
    
    public Charge updateChargeStatus(long chargeId, ChargeUpdateStatusForm inputForm) {
    	ChargeUpdateApiForm updateForm = ChargeUpdateApiForm.newInstance();
    	updateForm.setUpdateType(ChargeUpdateType.UpdateStatus.ordinal());
    	updateForm.setChargeUpdateStatusForm(inputForm);
    	return updateCharge(chargeId, updateForm);
    }
    
    public Charge addCharge(ChargeAddForm formInfo) {
    	AppNameAuthUtils.getInstance().checkMS(AppNameEnum.StoreMngMS);
        return ChargeDAO.getInstance().add(formInfo);
    }

}

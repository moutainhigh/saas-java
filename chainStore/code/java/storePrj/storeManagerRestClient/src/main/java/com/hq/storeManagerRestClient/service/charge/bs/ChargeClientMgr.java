package com.hq.storeManagerRestClient.service.charge.bs;

import com.hq.storeManagerRestClient.common.restClientResp.PageResp;
import com.hq.storeManagerRestClient.common.utils.StringUtils4Client;
import com.hq.storeManagerRestClient.service.charge.apiData.ChargeAddForm;
import com.hq.storeManagerRestClient.service.charge.apiData.ChargeQueryForm;
import com.hq.storeManagerRestClient.service.charge.apiData.ChargeUpdateApiForm;
import com.hq.storeManagerRestClient.service.charge.apiData.ChargeUpdateInfoForm;
import com.hq.storeManagerRestClient.service.charge.apiData.ChargeUpdatePayInfoForm;
import com.hq.storeManagerRestClient.service.charge.apiData.ChargeUpdateStatusForm;
import com.hq.storeManagerRestClient.service.charge.apiData.ChargeUpdateType;
import com.hq.storeManagerRestClient.service.charge.data.Charge;
import com.hq.storeManagerRestClient.service.charge.data.ChargeDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class ChargeClientMgr {

    public static ChargeClientMgr getInstance() {
        return HotSwap.getInstance().getSingleton(ChargeClientMgr.class);
    }

    /**
     * 查询 收费 列表
     *
     * @param queryForm
     * @return
     */
    public PageResp<Charge> findChargePageInfo(ChargeQueryForm queryForm) {
        final String findPath = "findChargePageInfo";
        return ChargeDAO.getInstance().findByCond(findPath, queryForm);
    }

    /**
     * 根据 id 获取 Charge
     *
     * @param chargeId
     * @return
     */
    public Charge getCharge(long chargeId) {
        String uriPath = StringUtils4Client.format("{}",chargeId);
        return ChargeDAO.getInstance().findOne(uriPath);
    }

    /**
     * 根据 id 删除 Charge
     *
     * @param chargeId
     */
    public void deleteCharge(long chargeId) {
        String id = StringUtils4Client.format("{}",chargeId);
        ChargeDAO.getInstance().delete(id);
    }

    /**
     * 编辑 Charge
     *
     * @param updateForm
     */
    public Charge updateCharge(long chargeId, ChargeUpdateApiForm updateForm) {
    	String id = StringUtils4Client.format("{}", chargeId);
        return ChargeDAO.getInstance().updateWithResp(id, updateForm);
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
    
    public Charge updateChargePayInfo(long chargeId, ChargeUpdatePayInfoForm inputForm) {
    	ChargeUpdateApiForm updateForm = ChargeUpdateApiForm.newInstance();
    	updateForm.setUpdateType(ChargeUpdateType.UpdatePayInfo.ordinal());
    	updateForm.setChargeUpdatePayInfoForm(inputForm);
    	return updateCharge(chargeId, updateForm);
    }
    
    /**
     * 新增 Charge
     *
     * @param formInfo
     * @return
     */
    public Charge addCharge(ChargeAddForm formInfo) {
        Charge charge = ChargeDAO.getInstance().add(formInfo);
        return charge;
    }

}

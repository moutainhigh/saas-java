package com.hq.chainStore.service.incomePay.bs;

import com.hq.chainStore.service.incomePay.apiData.*;
import com.hq.chainStore.service.incomePay.data.IncomePay;
import com.hq.chainStore.service.incomePay.data.IncomePayDAO;
import com.hq.chainStore.service.common.PageResp;
import com.hq.common.StringUtils4Client;
import com.zenmind.common.hotSwap.HotSwap;

public class IncomePayMgr {

    public static IncomePayMgr getInstance() {
        return HotSwap.getInstance().getSingleton(IncomePayMgr.class);
    }

    /**
     * 查询 收支 列表
     *
     * @param queryForm
     * @return
     */
    public PageResp<IncomePay> findIncomePayPageInfo(IncomePayQueryForm queryForm) {
        final String findPath = "findIncomePayPageInfo";
        return IncomePayDAO.getInstance().findByCond(findPath, queryForm);
    }

    /**
     * 根据 id 获取 IncomePay
     *
     * @param storeId
     * @param incomePayId
     * @return
     */
    public IncomePay getIncomePay(long storeId, long incomePayId) {
        String uriPath = StringUtils4Client.format("{}/{}", storeId, incomePayId);
        return IncomePayDAO.getInstance().findOne(uriPath);
    }

    /**
     * 根据 id 删除 IncomePay
     *
     * @param storeId
     * @param incomePayId
     */
    public void deleteIncomePay(long storeId, long incomePayId) {
        String id = StringUtils4Client.format("{}/{}", storeId, incomePayId);
        IncomePayDAO.getInstance().delete(id);
    }

    /**
     * 编辑 IncomePay
     *
     * @param updateForm
     */
    public void updateIncomePay(long storeId, long incomePayId, IncomePayUpdateApiForm updateForm) {
    	String id = StringUtils4Client.format("{}/{}", storeId, incomePayId);
        IncomePayDAO.getInstance().update(id, updateForm);
    }

    public void updateIncomePayInfo(long storeId, long incomePayId, IncomePayUpdateInfoForm inputForm) {
    	IncomePayUpdateApiForm updateForm = IncomePayUpdateApiForm.newInstance();
    	updateForm.setStoreId(storeId);
    	updateForm.setUpdateType(IncomePayUpdateType.UpdateInfo.ordinal());
    	updateForm.setIncomePayUpdateInfoForm(inputForm);
    	updateIncomePay(storeId, incomePayId, updateForm);
    }
    
    /**
     * 新增 IncomePay
     *
     * @param formInfo
     * @return
     */
    public IncomePay addIncomePay(IncomePayAddForm formInfo) {
        IncomePay incomePay = IncomePayDAO.getInstance().add(formInfo);
        return incomePay;
    }

}

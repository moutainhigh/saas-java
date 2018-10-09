package com.hq.storeMS.service.storeIncomePay.bs.updateHandle;

import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;
import com.hq.storeMS.service.storeIncomePay.apiData.StoreIncomePayUpdateForm;
import com.hq.storeMS.service.storeIncomePay.apiData.StoreIncomePayUpdateType;
import com.zenmind.common.hotSwap.HotSwap;

import java.util.HashMap;
import java.util.Map;

public class IncomePayHandlerHelper {

    public static IncomePayHandlerHelper getInstance() {
        return HotSwap.getInstance().getSingleton(IncomePayHandlerHelper.class);
    }

    private Map<StoreIncomePayUpdateType, IIncomePayHandler> handleMapper = new HashMap<StoreIncomePayUpdateType, IIncomePayHandler>();

    public IncomePayHandlerHelper() {

        handleMapper.put(StoreIncomePayUpdateType.AddIncomePayType, new IIncomePayHandler() {
            @Override
            public OperateTips update(long storeId, StoreIncomePayUpdateForm formInfo) {
            	BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.INCOME_PAY_ADMIN);
                return IncomePayTypeMgr.getInstance().addIncomePayType(storeId, formInfo.getIncomePayTypeAddForm());
            }
        });
        handleMapper.put(StoreIncomePayUpdateType.RemoveIncomePayType, new IIncomePayHandler() {
            @Override
            public OperateTips update(long storeId, StoreIncomePayUpdateForm formInfo) {
            	BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.INCOME_PAY_ADMIN);
                return IncomePayTypeMgr.getInstance().removeIncomePayType(storeId, formInfo.getIncomePayTypeRemoveForm());
            }
        });
        handleMapper.put(StoreIncomePayUpdateType.UpdateIncomePayType, new IIncomePayHandler() {
            @Override
            public OperateTips update(long storeId, StoreIncomePayUpdateForm formInfo) {
            	BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.INCOME_PAY_ADMIN);
                return IncomePayTypeMgr.getInstance().updateIncomePayType(storeId, formInfo.getIncomePayTypeUpdateForm());
            }
        });

    }

    public OperateTips update(long storeId, StoreIncomePayUpdateForm formInfo) {
        StoreIncomePayUpdateType updateType = formInfo.getStoreIncomePayUpdateType();
        IIncomePayHandler handle = handleMapper.get(updateType);
        if (handle != null) {
            return handle.update(storeId, formInfo);
        }
        return OperateTips.newInstance(false, updateType.getDescript() + "失败");
    }


}

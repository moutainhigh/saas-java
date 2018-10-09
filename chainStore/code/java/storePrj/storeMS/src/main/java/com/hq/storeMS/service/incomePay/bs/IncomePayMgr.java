package com.hq.storeMS.service.incomePay.bs;

import com.hq.storeMS.common.util.PageUtil;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.detailDataVersion.bs.DetailDataVersionMgr;
import com.hq.storeMS.service.detailDataVersion.data.DataVersionEnum;
import com.hq.storeMS.service.incomePay.apiData.IncomePayQueryForm;
import com.hq.storeMS.service.incomePay.data.IncomePay;
import com.zenmind.common.hotSwap.HotSwap;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IncomePayMgr {

    public static IncomePayMgr getInstance() {
        return HotSwap.getInstance().getSingleton(IncomePayMgr.class);
    }

    private final DataVersionEnum dataVersionEnum = DataVersionEnum.IncomePay;

    public void addAndReturnId(IncomePay target) {
        IncomePayDataHolder.getInstance().addAndReturnId(target);
        DetailDataVersionMgr.getInstance().updateByStoreId(target.getStoreId(), dataVersionEnum);
    }

    public void updateIncomePay(IncomePay target) {
        IncomePayDataHolder.getInstance().update(target);
        DetailDataVersionMgr.getInstance().updateByStoreId(target.getStoreId(), dataVersionEnum);
    }

    public void delete(IncomePay target) {
        target.setEntityState(EntityState.Deleted.ordinal());
        updateIncomePay(target);
    }

    public IncomePay get(long storeId, long id) {
        return IncomePayDataHolder.getInstance().get(storeId, id);
    }

    public PageResp<IncomePay> findIncomePayPageInfo(IncomePayQueryForm queryForm) {
        List<IncomePay> list = IncomePayDataHolder.getInstance().findByCond(queryForm);
        List<IncomePay> result = filterRecord(queryForm, list);
        return PageUtil.getInstance().buildPageResp(result, queryForm.getPageNo(), queryForm.getPageItemCount());
    }

    /**
     * 匹配正确的列表项
     *
     * @param queryForm
     * @param list
     * @return
     */
    private List<IncomePay> filterRecord(IncomePayQueryForm queryForm, List<IncomePay> list) {
        List<IncomePay> result = new ArrayList<IncomePay>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (IncomePay record : list) {
                if (record == null) continue;
                if (isRightRecord(queryForm, record)) {
                    result.add(record);
                }
            }
        }
        Collections.sort(result, new Comparator<IncomePay>() {
            @Override
            public int compare(IncomePay o1, IncomePay o2) {
                return Long.compare(o2.getIncomePayTime(), o1.getIncomePayTime());
            }
        });
        return result;
    }

    // 谨记：内存筛选 一定要记得 不应该再操作数据库
    private boolean isRightRecord(IncomePayQueryForm queryForm, IncomePay record) {
        boolean isRight = checkCategory(queryForm.getCategory(), record)
                && checkMinMoney(queryForm.getMinMoney(), record)
                && checkMaxMoney(queryForm.getMaxMoney(), record)
                && checkTypeId(queryForm.getTypeId(), record)
                && checkBUserId(queryForm.getBuserId(), record);
        return isRight;
    }

    private boolean checkCategory(int category, IncomePay record) {
        if (category == IncomePayQueryForm.CATEGORY_INVALID) return true;
        return category == record.getCategory();
    }

    private boolean checkMinMoney(long minMoney, IncomePay record) {
    	if(minMoney == 0) {
    		return true;
    	}
        return record.getMoney() >= minMoney;
    }
    
    private boolean checkMaxMoney(long maxMoney, IncomePay record) {
    	if(maxMoney == 0) {
    		return true;
    	}
    	return record.getMoney() <= maxMoney;
    }

    private boolean checkTypeId(String typeId, IncomePay record) {
        if (StringUtils.isEmpty(typeId)) return true;
        return typeId.equals(record.getTypeId());
    }

    private boolean checkBUserId(long buserId, IncomePay record) {
        if (buserId == 0) return true;
        return buserId == record.getBuserId();
    }

}

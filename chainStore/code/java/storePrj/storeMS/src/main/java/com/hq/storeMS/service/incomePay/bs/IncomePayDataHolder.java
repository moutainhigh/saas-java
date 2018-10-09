package com.hq.storeMS.service.incomePay.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.service.incomePay.apiData.IncomePayQueryForm;
import com.hq.storeMS.service.incomePay.data.IncomePay;
import com.hq.storeMS.service.incomePay.data.IncomePayCacheDAO;
import com.hq.storeMS.service.incomePay.data.IncomePayDAO;
import com.hq.storeMS.service.store.bs.BossDataHolder;
import com.zenmind.common.hotSwap.HotSwap;

public class IncomePayDataHolder {

    public static IncomePayDataHolder getInstance() {
        return HotSwap.getInstance().getSingleton(IncomePayDataHolder.class);
    }

    /**
     * 业务层一定要区分是add还是update,用此方法的时候id必须是long型的自增字段
     *
     * @param target
     * @return
     */
    public void addAndReturnId(IncomePay target) {
        IncomePayDAO.getInstance().addAndReturnId(getBossId(target.getStoreId()), target);
        IncomePayCacheDAO.getInstance().delete(target);
    }

    /**
     * 业务层一定要区分是add还是update
     *
     * @param target
     * @return
     */
    public void update(IncomePay target) {
        target.incrVer();
        target.setLastUpdateTime(System.currentTimeMillis());
        IncomePayDAO.getInstance().updpate(getBossId(target.getStoreId()), target);
        IncomePayCacheDAO.getInstance().delete(target);
    }

    public void delete(IncomePay target) {
        IncomePayDAO.getInstance().delete(getBossId(target.getStoreId()), target.getId());
        IncomePayCacheDAO.getInstance().delete(target);
    }

    public IncomePay get(long storeId, long id) {
        IncomePay target = IncomePayCacheDAO.getInstance().get(storeId, id);
        if (target == null) {
            target = IncomePayDAO.getInstance().get(getBossId(storeId), id);
            if (target != null) {
                IncomePayCacheDAO.getInstance().save(target);
            }
        }
        return target;
    }

    /**
     * 查询收支列表
     *
     * @param queryForm
     * @return
     */
    public List<IncomePay> findByCond(IncomePayQueryForm queryForm) {
        List<IncomePay> list = IncomePayCacheDAO.getInstance().getList(queryForm);
        if (CollectionUtils.isEmpty(list)) {
            list = IncomePayDAO.getInstance().findByCond(getBossId(queryForm.getStoreId()), queryForm);
            if (CollectionUtils.isNotEmpty(list)) {
                IncomePayCacheDAO.getInstance().saveList(queryForm, list);
            }
        }
        return list;
    }
    
    private long getBossId(long storeId) {
    	return BossDataHolder.getInstance().getBossId(storeId);
    }

}

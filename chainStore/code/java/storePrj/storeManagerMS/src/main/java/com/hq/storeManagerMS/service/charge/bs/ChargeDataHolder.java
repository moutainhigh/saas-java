package com.hq.storeManagerMS.service.charge.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeManagerMS.service.charge.apiData.ChargeQueryForm;
import com.hq.storeManagerMS.service.charge.data.Charge;
import com.hq.storeManagerMS.service.charge.data.ChargeCacheDAO;
import com.hq.storeManagerMS.service.charge.data.ChargeDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class ChargeDataHolder {

    public static ChargeDataHolder getInstance() {
        return HotSwap.getInstance().getSingleton(ChargeDataHolder.class);
    }

    /**
     * 业务层一定要区分是add还是update,用此方法的时候id必须是long型的自增字段
     *
     * @param target
     * @return
     */
    public void addAndReturnId(Charge target) {
        ChargeDAO.getInstance().addAndReturnId(target);
        ChargeCacheDAO.getInstance().deleteCharge(target);
    }

    /**
     * 业务层一定要区分是add还是update
     *
     * @param target
     * @return
     */
    public void update(Charge target) {
        target.incrVer();
        target.setLastUpdateTime(System.currentTimeMillis());
        ChargeDAO.getInstance().updpate(target);
        ChargeCacheDAO.getInstance().deleteCharge(target);
    }

    public Charge get(long id) {
        Charge target = ChargeCacheDAO.getInstance().get(id);
        if (target == null) {
            target = ChargeDAO.getInstance().get(id);
            if (target != null) {
                ChargeCacheDAO.getInstance().save(target);
            }
        }
        return target;
    }

    /**
     * 查询收费列表
     *
     * @param queryForm
     * @return
     */
    public List<Charge> findByCond(ChargeQueryForm queryForm) {
        List<Charge> list = ChargeCacheDAO.getInstance().getList(queryForm);
        if (CollectionUtils.isEmpty(list)) {
            list = ChargeDAO.getInstance().findByCond(queryForm);
            if (CollectionUtils.isNotEmpty(list)) {
                ChargeCacheDAO.getInstance().saveList(queryForm, list);
            }
        }
        return list;
    }

}

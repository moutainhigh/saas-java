package com.hq.storeMS.service.spreadStatis.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.service.spreadStatis.apiData.SpreadStatisQueryForm;
import com.hq.storeMS.service.spreadStatis.data.SpreadStatis;
import com.hq.storeMS.service.spreadStatis.data.SpreadStatisCacheDAO;
import com.hq.storeMS.service.spreadStatis.data.SpreadStatisDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class SpreadStatisDataHolder {

    public static SpreadStatisDataHolder getInstance() {
        return HotSwap.getInstance().getSingleton(SpreadStatisDataHolder.class);
    }

    public void addAndReturnId(SpreadStatis target) {
        SpreadStatisDAO.getInstance().addAndReturnId(target);
        SpreadStatisCacheDAO.getInstance().delete(target);
    }

    public void update(SpreadStatis target) {
        target.incrVer();
        target.setLastUpdateTime(System.currentTimeMillis());
        SpreadStatisDAO.getInstance().updpate(target);
        SpreadStatisCacheDAO.getInstance().delete(target);
    }

    public void delete(SpreadStatis target) {
        SpreadStatisDAO.getInstance().delete(target.getId());
        SpreadStatisCacheDAO.getInstance().delete(target);
    }

    public SpreadStatis get(long storeId, long id) {
        SpreadStatis target = SpreadStatisCacheDAO.getInstance().get(storeId, id);
        if (target == null) {
            target = SpreadStatisDAO.getInstance().get(id);
            if (target != null) {
                SpreadStatisCacheDAO.getInstance().save(target);
            }
        }
        return target;
    }

    public List<SpreadStatis> findByCond(SpreadStatisQueryForm queryForm) {
        List<SpreadStatis> list = SpreadStatisCacheDAO.getInstance().getList(queryForm);
        if (CollectionUtils.isEmpty(list)) {
            list = SpreadStatisDAO.getInstance().findByCond(queryForm);
            if (CollectionUtils.isNotEmpty(list)) {
                SpreadStatisCacheDAO.getInstance().saveList(queryForm, list);
            }
        }
        return list;
    }
    
}

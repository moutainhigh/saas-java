package com.hq.storeMS.service.storeConfig.bs;

import org.apache.commons.lang3.math.NumberUtils;

import com.hq.storeMS.common.datasyn.IntfDataHolder;
import com.hq.storeMS.common.datasyn.info.DataSynItem;
import com.hq.storeMS.common.datasyn.info.DataSynType;
import com.hq.storeMS.common.datasyn.info.DataSynVer;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.storeConfig.data.StoreConfig;
import com.hq.storeMS.service.storeConfig.data.StoreConfigCacheDAO;
import com.hq.storeMS.service.storeConfig.data.StoreConfigDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class StoreConfigDataHolder implements IntfDataHolder {

    public static StoreConfigDataHolder getInstance() {
        return HotSwap.getInstance().getSingleton(StoreConfigDataHolder.class);
    }

    final private DataSynType synType = DataSynType.StoreConfig;

    public void addWithId(StoreConfig target) {
        StoreConfigDAO.getInstance().addWithId(target);
    }

    public void delete(StoreConfig target) {
        StoreConfigDAO.getInstance().delete(target.getId());
        StoreConfigCacheDAO.getInstance().delete(target);
    }

    public void update(StoreConfig target) {
        target.incrVer();
        target.setLastUpdateTime(System.currentTimeMillis());
        StoreConfigDAO.getInstance().updpate(target);
        StoreConfigCacheDAO.getInstance().delete(target);
    }

    public StoreConfig get(long id) {
        StoreConfig target = StoreConfigCacheDAO.getInstance().get(id);
        if (target == null) {
            target = StoreConfigDAO.getInstance().get(id);
            if (target != null) {
                StoreConfigCacheDAO.getInstance().save(target);
            }
        }
        return target;
    }

    public DataSynType getSynType() {
        return synType;
    }

    public DataSynItem getSynItem(DataSynVer clientSynVer) {
        DataSynItem item = null;
        String id = clientSynVer.getId();
        if (NumberUtils.isNumber(id)) {
            StoreConfig target = this.get(Long.valueOf(id));
            if (target != null) {
                long newVer = target.getVer();
                if (clientSynVer.getVer() < newVer) {
                    String data = DataSynMgr.getInstance().toClientData(target);
                    item = DataSynItem.newInstance(clientSynVer, newVer, data);
                }
            } else {
                MainLog.info(LogModule.StoreConfig, "StoreConfigDataHolder[getSynItem]", "获取详情对象为空[id=" + id + "]");
            }
        } else {
            MainLog.info(LogModule.StoreConfig, "StoreConfigDataHolder[getSynItem]", "数据同步失败[id=" + id + "]");
        }
        return item;
    }

}

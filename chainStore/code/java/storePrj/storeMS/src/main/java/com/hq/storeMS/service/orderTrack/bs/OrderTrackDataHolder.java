package com.hq.storeMS.service.orderTrack.bs;

import java.util.List;

import com.hq.storeMS.service.orderTrack.apiData.OrderTrackQueryForm;
import com.hq.storeMS.service.orderTrack.data.OrderTrack;
import com.hq.storeMS.service.orderTrack.data.OrderTrackCacheDAO;
import com.hq.storeMS.service.orderTrack.data.OrderTrackDAO;
import com.hq.storeMS.service.store.bs.BossDataHolder;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderTrackDataHolder {

    public static OrderTrackDataHolder getInstance() {
        return HotSwap.getInstance().getSingleton(OrderTrackDataHolder.class);
    }

    public void addWithId(OrderTrack target) {
        OrderTrackDAO.getInstance().addWithId(getBossId(target.getStoreId()), target);
        OrderTrackCacheDAO.getInstance().delete(target);
    }

    public void updpate(OrderTrack target) {
        target.incrVer();
        target.setLastUpdateTime(System.currentTimeMillis());
        OrderTrackDAO.getInstance().updpate(getBossId(target.getStoreId()), target);
        OrderTrackCacheDAO.getInstance().delete(target);
    }

    public void delete(OrderTrack target) {
        OrderTrackDAO.getInstance().delete(getBossId(target.getStoreId()), target.getId());
        OrderTrackCacheDAO.getInstance().delete(target);
    }

    public OrderTrack get(long storeId, long id) {
        OrderTrack target = OrderTrackCacheDAO.getInstance().get(storeId, id);
        if (target == null) {
            target = OrderTrackDAO.getInstance().get(getBossId(storeId), id);
            if (target != null) {
                OrderTrackCacheDAO.getInstance().save(target);
            }
        }
        return target;
    }

    public List<OrderTrack> findOrderTrackList(OrderTrackQueryForm queryForm) {
        List<OrderTrack> target = OrderTrackCacheDAO.getInstance().getList(queryForm);
        if (null == target) {
            target = OrderTrackDAO.getInstance().findOrderTrackList(getBossId(queryForm.getStoreId()), queryForm);
            if (null != target) {
                OrderTrackCacheDAO.getInstance().saveList(queryForm, target);
            }
        }
        return target;
    }

    private long getBossId(long storeId) {
		return BossDataHolder.getInstance().getBossId(storeId);
	}
}

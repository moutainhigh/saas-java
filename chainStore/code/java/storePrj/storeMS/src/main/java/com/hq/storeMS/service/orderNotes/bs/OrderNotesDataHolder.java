package com.hq.storeMS.service.orderNotes.bs;

import java.util.List;

import com.hq.storeMS.service.dataReport.apiData.DataReportQueryForm;
import com.hq.storeMS.service.orderNotes.data.OrderNotes;
import com.hq.storeMS.service.orderNotes.data.OrderNotesCacheDAO;
import com.hq.storeMS.service.orderNotes.data.OrderNotesDAO;
import com.hq.storeMS.service.store.bs.BossDataHolder;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderNotesDataHolder {

    public static OrderNotesDataHolder getInstance() {
        return HotSwap.getInstance().getSingleton(OrderNotesDataHolder.class);
    }

    public void addWithId(OrderNotes target) {
        OrderNotesDAO.getInstance().addWithId(getBossId(target.getStoreId()), target);
        OrderNotesCacheDAO.getInstance().delete(target);
    }

    public void updpate(OrderNotes target) {
        target.incrVer();
        target.setLastUpdateTime(System.currentTimeMillis());
        OrderNotesDAO.getInstance().updpate(getBossId(target.getStoreId()), target);
        OrderNotesCacheDAO.getInstance().delete(target);
    }

    public void delete(OrderNotes target) {
        OrderNotesDAO.getInstance().delete(getBossId(target.getStoreId()), target.getId());
        OrderNotesCacheDAO.getInstance().delete(target);
    }

    public OrderNotes get(long storeId, long id) {
        OrderNotes target = OrderNotesCacheDAO.getInstance().get(storeId, id);
        if (target == null) {
            target = OrderNotesDAO.getInstance().get(getBossId(storeId), id);
            if (target != null) {
                OrderNotesCacheDAO.getInstance().save(target);
            }
        }
        return target;
    }

    public List<OrderNotes> findOrderNotesList(DataReportQueryForm queryForm) {
        List<OrderNotes> target = OrderNotesCacheDAO.getInstance().getList(queryForm);
        if (null == target) {
            target = OrderNotesDAO.getInstance().findOrderNotesList(getBossId(queryForm.getStoreId()), queryForm);
            if (null != target) {
                OrderNotesCacheDAO.getInstance().saveList(queryForm, target);
            }
        }
        return target;
    }

    public List<OrderNotes> findOrderByIds(long storeId, String ids) {
        List<OrderNotes> target = OrderNotesCacheDAO.getInstance().getListByIds(storeId, ids);
        if (null == target) {
            target = OrderNotesDAO.getInstance().findOrderByIds(getBossId(storeId), storeId,ids);
            if (null != target) {
                OrderNotesCacheDAO.getInstance().saveIdsList(storeId, ids,target);
            }
        }
        return target;
    }

    private long getBossId(long storeId) {
    	return BossDataHolder.getInstance().getBossId(storeId);
	}
}

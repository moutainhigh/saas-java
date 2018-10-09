package com.hq.orderMS.service.orderTmpRcd.bs;

import java.util.List;

import com.hq.orderMS.service.orderTmpRcd.apiData.OrderTmpRcdQueryForm;
import com.hq.orderMS.service.orderTmpRcd.data.OrderTmpRcd;
import com.hq.orderMS.service.orderTmpRcd.data.OrderTmpRcdDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderTmpRcdDataHolder {

    public static OrderTmpRcdDataHolder getInstance() {
        return HotSwap.getInstance().getSingleton(OrderTmpRcdDataHolder.class);
    }

    public void addWithId(OrderTmpRcd target) {
        OrderTmpRcdDAO.getInstance().addWithId(target);
    }

    public void delete(String id) {
        OrderTmpRcdDAO.getInstance().delete(id);
    }

    public List<OrderTmpRcd> findList(OrderTmpRcdQueryForm queryForm) {
        return OrderTmpRcdDAO.getInstance().findList(queryForm);
    }
}

package com.hq.storeMS.service.dataReport.data;

import java.util.ArrayList;
import java.util.List;

import com.hq.orderRestClient.service.order.data.BuyItem;
import com.hq.orderRestClient.service.order.data.BuyTypeEnum;
import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderTypeEnum;
import com.hq.orderRestClient.service.order.data.RechargeItem;
import com.zenmind.common.hotSwap.HotSwap;

public class DataReportBeanHelper {
	public static DataReportBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(DataReportBeanHelper.class);
	}

	public List<DataReport> orderList2ReportList(List<Order> orderList) {
		List<DataReport> dataReports = new ArrayList<DataReport>();
		for (Order order : orderList) {
			int orderType = order.getOrderType();
			if (orderType == OrderTypeEnum.PURCHASE.ordinal()) {
				List<BuyItem> buyItems = order.getBuyItems();
				for (BuyItem buyItem : buyItems) {
					DataReport dataReport = DataReport.newInstance();
					dataReport.setCreatedTime(order.getCreatedTime());
					dataReport.setCreatorId(order.getCreatorId());
					dataReport.setCuserId(order.getCuserId());
					dataReport.setLeaguerId(order.getLeaguerId());
					dataReport.setOrderId(order.getId());
					dataReport.setOrderNumber(order.getNumber());
					dataReport.setPayTime(order.getPayTime());
					dataReport.setStoreId(order.getStoreId());
					
					dataReport.setBuyType(buyItem.getBuyType());
					dataReport.setCount(buyItem.getCount());
					dataReport.setPay(buyItem.getPay());
					dataReport.setPgId(buyItem.getPgId());
					
					dataReports.add(dataReport);
				}
			} else if (orderType == OrderTypeEnum.RECHARGE.ordinal()) {
				List<RechargeItem> rechargeItems = order.getRechargeItems();
				for (RechargeItem rechargeItem : rechargeItems) {
					DataReport dataReport = DataReport.newInstance();
					dataReport.setCreatedTime(order.getCreatedTime());
					dataReport.setCreatorId(order.getCreatorId());
					dataReport.setCuserId(order.getCuserId());
					dataReport.setLeaguerId(order.getLeaguerId());
					dataReport.setOrderId(order.getId());
					dataReport.setOrderNumber(order.getNumber());
					dataReport.setPayTime(order.getPayTime());
					dataReport.setStoreId(order.getStoreId());
					
					dataReport.setBuyType(BuyTypeEnum.RECHARGE.ordinal());
					dataReport.setCount(0);
					dataReport.setPay(rechargeItem.getPay());
					dataReport.setPgId("");
					
					dataReports.add(dataReport);
				}
			}
		}
		return dataReports;
	}
}

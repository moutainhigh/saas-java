package com.hq.storeMS.service.dataReport.bs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hq.orderRestClient.service.order.apiData.OrderQueryForm;
import com.hq.orderRestClient.service.order.data.BuyItem;
import com.hq.orderRestClient.service.order.data.Order;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.order.bs.OrderDataHolder;
import com.hq.storeMS.service.storeCardInfo.bs.StoreCardInfoDataHolder;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeMS.service.storeConfig.bs.StoreConfigDataHolder;
import com.hq.storeMS.service.storeConfig.data.StoreConfig;
import com.hq.storeMS.service.storeLeaguerInfo.bs.StoreLeaguerInfoDataHolder;
import com.hq.storeMS.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class HelperClass {

	public static HelperClass getInstance() {
		return HotSwap.getInstance().getSingleton(HelperClass.class);
	}

//	private List<List<BuyItem>> result = new ArrayList<>(new ArrayList<>());

	public List<List<BuyItem>> sortByDuplicate(List<BuyItem> list) {

		List<List<BuyItem>> results = new ArrayList<>();

		while (list.size() > 0) {
			// 每次取出 list 的第一个值
			BuyItem first = list.remove(0);
			// 标志是否已经在 results 中存在
			boolean isExistsOnResults = false;

			// 遍历 results
			for (List<BuyItem> subList : results) {
				// 如果已经存在，直接插入到 sub list 中
				if (subList.size() > 0 && subList.get(0).equals(first)) {
					subList.add(first);
					isExistsOnResults = true;
					break;
				}
			}

			// 如果不存在于 results 中，则创建一个新的。
			if (!isExistsOnResults) {
				List<BuyItem> subResultList = new ArrayList<>();
				subResultList.add(first);
				results.add(subResultList);
			}
		}

		return results;
	}

	public Map<String, List<BuyItem>> getMap(List<BuyItem> buyItems) {

		Map<String, List<BuyItem>> map = new HashMap<>();
		if (buyItems == null)
			return map;
		for (BuyItem buyItem : buyItems) {
			if (buyItem == null)
				continue;
			String pgId = buyItem.getPgId();
			if (pgId == null)
				continue;
			List<BuyItem> items = map.get(pgId);
			if (items == null) {
				items = new ArrayList<>();
				map.put(pgId, items);
			}
			items.add(buyItem);
		}

		return map;
	}

	// 按着会员消费排序
	public List<LeaguerDetail> leaguerDetailsSort(List<LeaguerDetail> leaguerDetailList1) {

		List<LeaguerDetail> leaguerDetailList = leaguerDetailList1;
		for (int i = 0; i < leaguerDetailList.size(); i++) {
			for (int j = 0; j < leaguerDetailList.size() - 1 - i; j++) {
				if (leaguerDetailList.get(j).getConsumeAmount() < leaguerDetailList.get(j + 1).getConsumeAmount()) {
					LeaguerDetail leaguerDetail1 = new LeaguerDetail();
					LeaguerDetail leaguerDetail2 = new LeaguerDetail();
					leaguerDetail1 = leaguerDetailList.get(j);
					leaguerDetail2 = leaguerDetailList.get(j + 1);
					leaguerDetailList.remove(j);
					leaguerDetailList.add(j, leaguerDetail2);
					leaguerDetailList.remove(j + 1);
					leaguerDetailList.add(j + 1, leaguerDetail1);

				}
			}
		}

		return leaguerDetailList;
	}

	// 获取订单列表
	public List<Order> getOrderListData(String startTime, String endTime, String storeId) {
		OrderQueryForm queryForm = new OrderQueryForm();
		queryForm.setStoreId(Long.valueOf(storeId));
		queryForm.addStatus(1);
		queryForm.setMinTime(Long.valueOf(startTime));
		queryForm.setMaxTime(Long.valueOf(endTime));
		return OrderDataHolder.getInstance().findOrderList(queryForm);

	}

	public StoreLeaguerInfo getStoreLeaguerInfo(String storeId) {
		return StoreLeaguerInfoDataHolder.getInstance().get(Long.valueOf(storeId));
	}

	public StoreConfig getStoreConfig(String storeId) {
		return StoreConfigDataHolder.getInstance().get(Long.valueOf(storeId));
	}

	public StoreCardInfo getStoreCardInfo(String storeId) {
		return StoreCardInfoDataHolder.getInstance().get(Long.valueOf(storeId));
	}

}

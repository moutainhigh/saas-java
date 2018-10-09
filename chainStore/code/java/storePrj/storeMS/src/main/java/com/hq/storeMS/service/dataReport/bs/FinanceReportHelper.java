package com.hq.storeMS.service.dataReport.bs;

import com.hq.orderRestClient.service.order.data.*;
import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.dataReport.apiData.DataReportQueryForm;
import com.hq.storeMS.service.dataReport.data.DayData;
import com.hq.storeMS.service.dataReport.data.FinanceReport;
import com.hq.storeMS.service.dataReport.data.PayItemDetail;

import java.util.*;

public class FinanceReportHelper {

	/**
	 * 财务统计
	 *
	 * @param queryForm
	 * @return
	 */
	public static FinanceReport buildFinanceReport(DataReportQueryForm queryForm) {
		List<Order> orders = DataReportMgr.getInstance().findFianceOrderList(queryForm);
		FinanceReport financeReport = FinanceReport.newInstance(queryForm.getStoreId(),queryForm.getMinTime(), queryForm.getMaxTime());
		Map<String, DayData> dayDataMap = getDayDataMapByTimeRange(queryForm);
		Map<Integer, PayItemDetail> payItemDetailMap = FinanceReportHelper.getPayItemDetailMap();
		for (Order order : orders) {
			financeReport.addOperatingIncome(order.getRealPay());// 营业收入 统计实收
			financeReport.addChargeBackAmount(order.getChargeBackCost());// 退单金额
			addDayIncome(dayDataMap, order);
			addBuyItemsAmount(financeReport, order);
			addDelimitCardAmount(financeReport, order);
			dealRecharge(financeReport, order);
			dealPayItems(payItemDetailMap, financeReport, order);
		}
		List<PayItemDetail> payItemDetails = getPayItemDetails(payItemDetailMap);
		List<DayData> dayDataList = getDayDataList(dayDataMap);
		financeReport.setPayItemDetails(payItemDetails);
		financeReport.setDayDataList(dayDataList);
		return financeReport;
	}

	/**
	 * 初始化指定时间段内每日数据统计模型
	 * 
	 * @param queryForm
	 * @return
	 */

	public static HashMap<String, DayData> getDayDataMapByTimeRange(DataReportQueryForm queryForm) {
		HashMap<String, DayData> dayDataMap = new HashMap<>();
		long minTime = queryForm.getMinTime();
		long maxTime = queryForm.getMaxTime();
		String minTimeStr = AppUtils.timeStamp2Str(minTime);
		String maxTimeStr = AppUtils.timeStamp2Str(maxTime);
		DayData dayData = DayData.newInstance(minTime);
		dayDataMap.put(minTimeStr, dayData);
		if (maxTime <= minTime)
			return dayDataMap;
		if (!minTimeStr.equals(maxTimeStr)) {// 选的不是一天
			long dayLong = 24 * 60 * 60 * 1000;
			long tempDayLong = minTime;
			boolean isEnd = false;
			while (!isEnd) {
				tempDayLong = tempDayLong + dayLong;
				DayData tempDayData = DayData.newInstance(tempDayLong);
				dayDataMap.put(tempDayData.getTimeStr(), tempDayData);
				if (tempDayData.getTimeStr().equals(maxTimeStr)) {
					isEnd = true;
				}
			}
		}
		return dayDataMap;
	}

	public static Map<Integer, PayItemDetail> getPayItemDetailMap() {
		Map<Integer, PayItemDetail> payItemDetailMap = new HashMap<>();
		PayTypeEnum[] payTypeEnums = PayTypeEnum.values();
		for (PayTypeEnum payTypeEnum : payTypeEnums) {
			PayItemDetail payItemDetail = PayItemDetail.newInstance(payTypeEnum.ordinal(), payTypeEnum.getMark());
			payItemDetailMap.put(payTypeEnum.ordinal(), payItemDetail);
		}
		return payItemDetailMap;
	}

	public static void addBuyItemsAmount(FinanceReport financeReport, Order order) {
		List<BuyItem> buyItems = order.getBuyItems();
		if (buyItems != null && buyItems.size() > 0) {// 购买消费
			for (BuyItem buyItem : buyItems) {
				BuyTypeEnum buyTypeEnum = BuyTypeEnum.valueOf(buyItem.getBuyType());
				switch (buyTypeEnum) {
				case PRDCARD:
					financeReport.addSaleProductCardCount(buyItem.getCount());// 增加次卡销售次数
					financeReport.addCardSalesAmount(buyItem.getPay());
					break;
				default:
					break;
				}
			}
		}
	}

	/**
	 * 增加每天的收入
	 *
	 * @param dayDataMap
	 * @param order
	 */
	public static void addDayIncome(Map<String, DayData> dayDataMap, Order order) {
		String dayStr = AppUtils.timeStamp2Str(order.getCreatedTime());
		DayData dayData = dayDataMap.get(dayStr);
		if (dayData == null) {
			dayData = DayData.newInstance(order.getCreatedTime());
			dayDataMap.put(dayStr, dayData);
		}
		dayData.addAmount(order.getRealPay());// 统计对应天数的收入
	}

	/**
	 * 划卡支付次数
	 *
	 * @param financeReport
	 * @param order
	 */
	public static void addDelimitCardAmount(FinanceReport financeReport, Order order) {
		List<DelimitCardItem> delimitCardItems = order.getDelimitCardItems();// 划卡支付次数
		if (null != delimitCardItems && delimitCardItems.size() > 0) {
			for (DelimitCardItem delimitCardItem : delimitCardItems) {
				financeReport.adddelimitCardCount(delimitCardItem.getCount());
			}
		}
	}

	/**
	 * 会员卡充值统计
	 *
	 * @param financeReport
	 * @param order
	 */
	public static void dealRecharge(FinanceReport financeReport, Order order) {
		List<RechargeItem> rechargeItems = order.getRechargeItems();
		if (null != rechargeItems && rechargeItems.size() > 0) {
			for (RechargeItem rechargeItem : rechargeItems) {
				financeReport.addRechargeAmount(rechargeItem.getPay());
				financeReport.addDonationAmount(rechargeItem.getLargess());
				financeReport.addCardSalesAmount(rechargeItem.getPay());
			}
		}
	}

	/**
	 * 支付方式统计
	 *
	 * @param payItemDetailMap
	 * @param financeReport
	 * @param order
	 */
	public static void dealPayItems(Map<Integer, PayItemDetail> payItemDetailMap, FinanceReport financeReport,
			Order order) {
		List<PayItem> payItems = order.getPayItems();
		if (null != payItems && payItems.size() > 0) {
			for (PayItem payItem : payItems) {
				PayItemDetail payItemDetail = payItemDetailMap.get(payItem.getPayType());
				if (null == payItemDetail) {// 此处初始化过，所以下面是不会运行的
					payItemDetail = PayItemDetail.newInstance(payItem.getPayType(),
							PayTypeEnum.valueOf(payItem.getPayType()).getMark());
					payItemDetailMap.put(payItem.getPayType(), payItemDetail);
				}
				if (payItem.getPayType() == PayTypeEnum.MEMBERSHIPCARD.ordinal()) {
					financeReport.addmemberCardPayAmount(payItem.getCost());
				}
				payItemDetail.addCost(payItem.getCost());
			}
		}
	}

	/**
	 * 获取每天营收统计
	 *
	 * @param dayDataMap
	 * @return
	 */
	private static List<DayData> getDayDataList(Map<String, DayData> dayDataMap) {
		List<DayData> dayDataList = new ArrayList<>(dayDataMap.values());
		sortDayDataList(dayDataList);
		return dayDataList;
	}

	/**
	 * 根据每天营收金额排序
	 *
	 * @param dayDataList
	 */
	public static void sortDayDataList(List<DayData> dayDataList) {
		dayDataList.sort(new Comparator<DayData>() {
			@Override
			public int compare(DayData o1, DayData o2) {
				if (o1.getTimeMillis() > o2.getTimeMillis()) {
					return 1;
				} else if (o1.getTimeMillis() == o2.getTimeMillis()) {
					return 0;
				} else {
					return -1;
				}
				// return (int) (o1.getTimeMillis() - o2.getTimeMillis());
			}
		});
	}

	/**
	 * 获取支付统计列表
	 *
	 * @param payItemDetailMap
	 * @return
	 */
	private static List<PayItemDetail> getPayItemDetails(Map<Integer, PayItemDetail> payItemDetailMap) {
		List<PayItemDetail> payItemDetails = new ArrayList<>(payItemDetailMap.values());
		sortPayItemDetails(payItemDetails);
		return payItemDetails;
	}

	/**
	 * 根据金额大小对支付方式排序
	 *
	 * @param payItemDetails
	 */
	private static void sortPayItemDetails(List<PayItemDetail> payItemDetails) {
		payItemDetails.sort(new Comparator<PayItemDetail>() {
			@Override
			public int compare(PayItemDetail o1, PayItemDetail o2) {
				return (int) -(o1.getCost() * 10000 - o2.getCost() * 10000);
			}
		});
	}
}

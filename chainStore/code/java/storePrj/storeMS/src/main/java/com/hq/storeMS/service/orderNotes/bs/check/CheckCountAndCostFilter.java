package com.hq.storeMS.service.orderNotes.bs.check;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.hq.orderRestClient.common.constants.OrderClientConstants;
import com.hq.orderRestClient.service.order.data.BuyItem;
import com.hq.orderRestClient.service.order.data.DelimitCardItem;
import com.hq.orderRestClient.service.order.data.DonationItem;
import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderTypeEnum;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.orderNotes.apiData.ChargeBackRecordAddForm;
import com.hq.storeMS.service.orderNotes.data.ChargeBackItem;
import com.hq.storeMS.service.orderNotes.data.ItemCountAndCost;
import com.hq.storeMS.service.orderNotes.data.OrderNotes;
import com.hq.storeMS.service.workFlowData.data.PrdCardPayEnum;
import com.zenmind.common.BigDecimalUtil;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * 实现逻辑：
 * 1. 订单的可退金额 与 退款总额 比较
 * 2. 退单项与订单项剩余次数和金额 比较
 * @author kevin
 *
 */
public class CheckCountAndCostFilter {

	public static CheckCountAndCostFilter getInstance() {
		return HotSwap.getInstance().getSingleton(CheckCountAndCostFilter.class);
	}

	public OperateTips check(Order order, OrderNotes orderNotes, ChargeBackRecordAddForm inputForm) {
		OperateTips operateTips = OperateTips.newInstance(true);
		if (order.getOrderType() == OrderTypeEnum.PURCHASE.ordinal()) {// 购买消费
			// 可退金额
			float freeCost = BigDecimalUtil.round(order.getRealPay() - order.getChargeBackCost(), 2);
			// 已退项 个数与金额
			Map<String, ItemCountAndCost> itemCountAndCostMap = orderNotes.takeItemCountAndCostMap();

			// 待退项列表
			List<ChargeBackItem> chargeBackItems = inputForm.getChargeBackItems();
			if(!checkTotalCost(freeCost, chargeBackItems)) {
				operateTips.setTips("可退金额不足");
				operateTips.setSuccess(false);
				return operateTips;
			}
			
			for (ChargeBackItem chargeBackItem : chargeBackItems) {
				if (!checkCostAndCount(chargeBackItem, order, itemCountAndCostMap)) {
					operateTips.setTips("可退金额或可退次数不够");
					operateTips.setSuccess(false);
					break;
				}
			}
		}
		return operateTips;
	}

	// 检查总额
	private boolean checkTotalCost(float freeCost, List<ChargeBackItem> chargeBackItems) {
		float cost = 0.0f;
		for (ChargeBackItem chargeBackItem : chargeBackItems) {
			cost += chargeBackItem.getCost();
		}
		cost = BigDecimalUtil.round(cost, 2);
		return freeCost >= cost;
	}

	// 检查退款项
	private boolean checkCostAndCount(ChargeBackItem chargeBackItem, Order order, Map<String, ItemCountAndCost> itemCountAndCostMap) {
		String itemId = chargeBackItem.getItemId();
		float cost = BigDecimalUtil.round(chargeBackItem.getCost(), 2);
		int count = chargeBackItem.getCount();

		if (StringUtils.isBlank(itemId)) {// 只退款
			return true;
		}

		// 已退项
		ItemCountAndCost userCountAndCost = itemCountAndCostMap.get(itemId);
		// 订单里的购买项
		ItemCountAndCost itemCountAndCost = getItemCountAndCost(itemId, order);

		// 剩余的可退金额与次数
		float itemCost = BigDecimalUtil.round(itemCountAndCost.getCost(), 2);
		int itemCount = itemCountAndCost.getCount();
		if(userCountAndCost != null){
			itemCost = BigDecimalUtil.round(itemCost - userCountAndCost.getCost(), 2);
			itemCount = itemCount - userCountAndCost.getCount();
		}
		return itemCost >= cost && itemCount >= count;
	}

	private ItemCountAndCost getItemCountAndCost(String itemId, Order order) {
		ItemCountAndCost itemCountAndCost = ItemCountAndCost.newInstance(itemId);

		float itemCost = 0f;
		int itemCount = 0;
		PrdCardPayEnum prdCardPayEnum = getPrdCardPayByItemId(itemId);
		switch (prdCardPayEnum) {
		case Donation:
			DonationItem donationItem = order.takeDonationItem(itemId);
			itemCount = donationItem != null ? donationItem.getCount() : 0;
			break;
		case PrdCard:
			DelimitCardItem delimitCardItem = order.takeDelimitCardItem(itemId);
			itemCount = delimitCardItem != null ? delimitCardItem.getCount() : 0;
			break;
		case CashPay:
			BuyItem buyItem = order.takeBuyItem(itemId);
			itemCount = buyItem != null ? buyItem.getCount() : 0;
			itemCost = buyItem != null ? buyItem.getPay() : 0f;
			break;
		default:
			break;
		}

		itemCountAndCost.setCost(itemCost);
		itemCountAndCost.setCount(itemCount);
		return itemCountAndCost;
	}

	private PrdCardPayEnum getPrdCardPayByItemId(String itemId) {
		if (itemId.startsWith(OrderClientConstants.ORDER_DONATION_ITEM_ID_SUFFFIX)) {
			return PrdCardPayEnum.Donation;
		} else if (itemId.startsWith(OrderClientConstants.ORDER_DELIMITCARD_ITEM_ID_SUFFFIX)) {
			return PrdCardPayEnum.PrdCard;
		} else {
			return PrdCardPayEnum.CashPay;
		}
	}
}

package com.hq.storeMS.service.daySnapshot.data;

import java.util.List;

import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderStatusEnum;
import com.hq.orderRestClient.service.order.data.PayItem;
import com.hq.orderRestClient.service.order.data.PayTypeEnum;
import com.hq.storeMS.service.arrearage.data.PaymentHistory;
import com.hq.storeMS.service.incomePay.data.IncomePay;
import com.hq.storeMS.service.incomePay.data.IncomePayCategoryEnum;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetailBeanHelper;
import com.hq.storeMS.service.orderNotes.data.ChargeBackRecord;
import com.zenmind.common.BigDecimalUtil;

public class PreDaySnapshotDataBuilder {
	public static PreDaySnapshotDataBuilder newInstance() {
		PreDaySnapshotDataBuilder data = new PreDaySnapshotDataBuilder();
		return data;
	}

	private List<Order> orders;
	private List<IncomePay> incomePays;
	private List<PaymentHistory> paymentHistorys;
	private List<ChargeBackRecord> chargeBackRecords;

	public PreDaySnapshotData build() {
		PreDaySnapshotData result = PreDaySnapshotData.newInstance();

		circulateOrder(result);
		circulateIncomePay(result);
		circulatePaymentHistorys(result);
		circulateChargeBackRecords(result);

		return result;
	}

	private void circulateOrder(PreDaySnapshotData result) {
		int orderNum = result.getOrderNum();// 支付的订单数量
		int notPayNum = result.getNotPayNum();// 未支付的订单数量
		float amount = result.getAmount();// 订单实收总金额
		float memberCardCost = result.getMemberCardCost();// 会员卡抵扣金额
		float cashCost = result.getCashCost();// 订单现金收入总额
		
		for (Order data : orders) {
			List<PayItem> payItems = data.getPayItems();
			OrderStatusEnum orderStatusEnum = OrderStatusEnum.valueOf(data.getStatus());
			switch (orderStatusEnum) {
			case CANCEL:
				break;
			case NOT_PAY:
				notPayNum++;
				break;
			default:
				orderNum++;
				amount = amount + data.getRealPay();
				memberCardCost = memberCardCost + LeaguerDetailBeanHelper.getInstance().getMemshipCardCost(payItems);
				cashCost = cashCost + getCashCost(payItems);
				break;
			}
		}
		
		result.setOrderNum(orderNum);
		result.setNotPayNum(notPayNum);
		result.setMemberCardCost(memberCardCost);
		result.setAmount(amount);
		result.setCashCost(cashCost);
	}
	
	private void circulateIncomePay(PreDaySnapshotData result) {
		float incomeCost = result.getIncomeCost();//收入
		float payCost = result.getPayCost();//支出

		for (IncomePay data : incomePays) {
			IncomePayCategoryEnum categoryEnum = IncomePayCategoryEnum.valueOf(data.getCategory());
			switch (categoryEnum) {
			case INCOME:
				incomeCost = incomeCost + data.getMoney();
				break;
			case PAY:
				payCost = payCost + data.getMoney();
				break;
			default:
				break;
			}
		}
		result.setIncomeCost(incomeCost);
		result.setPayCost(payCost);
	}
	
	private void circulatePaymentHistorys(PreDaySnapshotData result) {
		float arrearBackCost = result.getArrearBackCost();//还款收入
		float cashCost = result.getCashCost();// 还款现金收入
		
		for (PaymentHistory data : paymentHistorys) {
			List<PayItem> payItems = data.getPayItems();
			arrearBackCost = arrearBackCost + getTotalCost(payItems);
			cashCost = cashCost + getCashCost(payItems);
		}
		
		result.setArrearBackCost(arrearBackCost);
		result.setCashCost(cashCost);
	}
	
	private void circulateChargeBackRecords(PreDaySnapshotData result) {
		float chargeBackCost = result.getChargeBackCost();// 退单支出金额
		float cashCost = result.getCashCost(); // 退单支出现金
		
		for (ChargeBackRecord data : chargeBackRecords) {
			List<PayItem> payItems = data.getPayItems();
			chargeBackCost = chargeBackCost + getTotalCost(payItems);
			cashCost = cashCost - getCashCost(payItems);
		}
		
		result.setChargeBackCost(chargeBackCost);
		result.setCashCost(cashCost);
	}

	private float getCashCost(List<PayItem> payItems) {
		float cost = 0;
		for (PayItem payItem : payItems) {
			if (payItem.getPayType() == PayTypeEnum.CASH.ordinal()) {
				cost = BigDecimalUtil.round(payItem.getCost(), 2);
				break;
			}
		}
		return cost;
	}
	
	private float getTotalCost(List<PayItem> payItems) {
		float cost = 0;
		for (PayItem payItem : payItems) {
			cost = BigDecimalUtil.round(payItem.getCost(), 2);
		}
		return cost;
	}

	public PreDaySnapshotDataBuilder withOrders(List<Order> orders) {
		this.orders = orders;
		return this;
	}

	public PreDaySnapshotDataBuilder withIncomePays(List<IncomePay> incomePays) {
		this.incomePays = incomePays;
		return this;
	}

	public PreDaySnapshotDataBuilder withPaymentHistorys(List<PaymentHistory> paymentHistorys) {
		this.paymentHistorys = paymentHistorys;
		return this;
	}

	public PreDaySnapshotDataBuilder withChargeBackRecords(List<ChargeBackRecord> chargeBackRecords) {
		this.chargeBackRecords = chargeBackRecords;
		return this;
	}

}

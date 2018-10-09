package com.hq.storeClient.service.orderDetail.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Table;

import com.hq.storeClient.service.order.data.PayItem;
import com.hq.storeClient.service.orderTrack.data.OrderTrack;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "orderDetail")
public class OrderDetail {
	// 订单基本信息
	private SimpleOrderInfo simpleOrderInfo;
	// 客户基本信息
	private SimpleLeaguerInfo simpleLeaguerInfo;

	// 会员充值清单
	private List<RechargeDetail> rechargeDetails = new ArrayList<RechargeDetail>();

	// 划卡项列表详情
	private List<DelimitCardDetail> delimitCardDetails = new ArrayList<DelimitCardDetail>();
	// 购买项列表详情
	private List<BuyDetail> buyDetails = new ArrayList<BuyDetail>();
	// 赠送项列表详情
	private List<DonateDetail> donateDetails = new ArrayList<DonateDetail>();
	// 退单列表明细
	private List<ChargeBackDetail> chargeBackDetails = new ArrayList<ChargeBackDetail>();

	// 订单备注
	private OrderRemark orderRemark;
	// 支付明细
	private List<PayItem> payItems = new ArrayList<PayItem>();
	// 提成列表详情
	private List<OrderBonusDetail> orderBonusDetails = new ArrayList<OrderBonusDetail>();

	// 订单的物流信息
	private OrderTrack orderTrack;

	public static OrderDetail newInstance() {
		OrderDetail data = new OrderDetail();
		return data;
	}

	public SimpleOrderInfo getSimpleOrderInfo() {
		return simpleOrderInfo;
	}

	public void setSimpleOrderInfo(SimpleOrderInfo simpleOrderInfo) {
		this.simpleOrderInfo = simpleOrderInfo;
	}

	public SimpleLeaguerInfo getSimpleLeaguerInfo() {
		return simpleLeaguerInfo;
	}

	public void setSimpleLeaguerInfo(SimpleLeaguerInfo simpleLeaguerInfo) {
		this.simpleLeaguerInfo = simpleLeaguerInfo;
	}

	public List<DelimitCardDetail> getDelimitCardDetails() {
		return delimitCardDetails;
	}

	public void setDelimitCardDetails(List<DelimitCardDetail> delimitCardDetails) {
		this.delimitCardDetails = delimitCardDetails;
	}

	public List<BuyDetail> getBuyDetails() {
		return buyDetails;
	}

	public void setBuyDetails(List<BuyDetail> buyDetails) {
		this.buyDetails = buyDetails;
	}

	public List<DonateDetail> getDonateDetails() {
		return donateDetails;
	}

	public void setDonateDetails(List<DonateDetail> donateDetails) {
		this.donateDetails = donateDetails;
	}

	public OrderRemark getOrderRemark() {
		return orderRemark;
	}

	public void setOrderRemark(OrderRemark orderRemark) {
		this.orderRemark = orderRemark;
	}

	public List<PayItem> getPayItems() {
		return payItems;
	}

	public void setPayItems(List<PayItem> payItems) {
		this.payItems = payItems;
	}

	public List<ChargeBackDetail> getChargeBackDetails() {
		return chargeBackDetails;
	}

	public void setChargeBackDetails(List<ChargeBackDetail> chargeBackDetails) {
		this.chargeBackDetails = chargeBackDetails;
	}

	public List<OrderBonusDetail> getOrderBonusDetails() {
		return orderBonusDetails;
	}

	public void setOrderBonusDetails(List<OrderBonusDetail> orderBonusDetails) {
		this.orderBonusDetails = orderBonusDetails;
	}

	public List<RechargeDetail> getRechargeDetails() {
		return rechargeDetails;
	}

	public void setRechargeDetails(List<RechargeDetail> rechargeDetails) {
		this.rechargeDetails = rechargeDetails;
	}

	public OrderTrack getOrderTrack() {
		return orderTrack;
	}

	public void setOrderTrack(OrderTrack orderTrack) {
		this.orderTrack = orderTrack;
	}

}

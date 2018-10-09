package com.hq.storeMS.service.dataReport.data.vo;

import com.zenmind.dataSyn.annotation.SynClass;

import java.util.ArrayList;
import java.util.List;

/**
 * 销售统计实体
 */
@SynClass
public class SellStatisData {
	private long storeId;

	// 销售总额
	private float totalCost;
	// 实收总额
	private float totalIncome;
	// 退单总额
	private float totalChargeBackCost;

	// 项目销售额
	private float prdCost;
	// 商品销售额
	private float goodsCost;
	// 套餐销售额
	private float pkgCost;
	// 次卡销售额
	private float cardCost;
	// 会员充值额
	private float rechargeCost;

	// 按天统计 销售额[每天的销售额 折线图]
	private List<SellDate> sellDates = new ArrayList<SellDate>();
	// 按产品统计 销售个数与金额
	private List<SellItem> sellItems = new ArrayList<SellItem>();

	public static SellStatisData newInstance(long storeIdP) {
		SellStatisData data = new SellStatisData();
		data.storeId = storeIdP;
		return data;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public float getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}

	public float getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(float totalIncome) {
		this.totalIncome = totalIncome;
	}

	public float getPrdCost() {
		return prdCost;
	}

	public void setPrdCost(float prdCost) {
		this.prdCost = prdCost;
	}

	public float getGoodsCost() {
		return goodsCost;
	}

	public void setGoodsCost(float goodsCost) {
		this.goodsCost = goodsCost;
	}

	public float getPkgCost() {
		return pkgCost;
	}

	public void setPkgCost(float pkgCost) {
		this.pkgCost = pkgCost;
	}

	public float getCardCost() {
		return cardCost;
	}

	public void setCardCost(float cardCost) {
		this.cardCost = cardCost;
	}

	public float getRechargeCost() {
		return rechargeCost;
	}

	public void setRechargeCost(float rechargeCost) {
		this.rechargeCost = rechargeCost;
	}

	public List<SellDate> getSellDates() {
		return sellDates;
	}

	public void setSellDates(List<SellDate> sellDates) {
		this.sellDates = sellDates;
	}

	public List<SellItem> getSellItems() {
		return sellItems;
	}

	public void setSellItems(List<SellItem> sellItems) {
		this.sellItems = sellItems;
	}

	public float getTotalChargeBackCost() {
		return totalChargeBackCost;
	}

	public void setTotalChargeBackCost(float totalChargeBackCost) {
		this.totalChargeBackCost = totalChargeBackCost;
	}

}

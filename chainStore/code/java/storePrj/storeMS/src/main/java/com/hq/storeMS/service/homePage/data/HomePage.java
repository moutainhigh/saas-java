package com.hq.storeMS.service.homePage.data;

import java.util.List;

import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeMS.service.storeClerkInfo.data.StoreClerkInfo;
import com.hq.storeMS.service.storeGoods.data.StoreGoods;
import com.hq.storeMS.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.hq.storeMS.service.storeProductInfo.data.StoreProductInfo;
import com.hq.storeMS.service.workFlowType.data.WorkFlowType;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class HomePage {
	// 店铺客户
	private StoreLeaguerInfo storeLeaguer;
	// 店铺卡包
	private StoreCardInfo storeCard;
	// 店铺项目
	private StoreProductInfo storeProduct;
	// 店铺商品
	private StoreGoods storeGoods;
	// 店铺员工
	private StoreClerkInfo storeClerk;
	// 工作流类型
	private List<WorkFlowType> workFlowTypes;

	// 收银统计数据
	private StatisticsData statisticsData;

	public static HomePage newInstance() {
		HomePage data = new HomePage();
		return data;
	}

	public StoreLeaguerInfo getStoreLeaguer() {
		return storeLeaguer;
	}

	public void setStoreLeaguer(StoreLeaguerInfo storeLeaguer) {
		this.storeLeaguer = storeLeaguer;
	}

	public StoreCardInfo getStoreCard() {
		return storeCard;
	}

	public void setStoreCard(StoreCardInfo storeCard) {
		this.storeCard = storeCard;
	}

	public StoreProductInfo getStoreProduct() {
		return storeProduct;
	}

	public void setStoreProduct(StoreProductInfo storeProduct) {
		this.storeProduct = storeProduct;
	}

	public StoreGoods getStoreGoods() {
		return storeGoods;
	}

	public void setStoreGoods(StoreGoods storeGoods) {
		this.storeGoods = storeGoods;
	}

	public StoreClerkInfo getStoreClerk() {
		return storeClerk;
	}

	public void setStoreClerk(StoreClerkInfo storeClerk) {
		this.storeClerk = storeClerk;
	}

	public List<WorkFlowType> getWorkFlowTypes() {
		return workFlowTypes;
	}

	public void setWorkFlowTypes(List<WorkFlowType> workFlowTypes) {
		this.workFlowTypes = workFlowTypes;
	}

	public StatisticsData getStatisticsData() {
		return statisticsData;
	}

	public void setStatisticsData(StatisticsData statisticsData) {
		this.statisticsData = statisticsData;
	}

}

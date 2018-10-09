package com.hq.storeMS.service.dataReport.data.vo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import com.hq.orderRestClient.service.order.apiData.OrderQueryForm;
import com.hq.orderRestClient.service.order.data.BuyItem;
import com.hq.orderRestClient.service.order.data.BuyTypeEnum;
import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderTypeEnum;
import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.storeCardInfo.data.PrdCardType;
import com.hq.storeMS.service.storeCardInfo.data.ProductCard;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeMS.service.storeGoods.data.Goods;
import com.hq.storeMS.service.storeGoods.data.GoodsType;
import com.hq.storeMS.service.storeGoods.data.StoreGoods;
import com.hq.storeMS.service.storePackageProject.data.PackageProject;
import com.hq.storeMS.service.storePackageProject.data.PackageProjectType;
import com.hq.storeMS.service.storePackageProject.data.StorePackageProject;
import com.hq.storeMS.service.storeProductInfo.data.ProductInfo;
import com.hq.storeMS.service.storeProductInfo.data.ProductType;
import com.hq.storeMS.service.storeProductInfo.data.StoreProductInfo;
import com.zenmind.common.StringFormatUtil;

public class SellStatisDataBuilder {
	public static SellStatisDataBuilder newInstance() {
		SellStatisDataBuilder data = new SellStatisDataBuilder();
		return data;
	}

	private static final SimpleDateFormat SDF_DAY = new SimpleDateFormat("yyyy/MM/dd");

	private OrderQueryForm queryForm;
	private List<Order> orders;
	private StoreCardInfo storeCardInfo;
	private StoreGoods storeGoods;
	private StoreProductInfo storeProductInfo;
	private StorePackageProject storePackageProject;

	// 日期销售额 {date : sellDate}
	private Map<String, SellDate> sellDateMap = new HashMap<String, SellDate>();
	// 产品销售明细统计 {type_pgId : SellItem}
	private Map<String, SellItem> sellItemMap = new HashMap<String, SellItem>();

	public SellStatisData build() {
		if(queryForm == null) {
			return null;
		}
		SellStatisData result = SellStatisData.newInstance(queryForm.getStoreId());
		if (CollectionUtils.isEmpty(orders)) {
			return result;
		}
		for (Order order : orders) {
			buildCostByOrder(order, result);
		}
		buildSellDate(result);
		buildSellItem(result);
		return result;
	}

	private void buildSellDate(SellStatisData result) {
		Set<String> dateStrs = getDateStrs();
		for (String date : dateStrs) {
			SellDate sellDate = sellDateMap.get(date);
			if(sellDate == null) {
				sellDate = SellDate.newInstance();
				sellDate.setDate(date);
				sellDateMap.put(date, sellDate);
			}
		}
		List<SellDate> sellDates = new ArrayList<SellDate>(sellDateMap.values());
		Collections.sort(sellDates, new Comparator<SellDate>() {
			@Override
			public int compare(SellDate o1, SellDate o2) {
				return o1.getDate().compareTo(o2.getDate());
			}
		});
		result.setSellDates(sellDates);
	}
	
	private Set<String> getDateStrs(){
		long minTime = queryForm.getMinPayTime() == 0L ? System.currentTimeMillis() : queryForm.getMinPayTime();
		long maxTime = queryForm.getMaxPayTime() == 0L ? System.currentTimeMillis() : queryForm.getMaxPayTime();
		Set<String> dates = new HashSet<String>();
		while (minTime < maxTime) {
			dates.add(AppUtils.timeStamp2Str(minTime, SDF_DAY));
			minTime = minTime + ServerConstants.ONE_DAY;
		}
		dates.add(AppUtils.timeStamp2Str(maxTime, SDF_DAY));
		return dates;
	}

	private void buildSellItem(SellStatisData result) {
		if (MapUtils.isEmpty(sellItemMap)) {
			return;
		}
		result.setSellItems(new ArrayList<SellItem>(sellItemMap.values()));
	}

	private void buildCostByOrder(Order order, SellStatisData data) {
		float totalCost = data.getTotalCost();
		float totalIncome = data.getTotalIncome();
		float totalChargeBackCost = data.getTotalChargeBackCost();
		data.setTotalCost(totalCost + order.getCost());
		data.setTotalIncome(totalIncome + order.getRealPay());
		data.setTotalChargeBackCost(totalChargeBackCost + order.getChargeBackCost());

		buildDateStatis(order, data);

		int orderType = order.getOrderType();
		if (orderType == OrderTypeEnum.RECHARGE.ordinal()) {
			float rechargeCost = data.getRechargeCost();
			data.setRechargeCost(rechargeCost + order.getCost());
		} else {
			List<BuyItem> buyItems = order.getBuyItems();
			for (BuyItem buyItem : buyItems) {
				buildCostByBuyItem(buyItem, data);
			}
		}
	}

	private void buildDateStatis(Order order, SellStatisData data) {
		long payTime = order.getPayTime();
		String date = AppUtils.timeStamp2Str(payTime, SDF_DAY);
		SellDate sellDate = sellDateMap.get(date);
		if (sellDate == null) {
			sellDate = SellDate.newInstance();
			sellDate.setDate(date);
			sellDateMap.put(date, sellDate);
		}
		sellDate.setCost(sellDate.getCost() + order.getCost());
	}

	private void buildCostByBuyItem(BuyItem buyItem, SellStatisData data) {
		int buyType = buyItem.getBuyType();
		String pgId = buyItem.getPgId();
		String key = StringFormatUtil.format("{}_{}", buyType, pgId);
		SellItem sellItem = sellItemMap.get(key);
		if (sellItem == null) {
			sellItem = getSellItem(buyType, pgId);
			sellItemMap.put(key, sellItem);
		}
		float pay = buyItem.getPay();
		sellItem.setCount(sellItem.getCount() + 1);
		sellItem.setCost(sellItem.getCost() + pay);

		BuyTypeEnum buyTypeEnum = BuyTypeEnum.valueOf(buyType);
		switch (buyTypeEnum) {
		case GOODS:
			float goodsCost = data.getGoodsCost();
			data.setGoodsCost(goodsCost + pay);
			break;
		case PRDCARD:
			float cardCost = data.getCardCost();
			data.setCardCost(cardCost + pay);
			break;
		case PACKAGE:
			float pkgCost = data.getPkgCost();
			data.setPkgCost(pkgCost + pay);
			break;
		case PRODUCT:
			float prdCost = data.getPrdCost();
			data.setPrdCost(prdCost + pay);
			break;
		default:
			break;
		}
	}

	private SellItem getSellItem(int buyType, String pgId) {
		SellItem item = SellItem.newInstance();
		String pgName = "";
		String typeName = "";
		String defaultImg = "";
		BuyTypeEnum buyTypeEnum = BuyTypeEnum.valueOf(buyType);
		switch (buyTypeEnum) {
		case GOODS:
			Goods goods = storeGoods.getGoodsMap().get(pgId);
			if (goods != null) {
				pgName = goods.getName();
				defaultImg = goods.getDefaultImg();
				GoodsType goodsType = storeGoods.getGoodsTypeMap().get(goods.getTypeId());
				typeName = goodsType != null ? goodsType.getName() : "";
			}
			break;
		case PACKAGE:
			PackageProject packageProject = storePackageProject.getPackageProjectMap().get(pgId);
			if (packageProject != null) {
				pgName = packageProject.getName();
				defaultImg = packageProject.getDefaultImg();
				PackageProjectType packageProjectType = storePackageProject.getPackageProjectTypeMap()
						.get(packageProject.getTypeId());
				typeName = packageProjectType != null ? packageProjectType.getName() : "";
			}
			break;
		case PRDCARD:
			ProductCard productCard = storeCardInfo.getProductCardMap().get(pgId);
			if (productCard != null) {
				pgName = productCard.getName();
				defaultImg = productCard.getImgPath();
				PrdCardType prdCardType = storeCardInfo.getPrdCardTypeMap().get(productCard.getTypeId());
				typeName = prdCardType != null ? prdCardType.getName() : "";
			}
			break;
		case PRODUCT:
			ProductInfo productInfo = storeProductInfo.getProductInfoMap().get(pgId);
			if (productInfo != null) {
				pgName = productInfo.getName();
				defaultImg = productInfo.getDefaultImg();
				ProductType productType = storeProductInfo.getProductTypeMap().get(productInfo.getTypeId());
				typeName = productType != null ? productType.getName() : "";
			}
			break;
		case RECHARGE:
			pgName = "会员充值";
			break;
		default:
			pgName = "";
			break;
		}
		item.setItemType(buyType);
		item.setPgId(pgId);
		item.setPgName(pgName);
		item.setTypeName(typeName);
		item.setDefaultImg(defaultImg);
		return item;
	}

	public SellStatisDataBuilder setOrders(List<Order> orders) {
		this.orders = orders;
		return this;
	}

	public SellStatisDataBuilder setStoreCardInfo(StoreCardInfo storeCardInfo) {
		this.storeCardInfo = storeCardInfo;
		return this;
	}

	public SellStatisDataBuilder setStoreGoods(StoreGoods storeGoods) {
		this.storeGoods = storeGoods;
		return this;
	}

	public SellStatisDataBuilder setStoreProductInfo(StoreProductInfo storeProductInfo) {
		this.storeProductInfo = storeProductInfo;
		return this;
	}

	public SellStatisDataBuilder setStorePackageProject(StorePackageProject storePackageProject) {
		this.storePackageProject = storePackageProject;
		return this;
	}

	public SellStatisDataBuilder setQueryForm(OrderQueryForm queryForm) {
		this.queryForm = queryForm;
		return this;
	}
}

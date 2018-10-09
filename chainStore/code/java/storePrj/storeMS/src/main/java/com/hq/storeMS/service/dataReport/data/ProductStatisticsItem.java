package com.hq.storeMS.service.dataReport.data;

import com.hq.orderRestClient.service.order.data.BuyItem;
import com.zenmind.dataSyn.annotation.SynClass;

/**
 * @Description 产品统计--产品模型
 * @Creator geefox
 * @E-mail firstblh@163.com
 * @Date 2018/8/7
 */
@SynClass
public class ProductStatisticsItem {

	private String pgId;// 产品ID
	private String pgName;// 产品名字
	private int itemType;// BuyTypeEnum

	private String typeName;// 分类名称
	private int salesVolume;// 销售数量

	public ProductStatisticsItem(String pgId, String pgName, int itemType, String typeName) {
		this.pgId = pgId;
		this.pgName = pgName;
		this.itemType = itemType;
		this.typeName = typeName;
	}

	public static ProductStatisticsItem newInstance(String pgId, String pgName, int itemType, String typeName) {
		ProductStatisticsItem target = new ProductStatisticsItem(pgId, pgName, itemType, typeName);
		return target;
	}

	public static ProductStatisticsItem newInstance(BuyItem buyItem, String pgName, String typeName) {
		ProductStatisticsItem target = new ProductStatisticsItem(buyItem.getPgId(), pgName, buyItem.getBuyType(),
				typeName);
		return target;
	}

	public String getPgId() {
		return pgId;
	}

	public void setPgId(String pgId) {
		this.pgId = pgId;
	}

	public String getPgName() {
		return pgName;
	}

	public void setPgName(String pgName) {
		this.pgName = pgName;
	}

	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	public int getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(int salesVolume) {
		this.salesVolume = salesVolume;
	}

	public void addSalesVolume(int salesVolume) {
		this.salesVolume += salesVolume;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}

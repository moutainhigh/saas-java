package com.hq.storeClient.service.dataReport.data.vo;

import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 销售品明细实体
 */
@SynClass
public class SellItem {
	// 产品ID
	private String pgId;
	// 产品名字 [充值类型 固定名称为 会员充值]
	private String pgName;
	// BuyTypeEnum
	private int itemType;
	// 分类名称
	private String typeName;
	// 默认图片
	private String defaultImg;
	// 销售数量
	private int count;
	// 销售金额
	private float cost;

	public static SellItem newInstance() {
		SellItem data = new SellItem();
		return data;
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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public String getDefaultImg() {
		return defaultImg;
	}

	public void setDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg;
	}
}

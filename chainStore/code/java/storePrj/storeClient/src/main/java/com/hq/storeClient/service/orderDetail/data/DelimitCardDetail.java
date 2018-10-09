package com.hq.storeClient.service.orderDetail.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class DelimitCardDetail {
	// 划卡消费ID itemType_pgId_leaguerPrdCardId 方便退单时使用
	private String delimitCardItemId;
	// 类型 ProductCardItemEnum
	private int itemType;
	// 项目/商品/套餐的ID
	private String pgId;
	// 名称
	private String pgName;
	private String typeName;
	// 默认图片
	private String defaultImg;
	// 价格
	private float price;
	// 客户次卡的ID
	private String leaguerPrdCardId;
	// 次卡名称
	private String prdCardName;
	// 数量
	private int count;

	public static DelimitCardDetail newInstance() {
		DelimitCardDetail data = new DelimitCardDetail();
		return data;
	}

	public String getDelimitCardItemId() {
		return delimitCardItemId;
	}

	public void setDelimitCardItemId(String delimitCardItemId) {
		this.delimitCardItemId = delimitCardItemId;
	}

	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getLeaguerPrdCardId() {
		return leaguerPrdCardId;
	}

	public void setLeaguerPrdCardId(String leaguerPrdCardId) {
		this.leaguerPrdCardId = leaguerPrdCardId;
	}

	public String getPrdCardName() {
		return prdCardName;
	}

	public void setPrdCardName(String prdCardName) {
		this.prdCardName = prdCardName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDefaultImg() {
		return defaultImg;
	}

	public void setDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg;
	}

}

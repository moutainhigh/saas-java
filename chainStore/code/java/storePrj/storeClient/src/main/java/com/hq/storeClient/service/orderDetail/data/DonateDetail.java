package com.hq.storeClient.service.orderDetail.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class DonateDetail {
	// 赠送项ID buyType_pgId 方便退单时使用
	private String donationItemId;
	// 购买类型 BuyTypeEnum
	private int buyType;
	// 项目/商品/次卡/套餐的ID
	private String pgId;
	private String pgName;
	private String typeName;
	// 默认图片
	private String defaultImg;
	// 售价
	private float price;
	// 数量
	private int count;
	// 总价
	private float cost;

	public static DonateDetail newInstance() {
		DonateDetail data = new DonateDetail();
		return data;
	}

	public String getDonationItemId() {
		return donationItemId;
	}

	public void setDonationItemId(String donationItemId) {
		this.donationItemId = donationItemId;
	}

	public int getBuyType() {
		return buyType;
	}

	public void setBuyType(int buyType) {
		this.buyType = buyType;
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

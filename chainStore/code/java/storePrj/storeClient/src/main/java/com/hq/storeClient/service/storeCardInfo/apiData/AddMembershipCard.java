package com.hq.storeClient.service.storeCardInfo.apiData;

public class AddMembershipCard {
	private int index;
	private String number;// 会员卡编号
	private String name;
	private float freeMoney;// 赠送金额
	private float prodDiscount;// 项目折扣
	private float goodsDiscount;// 商品折扣
	private float prdCardDiscount;// 次卡折扣
	private float packagePrjDiscount;// 次卡折扣
	private int status;// 卡片状态 对应CardStatusEnum
	private String imgPath;
	private String descript;// 描述

	public static AddMembershipCard newInstance() {
		return new AddMembershipCard();
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getFreeMoney() {
		return freeMoney;
	}

	public void setFreeMoney(float freeMoney) {
		this.freeMoney = freeMoney;
	}

	public float getProdDiscount() {
		return prodDiscount;
	}

	public void setProdDiscount(float prodDiscount) {
		this.prodDiscount = prodDiscount;
	}

	public float getGoodsDiscount() {
		return goodsDiscount;
	}

	public void setGoodsDiscount(float goodsDiscount) {
		this.goodsDiscount = goodsDiscount;
	}

	public float getPrdCardDiscount() {
		return prdCardDiscount;
	}

	public void setPrdCardDiscount(float prdCardDiscount) {
		this.prdCardDiscount = prdCardDiscount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public float getPackagePrjDiscount() {
		return packagePrjDiscount;
	}

	public void setPackagePrjDiscount(float packagePrjDiscount) {
		this.packagePrjDiscount = packagePrjDiscount;
	}
}

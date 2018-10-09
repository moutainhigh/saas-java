package com.hq.customerRestClient.service.storeCardInfo.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class MembershipCard {
	/**************************** 简版信息 **************************************/
	private String id;
	// 会员卡编号
	private String number;
	private String name;
	// 赠送金额
	private float freeMoney;
	// 项目折扣
	private float prodDiscount;
	// 商品折扣
	private float goodsDiscount;
	// 次卡折扣
	private float prdCardDiscount;
	// 套餐折扣
	private float packagePrjDiscount;
	// 卡片状态 对应CardStatusEnum
	private int status;
	private int entityState;
	private String imgPath;
	
	public static MembershipCard newInstance() {
		return new MembershipCard();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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

	public float getPackagePrjDiscount() {
		return packagePrjDiscount;
	}

	public void setPackagePrjDiscount(float packagePrjDiscount) {
		this.packagePrjDiscount = packagePrjDiscount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getEntityState() {
		return entityState;
	}

	public void setEntityState(int entityState) {
		this.entityState = entityState;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

}

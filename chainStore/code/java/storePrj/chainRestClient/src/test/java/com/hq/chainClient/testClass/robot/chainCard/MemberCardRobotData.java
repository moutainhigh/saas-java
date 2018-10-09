package com.hq.chainClient.testClass.robot.chainCard;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainClient.service.chainCard.apiData.AddMembershipCard;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class MemberCardRobotData {
	private int index;
	private String number;// 会员卡编号
	private String name;
	private float freeMoney;// 赠送金额
	private float prodDiscount;// 项目折扣
	private float goodsDiscount;// 商品折扣
	private float prdCardDiscount;// 次卡折扣
	private float packagePrjDiscount;// 套餐折扣
	private int status;// 卡片状态 对应 CardStatusEnum
	private String imgPath;//默认图片
	
	private String descript;// 描述

	public static MemberCardRobotData newRandomInstance() {
		int random = RandomUtils.nextInt(1, 100);
		MemberCardRobotData data = new MemberCardRobotData();
		data.name = "会员卡" + random;
		data.descript = "会员卡描述" + random;
		data.number = "number"+random;
		data.status = 0;
		data.imgPath = "defaultImg"+random;
		
		data.freeMoney = RandomUtils.nextFloat(100f, 500f);
		data.prodDiscount = RandomUtils.nextFloat(1f, 10f);
		data.goodsDiscount = RandomUtils.nextFloat(1f, 10f);
		data.prdCardDiscount = RandomUtils.nextFloat(1f, 10f);
		data.packagePrjDiscount = RandomUtils.nextFloat(1f, 10f);
		return data;
	}

	public AddMembershipCard toAddMembershipCard(int index) {
		AddMembershipCard data = AddMembershipCard.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		data.setIndex(index);
		return data;
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

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}
}

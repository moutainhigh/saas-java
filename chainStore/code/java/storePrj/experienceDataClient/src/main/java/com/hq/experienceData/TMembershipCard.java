package com.hq.experienceData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import com.hq.experienceData.service.DataConstants;
import com.hq.experienceData.service.RandomUtils;

public class TMembershipCard {
	private String number;// 会员卡编号
	private String name;
	private float freeMoney;// 赠送金额
	private float prodDiscount;// 项目折扣
	private float goodsDiscount;// 商品折扣
	private float prdCardDiscount;// 次卡折扣
	private int status;// 卡片状态 对应CardStatusEnum
	private String descript;// 描述
	private String imgPath;

	public static List<TMembershipCard> buildMembershipCardList() {
		List<TMembershipCard> list = new ArrayList<TMembershipCard>();
		String[] tmpNames = { "金卡", "白金卡", "钻石卡", "铜卡", "VIP卡", "至尊卡", "金尊卡",
				"银卡", "黑卡", "青春卡" };
		for (String name : tmpNames) {
			TMembershipCard data = new TMembershipCard();
			data.setNumber(RandomStringUtils.random(10, DataConstants.RANDOM_STRING));
			data.setName(name);
			data.setFreeMoney(RandomUtils.nextFloat(1000f, 5000f));
			data.setProdDiscount(RandomUtils.nextFloat(10f, 50f));
			data.setGoodsDiscount(RandomUtils.nextFloat(10f, 50f));
			data.setPrdCardDiscount(RandomUtils.nextFloat(10f, 50f));
			data.setStatus(RandomUtils.nextInt(0, 3));
			data.setImgPath("");
			data.setDescript("会员卡模板");
			list.add(data);
		}
		Collections.shuffle(list);
		return list.subList(0, DataConstants.NUMBER_COUNT);
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

}

package com.hq.storeMS.service.orderDetail.data;

import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.storeCardInfo.data.MembershipCard;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class SimpleLeaguerInfo {
	private String id;
	// 客户姓名
	private String name;
	// 性别
	private int sex;
	// 手机号
	private long phone;
	// 头像
	private String headImg;

	// 会员卡名称
	private String memberCardName;
	// 项目折扣
	private float prodDiscount;
	// 商品折扣
	private float goodsDiscount;
	// 次卡折扣
	private float prdCardDiscount;
	// 套餐折扣
	private float packagePrjDiscount;

	public static SimpleLeaguerInfo newInstance() {
		SimpleLeaguerInfo data = new SimpleLeaguerInfo();
		return data;
	}

	public static SimpleLeaguerInfo newInstanceByLeaguerDetailAndMemberCard(LeaguerDetail leaguerDetail,
			MembershipCard memberCard) {
		SimpleLeaguerInfo data = new SimpleLeaguerInfo();
		FastBeanCopyer.getInstance().copy(leaguerDetail, data);
		if (memberCard != null) {
			data.memberCardName = memberCard.getName();
			data.prodDiscount = memberCard.getProdDiscount();
			data.goodsDiscount = memberCard.getGoodsDiscount();
			data.prdCardDiscount = memberCard.getPrdCardDiscount();
			data.packagePrjDiscount = memberCard.getPackagePrjDiscount();
		}
		return data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getMemberCardName() {
		return memberCardName;
	}

	public void setMemberCardName(String memberCardName) {
		this.memberCardName = memberCardName;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getPackagePrjDiscount() {
		return packagePrjDiscount;
	}

	public void setPackagePrjDiscount(float packagePrjDiscount) {
		this.packagePrjDiscount = packagePrjDiscount;
	}

}

package com.hq.chainMS.service.chainCard.apiData;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.hq.chainMS.service.chainCard.data.MembershipCard;
import com.hq.chainMS.service.chainCard.data.MembershipCardDetail;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class UpdMembershipCard {
	private String id;// chainId_index
	private String number;// 编号
	private String name;
	private float freeMoney;// 赠送金额
	private float prodDiscount;// 项目折扣
	private float goodsDiscount;// 商品折扣
	private float prdCardDiscount;// 次卡折扣
	private float packagePrjDiscount;// 套餐折扣
	private int status;// 卡片状态 对应CardStatusEnum
	private String imgPath;
	// 适用门店
	private Set<Long> applyStoreIds = new HashSet<Long>();
	
	private String descript;// 描述

	public static UpdMembershipCard newInstance() {
		return new UpdMembershipCard();
	}

	public void updateMembershipCard(MembershipCard data) {
		BeanUtils.copyProperties(this, data);
	}

	public void updateMembershipCardDetail(MembershipCardDetail detail) {
		FastBeanCopyer.getInstance().copy(this, detail);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public float getPackagePrjDiscount() {
		return packagePrjDiscount;
	}

	public void setPackagePrjDiscount(float packagePrjDiscount) {
		this.packagePrjDiscount = packagePrjDiscount;
	}

	public Set<Long> getApplyStoreIds() {
		return applyStoreIds;
	}

	public void setApplyStoreIds(Set<Long> applyStoreIds) {
		this.applyStoreIds = applyStoreIds;
	}

}

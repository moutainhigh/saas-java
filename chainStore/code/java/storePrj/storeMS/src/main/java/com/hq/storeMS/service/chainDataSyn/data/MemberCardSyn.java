package com.hq.storeMS.service.chainDataSyn.data;

import com.hq.chainClient.service.chainCard.data.MembershipCard;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class MemberCardSyn {
	private long storeId;
	private long chainId;

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
	private String imgPath;
	
	// 同步状态 对应 ChainDataStatusEnum
	private int synStatus;

	public static MemberCardSyn newInstance() {
		return new MemberCardSyn();
	}
	
	public static MemberCardSyn newInstanceByMemberCard(MembershipCard data, long storeId, long chainId) {
		MemberCardSyn rs = newInstance();
		FastBeanCopyer.getInstance().copy(data, rs);
		rs.storeId=storeId;
		rs.chainId=chainId;
		return rs;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getChainId() {
		return chainId;
	}

	public void setChainId(long chainId) {
		this.chainId = chainId;
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

	public float getPackagePrjDiscount() {
		return packagePrjDiscount;
	}

	public void setPackagePrjDiscount(float packagePrjDiscount) {
		this.packagePrjDiscount = packagePrjDiscount;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public int getSynStatus() {
		return synStatus;
	}

	public void setSynStatus(int synStatus) {
		this.synStatus = synStatus;
	}
}

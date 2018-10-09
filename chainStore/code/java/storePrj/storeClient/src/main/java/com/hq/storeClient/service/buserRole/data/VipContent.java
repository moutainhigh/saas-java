package com.hq.storeClient.service.buserRole.data;

import java.util.HashSet;
import java.util.Set;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class VipContent {
	//会员等级
	private int vipType;
	
	// 店铺数
	private int storeLimit;
	// 客户数
	private int leaguerLimit;
	// 商品
	private int goodsLimit;
	// 项目
	private int productLimit;
	// 套餐
	private int packageLimit;
	// 次卡
	private int prdCardLimit;
	// 会员卡
	private int memberCardLimit;
	// 店员数
	private int buserLimit;

	/**
	 * 权限集合
	 * {@link com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum}
	 */
	private Set<Integer> permSet = new HashSet<Integer>();

	public static VipContent newInstance() {
		VipContent data = new VipContent();
		return data;
	}

	public int getStoreLimit() {
		return storeLimit;
	}

	public void setStoreLimit(int storeLimit) {
		this.storeLimit = storeLimit;
	}

	public int getLeaguerLimit() {
		return leaguerLimit;
	}

	public void setLeaguerLimit(int leaguerLimit) {
		this.leaguerLimit = leaguerLimit;
	}

	public int getGoodsLimit() {
		return goodsLimit;
	}

	public void setGoodsLimit(int goodsLimit) {
		this.goodsLimit = goodsLimit;
	}

	public int getProductLimit() {
		return productLimit;
	}

	public void setProductLimit(int productLimit) {
		this.productLimit = productLimit;
	}

	public int getPackageLimit() {
		return packageLimit;
	}

	public void setPackageLimit(int packageLimit) {
		this.packageLimit = packageLimit;
	}

	public int getPrdCardLimit() {
		return prdCardLimit;
	}

	public void setPrdCardLimit(int prdCardLimit) {
		this.prdCardLimit = prdCardLimit;
	}

	public int getMemberCardLimit() {
		return memberCardLimit;
	}

	public void setMemberCardLimit(int memberCardLimit) {
		this.memberCardLimit = memberCardLimit;
	}

	public int getBuserLimit() {
		return buserLimit;
	}

	public void setBuserLimit(int buserLimit) {
		this.buserLimit = buserLimit;
	}

	public Set<Integer> getPermSet() {
		return permSet;
	}

	public void setPermSet(Set<Integer> permSet) {
		this.permSet = permSet;
	}

	public int getVipType() {
		return vipType;
	}

	public void setVipType(int vipType) {
		this.vipType = vipType;
	}

}

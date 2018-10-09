package com.hq.storeMS.service.buserRole.data;

import java.util.HashSet;
import java.util.Set;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class VipContent {
	//会员等级
	private long vipType;
	
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

	public long getVipType() {
		return vipType;
	}

	public void setVipType(long vipType) {
		this.vipType = vipType;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + buserLimit;
		result = prime * result + goodsLimit;
		result = prime * result + leaguerLimit;
		result = prime * result + memberCardLimit;
		result = prime * result + packageLimit;
		result = prime * result + ((permSet == null) ? 0 : permSet.hashCode());
		result = prime * result + prdCardLimit;
		result = prime * result + productLimit;
		result = prime * result + storeLimit;
		result = prime * result + (int) (vipType ^ (vipType >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VipContent other = (VipContent) obj;
		if (buserLimit != other.buserLimit)
			return false;
		if (goodsLimit != other.goodsLimit)
			return false;
		if (leaguerLimit != other.leaguerLimit)
			return false;
		if (memberCardLimit != other.memberCardLimit)
			return false;
		if (packageLimit != other.packageLimit)
			return false;
		if (permSet == null) {
			if (other.permSet != null)
				return false;
		} else if (!permSet.equals(other.permSet))
			return false;
		if (prdCardLimit != other.prdCardLimit)
			return false;
		if (productLimit != other.productLimit)
			return false;
		if (storeLimit != other.storeLimit)
			return false;
		if (vipType != other.vipType)
			return false;
		return true;
	}

}

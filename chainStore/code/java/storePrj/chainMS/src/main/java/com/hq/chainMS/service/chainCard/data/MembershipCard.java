package com.hq.chainMS.service.chainCard.data;

import java.util.HashSet;
import java.util.Set;

import com.hq.chainMS.common.constants.ServerConstants;
import com.hq.chainMS.common.util.AppUtils;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class MembershipCard {
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
	// 卡片状态 对应 CardStatusEnum
	private int status;
	private int entityState;
	private String imgPath;
	// 适用门店
	private Set<Long> applyStoreIds = new HashSet<Long>();

	public static MembershipCard newInstance() {
		return new MembershipCard();
	}

	public static String generateId(long chainId, int index) {
		return AppUtils.joinByUnderline(ServerConstants.CHAIN_MEMBERSHIP_CARD_ID_SUFFIX, chainId, index);
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

	public int getEntityState() {
		return entityState;
	}

	public void setEntityState(int entityState) {
		this.entityState = entityState;
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

	public Set<Long> getApplyStoreIds() {
		return applyStoreIds;
	}

	public void setApplyStoreIds(Set<Long> applyStoreIds) {
		this.applyStoreIds = applyStoreIds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((applyStoreIds == null) ? 0 : applyStoreIds.hashCode());
		result = prime * result + entityState;
		result = prime * result + Float.floatToIntBits(freeMoney);
		result = prime * result + Float.floatToIntBits(goodsDiscount);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imgPath == null) ? 0 : imgPath.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + Float.floatToIntBits(packagePrjDiscount);
		result = prime * result + Float.floatToIntBits(prdCardDiscount);
		result = prime * result + Float.floatToIntBits(prodDiscount);
		result = prime * result + status;
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
		MembershipCard other = (MembershipCard) obj;
		if (applyStoreIds == null) {
			if (other.applyStoreIds != null)
				return false;
		} else if (!applyStoreIds.equals(other.applyStoreIds))
			return false;
		if (entityState != other.entityState)
			return false;
		if (Float.floatToIntBits(freeMoney) != Float.floatToIntBits(other.freeMoney))
			return false;
		if (Float.floatToIntBits(goodsDiscount) != Float.floatToIntBits(other.goodsDiscount))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (imgPath == null) {
			if (other.imgPath != null)
				return false;
		} else if (!imgPath.equals(other.imgPath))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (Float.floatToIntBits(packagePrjDiscount) != Float.floatToIntBits(other.packagePrjDiscount))
			return false;
		if (Float.floatToIntBits(prdCardDiscount) != Float.floatToIntBits(other.prdCardDiscount))
			return false;
		if (Float.floatToIntBits(prodDiscount) != Float.floatToIntBits(other.prodDiscount))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

}

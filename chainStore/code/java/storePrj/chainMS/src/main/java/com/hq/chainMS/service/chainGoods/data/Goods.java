package com.hq.chainMS.service.chainGoods.data;

import java.util.HashSet;
import java.util.Set;

import com.hq.chainMS.common.constants.ServerConstants;
import com.hq.chainMS.common.util.AppUtils;
import com.hq.chainMS.service.sellProduct.data.SellProduct;
import com.hq.chainMS.service.sellProduct.data.SellProductTypeEnum;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class Goods {
	private String id;
	// 商品编号
	private String number;
	// 名称
	private String name;
	// 商品分类Id
	private String typeId;
	// 状态 对应 GoodsStateEnum
	private int state;
	private int entityState;
	// 售价
	private float sellPrice;
	// 默认图片
	private String defaultImg;
	// 适用门店
	private Set<Long> applyStoreIds = new HashSet<Long>();

	public static Goods newInstance() {
		return new Goods();
	}

	public static String generateId(long chainId, long index) {
		return AppUtils.joinByUnderline(ServerConstants.CHAIN_GOODS_TYPE_ID_SUFFIX, chainId, index);
	}
	
	public SellProduct toSellProduct() {
		SellProduct data = SellProduct.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		data.setSellProductType(SellProductTypeEnum.GOODS.ordinal());
		return data;
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

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getEntityState() {
		return entityState;
	}

	public void setEntityState(int entityState) {
		this.entityState = entityState;
	}

	public String getDefaultImg() {
		return defaultImg;
	}

	public void setDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg;
	}

	public float getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
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
		result = prime * result + ((defaultImg == null) ? 0 : defaultImg.hashCode());
		result = prime * result + entityState;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + Float.floatToIntBits(sellPrice);
		result = prime * result + state;
		result = prime * result + ((typeId == null) ? 0 : typeId.hashCode());
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
		Goods other = (Goods) obj;
		if (applyStoreIds == null) {
			if (other.applyStoreIds != null)
				return false;
		} else if (!applyStoreIds.equals(other.applyStoreIds))
			return false;
		if (defaultImg == null) {
			if (other.defaultImg != null)
				return false;
		} else if (!defaultImg.equals(other.defaultImg))
			return false;
		if (entityState != other.entityState)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		if (Float.floatToIntBits(sellPrice) != Float.floatToIntBits(other.sellPrice))
			return false;
		if (state != other.state)
			return false;
		if (typeId == null) {
			if (other.typeId != null)
				return false;
		} else if (!typeId.equals(other.typeId))
			return false;
		return true;
	}
}

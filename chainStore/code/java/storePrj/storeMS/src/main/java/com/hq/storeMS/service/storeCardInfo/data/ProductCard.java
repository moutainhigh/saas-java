package com.hq.storeMS.service.storeCardInfo.data;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.util.AppUtils;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class ProductCard {
	// id _prd_storeId_index
	private String id;
	// 编号
	private String number;
	// 名称
	private String name;
	// 分类Id
	private String typeId;
	// 状态 对应 CardStatusEnum
	private int status;
	private int entityState;
	// 售价
	private float sellPrice;
	// 默认图片
	private String imgPath;
	// 有效期 eg：365日/12月/1年
	private int validPeriod;
	// 有效期时间的单位 对应 ValidPeriodUnitEnum
	private int validPeriodUnit;
	// 来源 对应 DataOriginEnum
	private int origin;
	// 置顶标识 TopFlagEnum
	private int topFlag;
	// 促销标识 PromotionFlagEnum
	private int promotionFlag;
	// 促销价格
	private float promotionPrice;

	public static ProductCard newInstance() {
		return new ProductCard();
	}

	public static String generateId(long storeId, int index) {
		return AppUtils.joinByUnderline(ServerConstants.STORE_PRODUCTCARD_ID_SUFFFIX, storeId, index);
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

	public float getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public int getValidPeriod() {
		return validPeriod;
	}

	public void setValidPeriod(int validPeriod) {
		this.validPeriod = validPeriod;
	}

	public int getValidPeriodUnit() {
		return validPeriodUnit;
	}

	public void setValidPeriodUnit(int validPeriodUnit) {
		this.validPeriodUnit = validPeriodUnit;
	}

	public int getOrigin() {
		return origin;
	}

	public void setOrigin(int origin) {
		this.origin = origin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + entityState;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imgPath == null) ? 0 : imgPath.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + origin;
		result = prime * result + promotionFlag;
		result = prime * result + Float.floatToIntBits(promotionPrice);
		result = prime * result + Float.floatToIntBits(sellPrice);
		result = prime * result + status;
		result = prime * result + topFlag;
		result = prime * result + ((typeId == null) ? 0 : typeId.hashCode());
		result = prime * result + validPeriod;
		result = prime * result + validPeriodUnit;
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
		ProductCard other = (ProductCard) obj;
		if (entityState != other.entityState)
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
		if (origin != other.origin)
			return false;
		if (promotionFlag != other.promotionFlag)
			return false;
		if (Float.floatToIntBits(promotionPrice) != Float.floatToIntBits(other.promotionPrice))
			return false;
		if (Float.floatToIntBits(sellPrice) != Float.floatToIntBits(other.sellPrice))
			return false;
		if (status != other.status)
			return false;
		if (topFlag != other.topFlag)
			return false;
		if (typeId == null) {
			if (other.typeId != null)
				return false;
		} else if (!typeId.equals(other.typeId))
			return false;
		if (validPeriod != other.validPeriod)
			return false;
		if (validPeriodUnit != other.validPeriodUnit)
			return false;
		return true;
	}

	public int getTopFlag() {
		return topFlag;
	}

	public void setTopFlag(int topFlag) {
		this.topFlag = topFlag;
	}

	public int getPromotionFlag() {
		return promotionFlag;
	}

	public void setPromotionFlag(int promotionFlag) {
		this.promotionFlag = promotionFlag;
	}

	public float getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(float promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

}

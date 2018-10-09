package com.hq.storeMS.service.storeGoods.data;

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
	private float price;
	// 默认图片
	private String defaultImg;
	// 来源 对应 DataOriginEnum
	private int origin;
	// 置顶标识 TopFlagEnum
	private int topFlag;
	// 促销标识 PromotionFlagEnum
	private int promotionFlag;
	// 促销价格
	private float promotionPrice;

	public static Goods newInstance() {
		Goods data = new Goods();
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
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

	public int getOrigin() {
		return origin;
	}

	public void setOrigin(int origin) {
		this.origin = origin;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((defaultImg == null) ? 0 : defaultImg.hashCode());
		result = prime * result + entityState;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + origin;
		result = prime * result + Float.floatToIntBits(price);
		result = prime * result + promotionFlag;
		result = prime * result + Float.floatToIntBits(promotionPrice);
		result = prime * result + state;
		result = prime * result + topFlag;
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
		if (origin != other.origin)
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (promotionFlag != other.promotionFlag)
			return false;
		if (Float.floatToIntBits(promotionPrice) != Float.floatToIntBits(other.promotionPrice))
			return false;
		if (state != other.state)
			return false;
		if (topFlag != other.topFlag)
			return false;
		if (typeId == null) {
			if (other.typeId != null)
				return false;
		} else if (!typeId.equals(other.typeId))
			return false;
		return true;
	}
}

package com.hq.storeClient.service.storeCardInfo.data;

import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 次卡实体
 * 
 */
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
	// 状态 对应CardStatusEnum
	private int status;
	private int entityState;
	// 售价
	private float sellPrice;
	// 默认图片
	private String imgPath;
	// 有效期 eg：365日/12月/1年
	private int validPeriod;
	// 有效期时间的单位 对应ValidPeriodUnitEnum
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

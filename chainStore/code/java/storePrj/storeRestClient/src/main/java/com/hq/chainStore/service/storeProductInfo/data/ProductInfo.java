package com.hq.chainStore.service.storeProductInfo.data;

import java.util.List;
import java.util.Set;

public class ProductInfo {
	/**************************** 简版信息 **************************************/
	private String id;
	// 编号
	private String number;
	private String name;
	private String typeId;
	private int state;
	private int entityState;
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

	/******************** 遗留字段 *********************************/
	private float cost;// 成本
	private String descript;
	private List<String> imgPathList;
	private long createTime;
	private long lastUpdateTime;
	// 对应医美师
	private Set<Long> beauticianSet;
	// 对应耗材
	@Deprecated
	private List<ProductMaterial> materialList;
	// 标签
	private String label;
	// 评分
	private float score;
	// 订单数量
	private long orderCount;

	private int type;

	public static ProductInfo newInstance() {
		return new ProductInfo();
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public List<String> getImgPathList() {
		return imgPathList;
	}

	public void setImgPathList(List<String> imgPathList) {
		this.imgPathList = imgPathList;
	}

	public void addImgPath(String imgPath) {
		this.imgPathList.add(imgPath);
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public long getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(long orderCount) {
		this.orderCount = orderCount;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Set<Long> getBeauticianSet() {
		return beauticianSet;
	}

	public void setBeauticianSet(Set<Long> beauticianSet) {
		this.beauticianSet = beauticianSet;
	}

	@Deprecated
	public List<ProductMaterial> getMaterialList() {
		return materialList;
	}

	@Deprecated
	public void setMaterialList(List<ProductMaterial> materialList) {
		this.materialList = materialList;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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

}

package com.hq.chainStore.service.storeCardInfo.apiData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hq.chainStore.service.productCardDetail.data.ProductCardItem;
import com.hq.chainStore.service.storeCardInfo.data.PrdInCard;

public class UpdProductCardForm {
	private String id;
	// 编号
	private String number;
	// 名称
	private String name;
	// 分类Id
	private String typeId;
	// 状态 对应CardStatusEnum
	private int status;
	// 售价
	private float sellPrice;
	// 默认图片
	private String imgPath;
	// 有效期 eg：365日/12月/1年
	private int validPeriod;
	// 有效期时间的单位 对应ValidPeriodUnitEnum
	private int validPeriodUnit;
	// 促销标识 PromotionFlagEnum
	private int promotionFlag;
	// 促销价格
	private float promotionPrice;

	// 描述
	private String descript;
	// 图片列表
	private List<String> imgPaths = new ArrayList<String>();
	// 次卡内容
	private List<ProductCardItem> productCardItems = new ArrayList<ProductCardItem>();

	/****************************** 遗留字段 ****************************************/
	private int type;// 类型 productCardTypeEnum
	private int time;// 次数
	private List<PrdInCard> productList = new ArrayList<PrdInCard>();
	// 每个项目对应的次数
	private Map<Long, Long> productCountMap = new HashMap<Long, Long>();
	// 对应原价格 所有项目价格的总和
	private float price;
	// 对应折后价格
	private float salePrice;

	public static UpdProductCardForm newInstance() {
		return new UpdProductCardForm();
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<PrdInCard> getProductList() {
		return productList;
	}

	public void setProductList(List<PrdInCard> productList) {
		this.productList = productList;
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

	public Map<Long, Long> getProductCountMap() {
		return productCountMap;
	}

	public void setProductCountMap(Map<Long, Long> productCountMap) {
		this.productCountMap = productCountMap;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(float salePrice) {
		this.salePrice = salePrice;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public float getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public List<String> getImgPaths() {
		return imgPaths;
	}

	public void setImgPaths(List<String> imgPaths) {
		this.imgPaths = imgPaths;
	}

	public List<ProductCardItem> getProductCardItems() {
		return productCardItems;
	}

	public void setProductCardItems(List<ProductCardItem> productCardItems) {
		this.productCardItems = productCardItems;
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

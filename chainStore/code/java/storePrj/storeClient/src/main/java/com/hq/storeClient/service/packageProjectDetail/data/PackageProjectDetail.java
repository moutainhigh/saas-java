package com.hq.storeClient.service.packageProjectDetail.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dao.classinfo.IndexField;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "packageProjectDetail")
public class PackageProjectDetail {
	// storeId_index
	@Id
	private String id;
	@IndexField
	private long storeId;
	// 套餐编号
	private String number;
	// 名称
	private String name;
	// 分类Id
	private String typeId;
	// 状态 对应 PackageStateEnum
	private int state;
	private int entityState;
	// 售价
	private float sellPrice;
	// 成本
	private float cost;
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

	// 描述
	private String descript;
	// 图片列表
	private List<String> imgPaths = new ArrayList<String>();
	// 套餐内容
	private List<PackageItem> packageItems = new ArrayList<PackageItem>();

	@IndexField
	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static PackageProjectDetail newInstance() {
		PackageProjectDetail data = new PackageProjectDetail();
		long curTime = System.currentTimeMillis();
		data.createdTime = curTime;
		data.lastUpdateTime = curTime;
		return data;
	}

	public void incrVer() {
		this.ver = ver + 1;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
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

	public float getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public String getDefaultImg() {
		return defaultImg;
	}

	public void setDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public List<String> getImgPaths() {
		return imgPaths;
	}

	public void setImgPaths(List<String> imgPaths) {
		this.imgPaths = imgPaths;
	}

	public List<PackageItem> getPackageItems() {
		return packageItems;
	}

	public void setPackageItems(List<PackageItem> packageItems) {
		this.packageItems = packageItems;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public long getVer() {
		return ver;
	}

	public void setVer(long ver) {
		this.ver = ver;
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

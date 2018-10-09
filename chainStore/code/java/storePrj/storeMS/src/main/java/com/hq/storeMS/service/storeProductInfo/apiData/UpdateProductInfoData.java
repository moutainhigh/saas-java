package com.hq.storeMS.service.storeProductInfo.apiData;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.service.productDetail.data.ProductDetail;
import com.hq.storeMS.service.storeProductInfo.data.ProductInfo;

public class UpdateProductInfoData {
	private String id;
	private long storeId;
	private String name;
	private String number;
	private String typeId;
	private float price;
	private int state;
	private String defaultImg;
	// 促销标识 PromotionFlagEnum
	private int promotionFlag;
	// 促销价格
	private float promotionPrice;

	// 成本
	private float cost;
	private String descript;
	private List<String> imgPathList = new ArrayList<String>();

	public static UpdateProductInfoData newInstance() {
		UpdateProductInfoData data = new UpdateProductInfoData();
		return data;
	}

	public void updateProductInfo(ProductInfo data) {
		if (CollectionUtils.isEmpty(imgPathList)) {
			imgPathList.add(ServerConstants.PRODUCT_DEFAULT_PATH);
		}
		if (StringUtils.isBlank(defaultImg)) {
			defaultImg = imgPathList.get(0);
		}
		BeanUtils.copyProperties(this, data, "cost", "descript", "imgPathList", "id");
	}

	public void updateProductDetail(ProductDetail detail) {
		if (CollectionUtils.isEmpty(imgPathList)) {
			imgPathList.add(ServerConstants.PRODUCT_DEFAULT_PATH);
		}
		if (StringUtils.isBlank(defaultImg)) {
			defaultImg = imgPathList.get(0);
		}
		BeanUtils.copyProperties(this, detail, "id");
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

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDefaultImg() {
		return defaultImg;
	}

	public void setDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg;
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

package com.hq.storeMS.service.storeProductInfo.apiData;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.productDetail.data.ProductDetail;
import com.hq.storeMS.service.storeProductInfo.data.ProductInfo;

public class AddProductInfoData {
	private int index;
	
	private long storeId;
	private String number;// 编号
	private String name;
	private String typeId;
	private float price;
	private int state;
	private String defaultImg;
	// 促销标识 PromotionFlagEnum
	private int promotionFlag;
	// 促销价格
	private float promotionPrice;

	private float cost;// 成本
	private String descript;
	private List<String> imgPathList = new ArrayList<String>();

	public static AddProductInfoData newInstance() {
		AddProductInfoData data = new AddProductInfoData();
		return data;
	}

	public ProductInfo toProductInfo() {
		if (CollectionUtils.isEmpty(imgPathList)) {
			imgPathList.add(ServerConstants.PRODUCT_DEFAULT_PATH);
		}
		if (StringUtils.isBlank(defaultImg)) {
			defaultImg = imgPathList.get(0);
		}
		ProductInfo data = ProductInfo.newInstance();
		BeanUtils.copyProperties(this, data, "cost", "descript", "imgPathList");
		data.setId(String.valueOf(index));
		return data;
	}

	public ProductDetail toProductDetail(long storeId) {
		if (CollectionUtils.isEmpty(imgPathList)) {
			imgPathList.add(ServerConstants.PRODUCT_DEFAULT_PATH);
		}
		if (StringUtils.isBlank(defaultImg)) {
			defaultImg = imgPathList.get(0);
		}
		ProductDetail data = ProductDetail.newInstance();
		BeanUtils.copyProperties(this, data, "id");
		data.setId(AppUtils.joinByUnderline(storeId, this.index));
		data.setStoreId(storeId);
		data.setProductId(String.valueOf(this.index));
		return data;
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
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

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public List<String> getImgPathList() {
		return imgPathList;
	}

	public void setImgPathList(List<String> imgPathList) {
		this.imgPathList = imgPathList;
	}

	public String getDefaultImg() {
		return defaultImg;
	}

	public void setDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeId() {
		return typeId;
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

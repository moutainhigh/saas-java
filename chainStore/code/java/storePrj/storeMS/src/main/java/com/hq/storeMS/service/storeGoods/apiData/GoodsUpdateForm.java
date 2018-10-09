package com.hq.storeMS.service.storeGoods.apiData;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import com.hq.storeMS.service.goodsDetail.data.GoodsDetail;
import com.hq.storeMS.service.storeGoods.data.Goods;

public class GoodsUpdateForm {
	private String id;
	// 名称
	private String name;
	// 商品分类Id
	private String typeId;
	// 编号
	private String number;
	// 售价
	private float price;
	// 状态 对应GoodsStateEnum
	private int state;
	// 默认图片
	private String defaultImg;
	// 促销标识 PromotionFlagEnum
	private int promotionFlag;
	// 促销价格
	private float promotionPrice;

	// 成本
	private float cost;
	// 描述
	private String descript;
	// 图片列表
	private List<String> imgPaths = new ArrayList<String>();

	public static GoodsUpdateForm newInstance() {
		GoodsUpdateForm data = new GoodsUpdateForm();
		return data;
	}

	private void init() {
		if (CollectionUtils.isNotEmpty(this.imgPaths)) {
			this.defaultImg = this.imgPaths.get(0);
		}
	}

	public void updateGoods(Goods data) {
		init();
		BeanUtils.copyProperties(this, data, "id", "cost", "descript", "imgPaths");
	}

	public void updateGoodsDetail(GoodsDetail detail) {
		init();
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setState(int state) {
		this.state = state;
	}

	public List<String> getImgPaths() {
		return imgPaths;
	}

	public void setImgPaths(List<String> imgPaths) {
		this.imgPaths = imgPaths;
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

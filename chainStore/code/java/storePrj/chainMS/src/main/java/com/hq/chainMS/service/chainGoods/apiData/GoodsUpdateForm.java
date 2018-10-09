package com.hq.chainMS.service.chainGoods.apiData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.hq.chainMS.service.chainGoods.data.Goods;
import com.hq.chainMS.service.chainGoods.data.GoodsDetail;

public class GoodsUpdateForm {
	private String id;
	// 名称
	private String name;
	// 商品分类Id
	private String typeId;
	// 编号
	private String number;
	// 售价
	private float sellPrice;
	// 状态 对应 GoodsStateEnum
	private int state;
	// 默认图片
	private String defaultImg;
	// 适用门店
	private Set<Long> applyStoreIds = new HashSet<Long>();

	// 成本
	private float cost;
	// 描述
	private String descript;
	// 图片列表
	private List<String> imgPaths = new ArrayList<String>();

	public static GoodsUpdateForm newInstance() {
		return new GoodsUpdateForm();
	}

	public void updateGoods(Goods data) {
		BeanUtils.copyProperties(this, data);
	}

	public void updateGoodsDetail(GoodsDetail detail) {
		BeanUtils.copyProperties(this, detail);
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
}

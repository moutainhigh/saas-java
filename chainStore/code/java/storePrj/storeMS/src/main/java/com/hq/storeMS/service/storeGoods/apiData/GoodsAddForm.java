package com.hq.storeMS.service.storeGoods.apiData;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.goodsDetail.data.GoodsDetail;
import com.hq.storeMS.service.storeGoods.data.Goods;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class GoodsAddForm {
	private long index;
	// 商品编号
	private String number;
	// 名称
	private String name;
	// 商品分类Id
	private String typeId;
	// 售价
	private float price;
	// 状态 对应 GoodsStateEnum
	private int state;
	//默认图片
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

	public static GoodsAddForm newInstance() {
		GoodsAddForm data = new GoodsAddForm();
		return data;
	}
	
	private void init() {
		if (CollectionUtils.isEmpty(this.imgPaths)) {
			this.imgPaths.add(ServerConstants.GOODS_DEFAULT_PATH);
		}
		if(StringUtils.isBlank(defaultImg)) {
			this.defaultImg = this.imgPaths.get(0);
		}
	}

	public Goods toGoods() {
		init();
		Goods data = Goods.newInstance();
		BeanUtils.copyProperties(this, data, "cost", "descript", "imgPaths");
		data.setId(String.valueOf(index));
		return data;
	}
	
	public GoodsDetail toGoodsDetail(long storeId) {
		init();
		GoodsDetail data = GoodsDetail.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		data.setId(AppUtils.joinByUnderline(storeId, this.index));
		data.setGoodsId(String.valueOf(this.index));
		data.setStoreId(storeId);
		return data;
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

	public long getIndex() {
		return index;
	}

	public void setIndex(long index) {
		this.index = index;
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

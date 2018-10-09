package com.hq.storeMS.service.sellItem.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Table;

import com.hq.storeMS.service.goodsDetail.data.GoodsDetail;
import com.hq.storeMS.service.packageProjectDetail.data.PackageProjectDetail;
import com.hq.storeMS.service.productCardDetail.data.ProductCardDetail;
import com.hq.storeMS.service.productDetail.data.ProductDetail;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "sellItem")
public class SellItem {
	// 产品类型 对应 SellItemTypeEnum
	private int sellItemType;
	// 商品、项目、套餐、次卡 简版信息的ID
	private String id;
	// 编号
	private String number;
	// 名称
	private String name;
	// 分类Id
	private String typeId;
	// 状态
	private int state;
	// 售价
	private float sellPrice;
	// 默认图片
	private String defaultImg;
	// 描述
	private String descript;
	// 图片列表
	private List<String> imgPaths = new ArrayList<String>();

	public static SellItem newInstance() {
		SellItem data = new SellItem();
		return data;
	}
	
	public static SellItem newInstanceByGoodsDetail(GoodsDetail detail) {
		if(detail == null) {
			return null;
		}
		SellItem data = newInstance();
		FastBeanCopyer.getInstance().copy(detail, data);
		data.setSellItemType(SellItemTypeEnum.GOODS.ordinal());
		data.setId(detail.getGoodsId());
		data.setSellPrice(detail.getPrice());
		return data;
	}
	
	public static SellItem newInstanceByProductDetail(ProductDetail detail) {
		if(detail == null) {
			return null;
		}
		SellItem data = newInstance();
		FastBeanCopyer.getInstance().copy(detail, data);
		data.setSellItemType(SellItemTypeEnum.PRODUCT.ordinal());
		data.setId(detail.getProductId());
		data.setSellPrice(detail.getPrice());
		data.setImgPaths(detail.getImgPathList());
		return data;
	}
	
	public static SellItem newInstanceByProductCardDetail(ProductCardDetail detail) {
		if(detail == null) {
			return null;
		}
		SellItem data = newInstance();
		FastBeanCopyer.getInstance().copy(detail, data);
		data.setSellItemType(SellItemTypeEnum.PRDCARD.ordinal());
		data.setState(detail.getStatus());
		return data;
	}
	
	public static SellItem newInstanceByPackageProjectDetail(PackageProjectDetail detail) {
		if(detail == null) {
			return null;
		}
		SellItem data = newInstance();
		FastBeanCopyer.getInstance().copy(detail, data);
		data.setSellItemType(SellItemTypeEnum.PACKAGE.ordinal());
		return data;
	}

	public int getSellItemType() {
		return sellItemType;
	}

	public void setSellItemType(int sellItemType) {
		this.sellItemType = sellItemType;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public float getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
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

}

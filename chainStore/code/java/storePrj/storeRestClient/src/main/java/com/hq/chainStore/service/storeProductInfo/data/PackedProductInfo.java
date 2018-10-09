package com.hq.chainStore.service.storeProductInfo.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Deprecated 版本迭代，打包项目功能已经摒弃，相关实体类都已失效
 */
@Deprecated
public class PackedProductInfo {
	
	private long id;

	private String name;
	
	//对应所有打包项目id的set集合
	private Set<Integer> productIdSet = new HashSet<Integer>();
	
	//对应原价格 所有打包项目价格的总和
	private float price;
	
	//对应折后价格
	private float salePrice;
	
	private String descript;
	
	//对应上下架状态
	private int state;
	
	//对应是否置顶  默认为false不置顶
	private boolean topFlag = false;
	
	private List<String> imgPathList = new ArrayList<String>();
	
	//评分
	private float score;
	//订单数量
	private long orderCount;
	
	public static PackedProductInfo newInstance(){
		PackedProductInfo info = new PackedProductInfo();
		info.score = 0f;
		info.orderCount = 0L;
		return info;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Integer> getProductIdSet() {
		return productIdSet;
	}

	public void setProductIdSet(Set<Integer> productIdSet) {
		this.productIdSet = productIdSet;
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

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	/*public PackedProductInfoState getStateEnum() {
		return PackedProductInfoState.valueOf(state);
	}

	public void setStateEnum(PackedProductInfoState state) {
		this.state = state.ordinal();
	}*/

	public boolean isTopFlag() {
		return topFlag;
	}

	public void setTopFlag(boolean topFlag) {
		this.topFlag = topFlag;
	}

	public List<String> getImgPathList() {
		return imgPathList;
	}

	public void setImgPathList(List<String> imgPathList) {
		this.imgPathList = imgPathList;
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
	
}

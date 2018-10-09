package com.hq.chainStore.service.storeProductInfo.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.common.dataSyn.bs.IntfSynData;

@Table(name = "storeProductInfo")
public class StoreProductInfo implements IntfSynData {
	@Id
    private long id;
    private long storeId;

    private int productIdIndex = 0;
    private int productTypeIdIndex = 0;
    private Map<String, ProductInfo> productInfoMap = new HashMap<String, ProductInfo>();
    private Map<String, ProductType> productTypeMap = new HashMap<String, ProductType>();// 项目分类

    // 数据拆分标识
    private int splitMark;
    private long createdTime;
    private long lastUpdateTime;
    private long ver;

	// 对应置顶项目id集合 对全局生效
	private List<String> topProductIdList = new ArrayList<String>();

	public static StoreProductInfo newInstance() {
		StoreProductInfo storeProductInfo = new StoreProductInfo();
		return storeProductInfo;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
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

	public void incrVer() {
		this.ver = ver + 1;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Map<String, ProductInfo> getProductInfoMap() {
		return productInfoMap;
	}

	public void setProductInfoMap(Map<String, ProductInfo> productInfoMap) {
		this.productInfoMap = productInfoMap;
	}

	public int getProductIdIndex() {
		return productIdIndex;
	}

	public void setProductIdIndex(int productIdIndex) {
		this.productIdIndex = productIdIndex;
	}

	public List<String> getTopProductIdList() {
		return topProductIdList;
	}

	public void setTopProductIdList(List<String> topProductIdList) {
		this.topProductIdList = topProductIdList;
	}

	public Map<String, ProductType> getProductTypeMap() {
		return productTypeMap;
	}

	public void setProductTypeMap(Map<String, ProductType> productTypeMap) {
		this.productTypeMap = productTypeMap;
	}

	public int getProductTypeIdIndex() {
		return productTypeIdIndex;
	}

	public void setProductTypeIdIndex(int productTypeIdIndex) {
		this.productTypeIdIndex = productTypeIdIndex;
	}

	@Override
	public Object targetId() {
		return this.id;
	}

	@Override
	public long targetVer() {
		return this.ver;
	}

	public int getSplitMark() {
		return splitMark;
	}

	public void setSplitMark(int splitMark) {
		this.splitMark = splitMark;
	}

}

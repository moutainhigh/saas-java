package com.hq.chainClient.service.chainProduct.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "chainProduct")
public class ChainProduct {
	@Id
	private long id;
	private long chainId;
	
	private long productIdIndex = 0;
	private Map<String, Product> productInfoMap = new HashMap<String, Product>();
	
	private long productTypeIdIndex = 0;
	private Map<String, ProductType> productTypeMap = new HashMap<String, ProductType>();// 项目分类

	// 数据拆分标识
	private int splitMark;
	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static ChainProduct newInstance(long chainId) {
		ChainProduct chainProduct = new ChainProduct();
		return chainProduct;
	}
	
	public Product takeProductById(String id) {
		return productInfoMap.get(id);
	}
	
	public ProductType takeProductTypeById(String id) {
		return productTypeMap.get(id);
	}
	
	public long getChainId() {
		return chainId;
	}

	public void setChainId(long chainId) {
		this.chainId = chainId;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Map<String, Product> getProductInfoMap() {
		return productInfoMap;
	}

	public void setProductInfoMap(Map<String, Product> productInfoMap) {
		this.productInfoMap = productInfoMap;
	}

	public long getProductIdIndex() {
		return productIdIndex;
	}

	public void setProductIdIndex(long productIdIndex) {
		this.productIdIndex = productIdIndex;
	}

	public Map<String, ProductType> getProductTypeMap() {
		return productTypeMap;
	}

	public void setProductTypeMap(Map<String, ProductType> productTypeMap) {
		this.productTypeMap = productTypeMap;
	}

	public long getProductTypeIdIndex() {
		return productTypeIdIndex;
	}

	public void setProductTypeIdIndex(long productTypeIdIndex) {
		this.productTypeIdIndex = productTypeIdIndex;
	}

	public int getSplitMark() {
		return splitMark;
	}

	public void setSplitMark(int splitMark) {
		this.splitMark = splitMark;
	}

}

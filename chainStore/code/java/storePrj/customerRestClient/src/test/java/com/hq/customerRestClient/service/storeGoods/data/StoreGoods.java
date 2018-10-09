package com.hq.customerRestClient.service.storeGoods.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "storeGoods")
public class StoreGoods {
	@Id
	private long id;
	private long storeId;
	
	//商品ID
	private long goodsIdIndex = 0L;
	//商品集合
	private Map<String, Goods> goodsMap = new HashMap<String, Goods>();
	
	//分类ID
	private long goodsTypeIdIndex = 0L;
	// 商品分类
	private Map<String, GoodsType> goodsTypeMap = new HashMap<String, GoodsType>();
	
	// 对应置顶项目id集合 对全局生效
	private List<String> topGoodsIdList = new ArrayList<String>();
	//数据拆分标识
	private int splitMark;

	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static StoreGoods newInstance() {
		StoreGoods data = new StoreGoods();
		return data;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getGoodsIdIndex() {
		return goodsIdIndex;
	}

	public void setGoodsIdIndex(long goodsIdIndex) {
		this.goodsIdIndex = goodsIdIndex;
	}

	public long getGoodsTypeIdIndex() {
		return goodsTypeIdIndex;
	}

	public void setGoodsTypeIdIndex(long goodsTypeIdIndex) {
		this.goodsTypeIdIndex = goodsTypeIdIndex;
	}

	public Map<String, Goods> getGoodsMap() {
		return goodsMap;
	}

	public void setGoodsMap(Map<String, Goods> goodsMap) {
		this.goodsMap = goodsMap;
	}

	public Map<String, GoodsType> getGoodsTypeMap() {
		return goodsTypeMap;
	}

	public void setGoodsTypeMap(Map<String, GoodsType> goodsTypeMap) {
		this.goodsTypeMap = goodsTypeMap;
	}

	public List<String> getTopGoodsIdList() {
		return topGoodsIdList;
	}

	public void setTopGoodsIdList(List<String> topGoodsIdList) {
		this.topGoodsIdList = topGoodsIdList;
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

	public int getSplitMark() {
		return splitMark;
	}

	public void setSplitMark(int splitMark) {
		this.splitMark = splitMark;
	}

}

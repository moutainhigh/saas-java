package com.hq.chainMS.service.chainGoods.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.chainMS.service.sysDataInit.data.SysInitTypeEnum;
import com.zenmind.dataSyn.annotation.SynClass;
import com.zenmind.dataSyn.annotation.SynIgnoreField;

@SynClass
@Table(name = "chainGoods")
public class ChainGoods {
	@Id
	private long id;
	private long chainId;

	// 商品ID
	private long goodsIdIndex = 0L;
	// 商品集合
	private Map<String, Goods> goodsMap = new HashMap<String, Goods>();

	// 分类ID
	private long goodsTypeIdIndex = 0L;
	// 商品分类
	private Map<String, GoodsType> goodsTypeMap = new HashMap<String, GoodsType>();
	
	// 数据拆分标识
	@SynIgnoreField
	private int splitMark;

	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static ChainGoods newInstance(long chainIdP) {
		ChainGoods data = new ChainGoods();
		data.id = chainIdP;
		data.chainId = chainIdP;

		GoodsType type = GoodsType.newInstance();
		String typeId = SysInitTypeEnum.UnClassify.getId();
		type.setId(GoodsType.generateId(chainIdP, Long.valueOf(typeId)));
		type.setName(SysInitTypeEnum.UnClassify.getName());
		data.goodsTypeMap.put(type.getId(), type);

		long curTime = System.currentTimeMillis();
		data.createdTime = curTime;
		data.lastUpdateTime = curTime;
		return data;
	}
	
	public Goods takeGoodsById(String id) {
		return goodsMap.get(id);
	}
	
	public GoodsType takeGoodsTypeById(String id) {
		return goodsTypeMap.get(id);
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

	public long getChainId() {
		return chainId;
	}

	public void setChainId(long chainId) {
		this.chainId = chainId;
	}

}

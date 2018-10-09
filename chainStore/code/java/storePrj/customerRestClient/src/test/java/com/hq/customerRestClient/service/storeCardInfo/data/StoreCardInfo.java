package com.hq.customerRestClient.service.storeCardInfo.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "storeCardInfo")
public class StoreCardInfo {
	@Id
	private long id;
	private long storeId;

	private int membershipCardIndex = 0;
	private Map<String, MembershipCard> membershipCardMap = new HashMap<String, MembershipCard>();
	
	private int productCardIndex = 0;
	private Map<String, ProductCard> productCardMap = new HashMap<String, ProductCard>();

	// 次卡分类
	private long prdCardTypeIndex = 0L;
	private Map<String, PrdCardType> prdCardTypeMap = new HashMap<String, PrdCardType>();

	// 数据拆分标识
	private int splitMark;
	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static StoreCardInfo newInstance() {
		StoreCardInfo storeCardInfo = new StoreCardInfo();
		return storeCardInfo;
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

	public int getMembershipCardIndex() {
		return membershipCardIndex;
	}

	public void setMembershipCardIndex(int membershipCardIndex) {
		this.membershipCardIndex = membershipCardIndex;
	}

	public Map<String, MembershipCard> getMembershipCardMap() {
		return membershipCardMap;
	}

	public void setMembershipCardMap(Map<String, MembershipCard> membershipCardMap) {
		this.membershipCardMap = membershipCardMap;
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

	public int getProductCardIndex() {
		return productCardIndex;
	}

	public void setProductCardIndex(int productCardIndex) {
		this.productCardIndex = productCardIndex;
	}

	public Map<String, ProductCard> getProductCardMap() {
		return productCardMap;
	}

	public void setProductCardMap(Map<String, ProductCard> productCardMap) {
		this.productCardMap = productCardMap;
	}

	public int getSplitMark() {
		return splitMark;
	}

	public void setSplitMark(int splitMark) {
		this.splitMark = splitMark;
	}

	public long getPrdCardTypeIndex() {
		return prdCardTypeIndex;
	}

	public void setPrdCardTypeIndex(long prdCardTypeIndex) {
		this.prdCardTypeIndex = prdCardTypeIndex;
	}

	public Map<String, PrdCardType> getPrdCardTypeMap() {
		return prdCardTypeMap;
	}

	public void setPrdCardTypeMap(Map<String, PrdCardType> prdCardTypeMap) {
		this.prdCardTypeMap = prdCardTypeMap;
	}

}

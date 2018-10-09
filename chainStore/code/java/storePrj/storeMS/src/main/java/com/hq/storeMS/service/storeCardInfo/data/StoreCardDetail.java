package com.hq.storeMS.service.storeCardInfo.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.hq.storeMS.service.membershipCardDetail.data.MembershipCardDetail;
import com.hq.storeMS.service.productCardDetail.data.ProductCardDetail;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class StoreCardDetail {
	private long id;
	private long storeId;

	private int membershipCardIndex = 0;
	private int productCardIndex = 0;

	private Map<String, MembershipCardDetail> membershipCardMap = new HashMap<String, MembershipCardDetail>();
	private Map<String, ProductCardDetail> productCardMap = new HashMap<String, ProductCardDetail>();

	private int splitMark;
	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static StoreCardDetail newInstance(long storeId) {
		StoreCardDetail storeCardInfo = new StoreCardDetail();
		storeCardInfo.id = storeId;
		storeCardInfo.storeId = storeId;
		return storeCardInfo;
	}
	
	public static StoreCardDetail newInstanceByStoreCardInfo(StoreCardInfo data) {
		StoreCardDetail result = new StoreCardDetail();
		BeanUtils.copyProperties(data, result, "membershipCardMap", "productCardMap");
		return result;
	}
	
	public void addMembershipCardDetails(List<MembershipCardDetail> details) {
		for (MembershipCardDetail detail : details) {
			this.membershipCardMap.put(detail.getId(), detail);
		}
	}
	
	public void addProductCardDetails(List<ProductCardDetail> details) {
		for (ProductCardDetail detail : details) {
			this.productCardMap.put(detail.getId(), detail);
		}
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

	public int getProductCardIndex() {
		return productCardIndex;
	}

	public void setProductCardIndex(int productCardIndex) {
		this.productCardIndex = productCardIndex;
	}

	public Map<String, MembershipCardDetail> getMembershipCardMap() {
		return membershipCardMap;
	}

	public void setMembershipCardMap(Map<String, MembershipCardDetail> membershipCardMap) {
		this.membershipCardMap = membershipCardMap;
	}

	public Map<String, ProductCardDetail> getProductCardMap() {
		return productCardMap;
	}

	public void setProductCardMap(Map<String, ProductCardDetail> productCardMap) {
		this.productCardMap = productCardMap;
	}

	public int getSplitMark() {
		return splitMark;
	}

	public void setSplitMark(int splitMark) {
		this.splitMark = splitMark;
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

}

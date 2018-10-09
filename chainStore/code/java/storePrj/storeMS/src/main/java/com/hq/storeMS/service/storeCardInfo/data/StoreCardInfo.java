package com.hq.storeMS.service.storeCardInfo.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.storeMS.service.common.DataOriginEnum;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.common.SplitMarkEnum;
import com.hq.storeMS.service.sysDataInit.data.SysInitTypeEnum;
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
		StoreCardInfo data = new StoreCardInfo();
		long curTime = System.currentTimeMillis();
		data.createdTime = curTime;
		data.lastUpdateTime = curTime;
		return data;
	}
	
	public static StoreCardInfo newInstance(long storeId) {
		StoreCardInfo storeCardInfo = newInstance();
		storeCardInfo.id = storeId;
		storeCardInfo.storeId = storeId;
		storeCardInfo.splitMark = SplitMarkEnum.FINISH_LEVEL2.ordinal();
		PrdCardType type = PrdCardType.newInstance();
		type.setId(SysInitTypeEnum.UnClassify.getId());
		type.setName(SysInitTypeEnum.UnClassify.getName());
		storeCardInfo.prdCardTypeMap.put(type.getId(), type);
		return storeCardInfo;
	}

	public void incrVer() {
		this.ver = ver + 1;
	}

	public ProductCard takeProductCardById(String productCardId) {
		return this.productCardMap.get(productCardId);
	}

	public MembershipCard takeMembershipCardById(String membershipCardId) {
		return this.membershipCardMap.get(membershipCardId);
	}
	
	public PrdCardType takeProductCardTypeById(String productCardTypeId) {
		return this.prdCardTypeMap.get(productCardTypeId);
	}
	
	public boolean memberCardIsFromChain(String memberCardId) {
		MembershipCard card = membershipCardMap.get(memberCardId);
		if(card != null && card.getOrigin() == DataOriginEnum.CHAIN.ordinal()) {
			return true;
		}
		return false;
	}
	
	public boolean prdCardIsFromChain(String prdCardId) {
		ProductCard card = productCardMap.get(prdCardId);
		if(card != null && card.getOrigin() == DataOriginEnum.CHAIN.ordinal()) {
			return true;
		}
		return false;
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

	/**
	 * 获取正常状态 已上架的ProductCard
	 *
	 * @return
	 */
	public Map<String, ProductCard> takeNormalProductCardMap() {
		Map<String, ProductCard> tempCardMap = new HashMap<>(productCardMap);
		Set<String> set = tempCardMap.keySet();
		Iterator<String> it = set.iterator();
		List<String> listKey = new ArrayList<String>();
		while (it.hasNext()) {
			String str = it.next();
			ProductCard productCard = tempCardMap.get(str);
			if (productCard != null && productCard.getStatus() != CardStatusEnum.OPEN.ordinal() && productCard.getEntityState() != EntityState.Normal.ordinal()) {
				listKey.add(str);
			}
		}
		for (String key : listKey) {
			tempCardMap.remove(key);
		}
		return tempCardMap;
	}

}

package com.hq.chainMS.service.chainCard.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.chainMS.service.sysDataInit.data.SysInitTypeEnum;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "chainCard")
public class ChainCard {
	@Id
	private long id;
	private long chainId;

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

	public static ChainCard newInstance(long chainId) {
		ChainCard chainCard = new ChainCard();
		chainCard.id = chainId;
		chainCard.chainId = chainId;

		PrdCardType type = PrdCardType.newInstance();
		String typeId = SysInitTypeEnum.UnClassify.getId();
		type.setId(PrdCardType.generateId(chainId, Long.valueOf(typeId)));
		type.setName(SysInitTypeEnum.UnClassify.getName());
		chainCard.prdCardTypeMap.put(type.getId(), type);

		long curTime = System.currentTimeMillis();
		chainCard.createdTime = curTime;
		chainCard.lastUpdateTime = curTime;
		return chainCard;
	}

	public void incrVer() {
		this.ver = ver + 1;
	}

	public ProductCard takeProductCardById(String productCardId) {
		return productCardMap.get(productCardId);
	}

	public MembershipCard takeMembershipCardById(String membershipCardId) {
		return membershipCardMap.get(membershipCardId);
	}
	
	public PrdCardType takePrdCardTypeById(String id) {
		return prdCardTypeMap.get(id);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getChainId() {
		return chainId;
	}

	public void setChainId(long chainId) {
		this.chainId = chainId;
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

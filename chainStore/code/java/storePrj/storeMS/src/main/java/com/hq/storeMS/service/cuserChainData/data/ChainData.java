package com.hq.storeMS.service.cuserChainData.data;

import java.util.HashMap;
import java.util.Map;

import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerMemberCard;
import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerProductCard;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class ChainData {
	// 连锁店Id
	private long chainId;
	// 连锁店会员卡
	private LeaguerMemberCard chainMemberCard = new LeaguerMemberCard();
	// 连锁店次卡
	private Map<String, LeaguerProductCard> chainPrdCardMap = new HashMap<String, LeaguerProductCard>();
	// 连锁店次卡ID
	private int chainPrdCardIndex = 1;

	public static ChainData newInstance() {
		ChainData data = new ChainData();
		return data;
	}
	
	public LeaguerProductCard takeLeaguerProductCard(String leaguerPrdCardId) {
		return chainPrdCardMap.get(leaguerPrdCardId);
	}

	public long getChainId() {
		return chainId;
	}

	public void setChainId(long chainId) {
		this.chainId = chainId;
	}

	public LeaguerMemberCard getChainMemberCard() {
		return chainMemberCard;
	}

	public void setChainMemberCard(LeaguerMemberCard chainMemberCard) {
		this.chainMemberCard = chainMemberCard;
	}

	public Map<String, LeaguerProductCard> getChainPrdCardMap() {
		return chainPrdCardMap;
	}

	public void setChainPrdCardMap(Map<String, LeaguerProductCard> chainPrdCardMap) {
		this.chainPrdCardMap = chainPrdCardMap;
	}

	public int getChainPrdCardIndex() {
		return chainPrdCardIndex;
	}

	public void setChainPrdCardIndex(int chainPrdCardIndex) {
		this.chainPrdCardIndex = chainPrdCardIndex;
	}

}

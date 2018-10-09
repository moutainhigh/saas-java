package com.hq.storeMS.service.cuserChainData.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerMemberCard;
import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerProductCard;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "cuserChainData")
public class CuserChainData {
	// cuserId
	@Id
	private long id;
	//{chainId:chainData}
	private Map<Long, ChainData> chainDataMap = new HashMap<Long, ChainData>();

	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static CuserChainData newInstance(long cuserId) {
		CuserChainData data = new CuserChainData();
		data.id = cuserId;
		long curTime = System.currentTimeMillis();
		data.createdTime = curTime;
		data.lastUpdateTime = curTime;
		return data;
	}
	
	public ChainData takeChainData(Long chainId) {
		return chainDataMap.get(chainId);
	}
	
	//获取连锁店的次卡信息
	public LeaguerProductCard takeChainLeaguerProductCard(long chainId, String leaguerPrdCardId) {
		ChainData chainData = chainDataMap.get(chainId);
		if(chainData == null) {
			return null;
		}
		return chainData.takeLeaguerProductCard(leaguerPrdCardId);
	}
	
	//获取连锁店的会员卡信息
	public LeaguerMemberCard takeLeaguerMemberCard(Long chainId) {
		ChainData chainData = chainDataMap.get(chainId);
		if(chainData == null) {
			return null;
		}
		return chainData.getChainMemberCard();
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

	public Map<Long, ChainData> getChainDataMap() {
		return chainDataMap;
	}

	public void setChainDataMap(Map<Long, ChainData> chainDataMap) {
		this.chainDataMap = chainDataMap;
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

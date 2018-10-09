package com.hq.storeMS.service.leaguerAffair.bs;

import com.hq.storeMS.service.leaguerAffair.data.LeaguerAffair;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerAffairMgr {

	public static LeaguerAffairMgr getInstance(){
		return HotSwap.getInstance().getSingleton(LeaguerAffairMgr.class);
	}
	
	public void addWithId(LeaguerAffair target) {
		LeaguerAffairDataHolder.getInstance().addWithId(target);
	}
	
	public void update(LeaguerAffair target) {
		LeaguerAffairDataHolder.getInstance().update(target);
	}
	
	public void delete(String id) {
		LeaguerAffairDataHolder.getInstance().delete(id);
	}
	
	public LeaguerAffair get(String id){
		LeaguerAffair leaguerAffair = LeaguerAffairDataHolder.getInstance().get(id);
		if(leaguerAffair == null){//不存在则添加
			String[] ids = id.split("_");
			leaguerAffair = LeaguerAffair.newInstance();
			leaguerAffair.setId(id);
			leaguerAffair.setLeaguerId(id);
			leaguerAffair.setStoreId(Long.valueOf(ids[0]));
			LeaguerAffairDataHolder.getInstance().addWithId(leaguerAffair);
		}
		return leaguerAffair;
	}
	
}

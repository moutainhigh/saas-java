package com.hq.storeMS.service.storeLeaguerInfo.bs;

import java.util.Map;

import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.leaguerDetail.bs.LeaguerDetailMgr;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.storeLeaguerInfo.data.Leaguer;
import com.hq.storeMS.service.storeLeaguerInfo.data.OutsiderEnum;
import com.hq.storeMS.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreLeaguerInfoMgr {

	public static StoreLeaguerInfoMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreLeaguerInfoMgr.class);
	}

	public StoreLeaguerInfo get(long id) {
		StoreLeaguerInfo storeLeaguerInfo = StoreLeaguerInfoDataHolder.getInstance().get(id);
		if(storeLeaguerInfo == null){//不存在，则新增
			storeLeaguerInfo = StoreLeaguerInfo.newInstance(id);
			StoreLeaguerInfoDataHolder.getInstance().addWithId(storeLeaguerInfo);
			addOutsiderLeaguer(storeLeaguerInfo);
		}
		return storeLeaguerInfo;
	}
	
	//添加散客男女
	public void addOutsiderLeaguer(StoreLeaguerInfo storeData) {
		long storeId = storeData.getStoreId();
		OutsiderEnum[] values = OutsiderEnum.values();
		for (OutsiderEnum outsiderEnum : values) {
			String id = outsiderEnum.getId(storeId);
			Map<String, Leaguer> leaguerMap = storeData.getLeaguerInfoMap();
			Leaguer data = leaguerMap.get(id);
			if(data == null) {
				data = Leaguer.newInstance();
				data.setId(id);
				leaguerMap.put(data.getId(), data);
			}
			data.setName(outsiderEnum.getName());
			data.setSex(outsiderEnum.getSex());
			data.setEntityState(EntityState.Deleted.ordinal());
			data.setHeadImg(outsiderEnum.getHeadImg());
			StoreLeaguerInfoMgr.getInstance().updStoreLeaguerInfo(storeData);
			
			boolean addFlag = false;
			LeaguerDetail target = LeaguerDetailMgr.getInstance().getSimple(storeId, id);
			if(target == null) {
				target = LeaguerDetail.newInstance();
				target.setId(id);
				addFlag=true;
			}
			target.setName(outsiderEnum.getName());
			target.setSex(outsiderEnum.getSex());
			target.setEntityState(EntityState.Deleted.ordinal());
			target.setHeadImg(outsiderEnum.getHeadImg());
			if(addFlag) {
				LeaguerDetailMgr.getInstance().addWithId(target);
			}else {
				LeaguerDetailMgr.getInstance().updateSimple(target);
			}
		}
	}
	
	public void addStoreLeaguerInfo(StoreLeaguerInfo target) {
		StoreLeaguerInfoDataHolder.getInstance().addWithId(target);
	}
	
	public void updStoreLeaguerInfo(StoreLeaguerInfo target){
		StoreLeaguerInfoDataHolder.getInstance().updpate(target);
	}
}

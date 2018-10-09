package com.hq.storeMS.service.storeBeauticianInfo.bs;

import org.apache.commons.lang3.math.NumberUtils;

import com.hq.storeMS.common.datasyn.IntfDataHolder;
import com.hq.storeMS.common.datasyn.info.DataSynItem;
import com.hq.storeMS.common.datasyn.info.DataSynType;
import com.hq.storeMS.common.datasyn.info.DataSynVer;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.storeBeauticianInfo.data.StoreBeauticianInfo;
import com.hq.storeMS.service.storeBeauticianInfo.data.StoreBeauticianInfoCacheDAO;
import com.hq.storeMS.service.storeBeauticianInfo.data.StoreBeauticianInfoDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class StoreBeauticianInfoDataHolder implements IntfDataHolder{
	
	public static StoreBeauticianInfoDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(StoreBeauticianInfoDataHolder.class);
	}
	
	final private DataSynType synType = DataSynType.StoreBeauticianInfo;
	
	public void addWithId(StoreBeauticianInfo target) {
		StoreBeauticianInfoDAO.getInstance().addWithId(target);
	}
	

	public void update(StoreBeauticianInfo target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		StoreBeauticianInfoDAO.getInstance().updpate(target);
		StoreBeauticianInfoCacheDAO.getInstance().delete(target);
	}
	
	public void delete(StoreBeauticianInfo target) {
		StoreBeauticianInfoDAO.getInstance().delete(target.getId());
		StoreBeauticianInfoCacheDAO.getInstance().delete(target);
	}

	public StoreBeauticianInfo get(long id) {
		StoreBeauticianInfo target = StoreBeauticianInfoCacheDAO.getInstance().get(id);
		if(target == null){
			target = StoreBeauticianInfoDAO.getInstance().get(id);
			if(target != null){
				StoreBeauticianInfoCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public DataSynType getSynType() {
		return synType;
	}

	public DataSynItem getSynItem(DataSynVer clientSynVer){
		DataSynItem item = null;
		String id = clientSynVer.getId();
		
		if(NumberUtils.isNumber(id )){
			StoreBeauticianInfo target = this.get(Long.valueOf(id));			
			if(target != null){
				long newVer = target.getVer();
				if(clientSynVer.getVer() < newVer){
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data );
				}
			}else{
				MainLog.info(LogModule.StoreBeauticianInfo, "StoreBeauticianInfoDataHolder[getSynItem]", "获取详情对象为空[id="+id+"]");
			}
		}else{
			MainLog.info(LogModule.StoreBeauticianInfo, "StoreBeauticianInfoDataHolder[getSynItem]", "数据同步失败[id="+id+"]");
		}
		
		return item;
	}
	
}

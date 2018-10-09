package com.hq.storeMS.service.store.bs;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeMS.common.datasyn.IntfDataHolder;
import com.hq.storeMS.common.datasyn.info.DataSynItem;
import com.hq.storeMS.common.datasyn.info.DataSynType;
import com.hq.storeMS.common.datasyn.info.DataSynVer;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.saas.apiData.OPStoreQueryApiForm;
import com.hq.storeMS.service.store.apiData.StoreQueryForm;
import com.hq.storeMS.service.store.data.Store;
import com.hq.storeMS.service.store.data.StoreCacheDAO;
import com.hq.storeMS.service.store.data.StoreDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class StoreDataHolder implements IntfDataHolder{
	
	public static StoreDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(StoreDataHolder.class);
	}
	
	final private DataSynType synType = DataSynType.Store;
	
	public void addAndReturnId(Store target) {
		StoreDAO.getInstance().addAndReturnId(target);
	}

	public void update(Store target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		StoreDAO.getInstance().updpate(target);
		StoreCacheDAO.getInstance().delete(target);
	}
	
	public void delete(Store target) {
		StoreDAO.getInstance().delete(target.getId());
		StoreCacheDAO.getInstance().delete(target);
	}

	public Store get(long id) {
		Store target = StoreCacheDAO.getInstance().get(id);
		if(target == null){
			target = StoreDAO.getInstance().get(id);
			if(target != null){
				StoreCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public List<Store> findPage(int pageItemCount, int pageNo) {
		return StoreDAO.getInstance().findByPage(pageItemCount, pageNo);
	}
	
	//谨慎使用 只提供给系统启动时，对数据库进行数据割接的时候用到，其他业务不能使用改方法 -- add by kevin  2018-5-29
	public long allStoreCount() {
		return StoreDAO.getInstance().count(new Query());
	}
	
	//谨慎使用 只提供给系统启动时，对数据库进行数据割接的时候用到，其他业务不能使用改方法-- add by kevin  2018-5-29
	public List<Store> findAll() {
		return StoreDAO.getInstance().find(new Query());
	}
	
	public List<Store> findByIdList(Collection<Long> storeIdSet,int pageItemCount,int pageNo) {
		return StoreDAO.getInstance().findByIdList(storeIdSet,pageItemCount,pageNo);
	}
	
	public List<Store> findByName(String name, int pageItemCount, int pageNo) {
		return StoreDAO.getInstance().findByName(name, pageItemCount, pageNo);
	}
	
	public List<Store> findStoreList(OPStoreQueryApiForm queryForm) {
		return StoreDAO.getInstance().findStoreList(queryForm);
	}
	
	public List<Store> findStoreByCond(StoreQueryForm queryForm) {
		return StoreDAO.getInstance().findStoreByCond(queryForm);
	}
	
	public DataSynType getSynType() {
		return synType;
	}

	public DataSynItem getSynItem(DataSynVer clientSynVer){
		DataSynItem item = null;
		String id = clientSynVer.getId();
		
		if(NumberUtils.isNumber(id )){
			long idL = Long.valueOf(id);
			Store target = this.get(idL);			
			if(target != null){
				long newVer = target.getVer();
				if(clientSynVer.getVer() < newVer){
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data );
				}
			}else{
				MainLog.info(LogModule.Store, "StoreDataHolder[getSynItem]", "获取详情对象为空[id="+id+"]");
			}
		}else{
			MainLog.info(LogModule.Store, "StoreDataHolder[getSynItem]", "数据同步失败[id="+id+"]");
		}
		return item;
	}
	
}

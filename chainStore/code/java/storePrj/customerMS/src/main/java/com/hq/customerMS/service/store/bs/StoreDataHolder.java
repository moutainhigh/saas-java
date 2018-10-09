package com.hq.customerMS.service.store.bs;

import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import com.hq.customerMS.common.constants.ServerConstants;
import com.hq.customerMS.common.datasyn.IntfDataHolder;
import com.hq.customerMS.common.datasyn.info.DataSynItem;
import com.hq.customerMS.common.datasyn.info.DataSynType;
import com.hq.customerMS.common.datasyn.info.DataSynVer;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.common.log.MainLog;
import com.hq.customerMS.common.validate.AppIdThreadLocal;
import com.hq.customerMS.service.store.data.StoreCacheDAO;
import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.store.apiData.JoinStoreForm;
import com.hq.storeClient.service.store.apiData.StoreQueryForm;
import com.hq.storeClient.service.store.bs.StoreClientMgr;
import com.hq.storeClient.service.store.data.Store;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class StoreDataHolder implements IntfDataHolder {

	public static StoreDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(StoreDataHolder.class);
	}

	final private DataSynType synType = DataSynType.Store;

	public Store get(long id) {
		Store data = StoreCacheDAO.getInstance().get(id);
		if(data!=null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = StoreClientMgr.getInstance().get(id);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

	public PageResp<Store> findStoreByCond(StoreQueryForm queryForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		PageResp<Store> data = StoreClientMgr.getInstance().findStoreByCond(queryForm);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

	public void joinStore(JoinStoreForm joinStoreForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		StoreClientMgr.getInstance().joinStoreForCuser(joinStoreForm);
		AppIdThreadLocal.getInstance().set(null);
	}
	
	public List<Store> findMyStores(long cuserId){
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		List<Store> data = StoreClientMgr.getInstance().findByCuser(cuserId);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	

	public DataSynType getSynType() {
		return synType;
	}

	public DataSynItem getSynItem(DataSynVer clientSynVer) {
		DataSynItem item = null;
		String id = clientSynVer.getId();

		if (NumberUtils.isNumber(id)) {
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
		} else {
			MainLog.info(LogModule.Store, "StoreDataHolder[getSynItem]", "数据同步失败[id=" + id + "]");
		}
		return item;
	}

}

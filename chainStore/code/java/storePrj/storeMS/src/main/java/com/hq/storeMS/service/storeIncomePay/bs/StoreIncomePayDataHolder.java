package com.hq.storeMS.service.storeIncomePay.bs;

import org.apache.commons.lang3.math.NumberUtils;

import com.hq.storeMS.common.datasyn.IntfDataHolder;
import com.hq.storeMS.common.datasyn.info.DataSynItem;
import com.hq.storeMS.common.datasyn.info.DataSynType;
import com.hq.storeMS.common.datasyn.info.DataSynVer;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.storeIncomePay.data.StoreIncomePay;
import com.hq.storeMS.service.storeIncomePay.data.StoreIncomePayCacheDAO;
import com.hq.storeMS.service.storeIncomePay.data.StoreIncomePayDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class StoreIncomePayDataHolder implements IntfDataHolder{
	
	public static StoreIncomePayDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(StoreIncomePayDataHolder.class);
	}
	
	final private DataSynType synType = DataSynType.StoreIncomePay;
	
	public void addWithId(StoreIncomePay target) {
		StoreIncomePayDAO.getInstance().addWithId(target);
	}

	public void delete(StoreIncomePay target) {
		StoreIncomePayDAO.getInstance().delete(target.getId());
		StoreIncomePayCacheDAO.getInstance().delete(target);
	}
	
	public void update(StoreIncomePay target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		StoreIncomePayDAO.getInstance().updpate(target);
		StoreIncomePayCacheDAO.getInstance().delete(target);
	}

	public StoreIncomePay get(long id) {
		StoreIncomePay target = StoreIncomePayCacheDAO.getInstance().get(id);
		if(target == null){
			target = StoreIncomePayDAO.getInstance().get(id);
			if(target != null){
				StoreIncomePayCacheDAO.getInstance().save(target);
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
		if(NumberUtils.isNumber(id)){
			StoreIncomePay target = this.get(Long.valueOf(id));			
			if(target != null){
				long newVer = target.getVer();
				if(clientSynVer.getVer() < newVer){
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data );
				}
			}else{
				MainLog.info(LogModule.StoreIncomePay, "StoreIncomePayDataHolder[getSynItem]", "获取详情对象为空[id="+id+"]");
			}
		}else{
			MainLog.info(LogModule.StoreIncomePay, "StoreIncomePayDataHolder[getSynItem]", "数据同步失败[id="+id+"]");
		}
		return item;
	}
}

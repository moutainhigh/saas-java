package com.hq.chainMS.service.chainClerk.bs;

import org.apache.commons.lang3.math.NumberUtils;

import com.hq.chainMS.common.datasyn.IntfDataHolder;
import com.hq.chainMS.common.datasyn.info.DataSynItem;
import com.hq.chainMS.common.datasyn.info.DataSynType;
import com.hq.chainMS.common.datasyn.info.DataSynVer;
import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.common.log.MainLog;
import com.hq.chainMS.service.chainClerk.data.ChainClerk;
import com.hq.chainMS.service.chainClerk.data.ChainClerkCacheDAO;
import com.hq.chainMS.service.chainClerk.data.ChainClerkDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class ChainClerkDataHolder implements IntfDataHolder{
	
	public static ChainClerkDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(ChainClerkDataHolder.class);
	}
	
	final private DataSynType synType = DataSynType.ChainClerk;
	
	public void addWithId(ChainClerk target) {
		ChainClerkDAO.getInstance().addWithId(target);
	}

	public void update(ChainClerk target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		ChainClerkDAO.getInstance().updpate(target);
		ChainClerkCacheDAO.getInstance().delete(target);
	}
	
	public void delete(ChainClerk target) {
		ChainClerkDAO.getInstance().delete(target.getId());
		ChainClerkCacheDAO.getInstance().delete(target);
	}

	public ChainClerk get(Object id) {
		ChainClerk target = ChainClerkCacheDAO.getInstance().get(id);
		if(target == null){
			target = ChainClerkDAO.getInstance().get(id);
			if(target != null){
				ChainClerkCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public DataSynType getSynType() {
		return synType;
	}

	public DataSynItem getSynItem(DataSynVer clientSynVer) {
		DataSynItem item = null;
		String id = clientSynVer.getId();
		if (NumberUtils.isNumber(id)) {
			ChainClerk target = this.get(Long.valueOf(id));
			if (target != null) {
				long newVer = target.getVer();
				if (clientSynVer.getVer() < newVer) {
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data);
				}
			} else {
				MainLog.info(LogModule.ChainClerk, "StoreBUserDataHolder[getSynItem]", "获取详情对象为空[id=" + id + "]");
			}
		} else {
			MainLog.info(LogModule.ChainClerk, "StoreBUserDataHolder[getSynItem]", "数据同步失败[id=" + id + "]");
		}
		return item;
	}

}

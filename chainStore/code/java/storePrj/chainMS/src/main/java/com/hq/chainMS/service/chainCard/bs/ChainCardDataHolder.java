package com.hq.chainMS.service.chainCard.bs;

import org.apache.commons.lang3.math.NumberUtils;

import com.hq.chainMS.common.datasyn.IntfDataHolder;
import com.hq.chainMS.common.datasyn.info.DataSynItem;
import com.hq.chainMS.common.datasyn.info.DataSynType;
import com.hq.chainMS.common.datasyn.info.DataSynVer;
import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.common.log.MainLog;
import com.hq.chainMS.service.chainCard.data.ChainCard;
import com.hq.chainMS.service.chainCard.data.ChainCardCacheDAO;
import com.hq.chainMS.service.chainCard.data.ChainCardDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class ChainCardDataHolder implements IntfDataHolder {

	public static ChainCardDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(ChainCardDataHolder.class);
	}

	final private DataSynType synType = DataSynType.ChainCard;

	public void addWithId(ChainCard target) {
		ChainCardDAO.getInstance().addWithId(target);
	}

	public void update(ChainCard target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		ChainCardDAO.getInstance().updpate(target);
		ChainCardCacheDAO.getInstance().delete(target);
	}

	public void delete(ChainCard target) {
		ChainCardDAO.getInstance().delete(target.getId());
		ChainCardCacheDAO.getInstance().delete(target);
	}

	public ChainCard get(long id) {
		ChainCard target = ChainCardCacheDAO.getInstance().get(id);
		if(target == null){
			target = ChainCardDAO.getInstance().get(id);
			if(target != null){
				ChainCardCacheDAO.getInstance().save(target);
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
		if(NumberUtils.isNumber( id )){
			ChainCard target = this.get(Long.valueOf(id));
			if(target != null){
				long newVer = target.getVer();
				if (clientSynVer.getVer() < newVer) {
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data);
				}
			}else{
				MainLog.error(LogModule.ChainCard, "ChainCardDataHolder[getSynItem]", "获取详情对象为空[id="+id+"]");
			}
		}else{
			MainLog.error(LogModule.ChainCard, "ChainCardDataHolder[getSynItem]", "数据同步失败[id="+id+"]");
		}
		return item;
	}
}

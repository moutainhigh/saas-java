package com.hq.storeMS.service.storeMaterialInfo.bs;

import org.apache.commons.lang3.math.NumberUtils;

import com.hq.storeMS.service.storeMaterialInfo.data.StoreMaterialInfo;
import com.hq.storeMS.service.storeMaterialInfo.data.StoreMaterialInfoDAO;
import com.hq.storeMS.service.storeMaterialInfo.data.StoreMaterialInfoRedisDAO;
import com.hq.storeMS.common.datasyn.IntfDataHolder;
import com.hq.storeMS.common.datasyn.info.DataSynItem;
import com.hq.storeMS.common.datasyn.info.DataSynType;
import com.hq.storeMS.common.datasyn.info.DataSynVer;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class StoreMaterialInfoDataHolder implements IntfDataHolder{
	
	public static StoreMaterialInfoDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(StoreMaterialInfoDataHolder.class);
	}
	
	final private DataSynType synType = DataSynType.StoreMaterialInfo;
	
	/**
	 * 业务层一定要区分是add还是update,用此方法的时候id必须是long型的自增字段
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void addWithId(StoreMaterialInfo target) {
		StoreMaterialInfoDAO.getInstance().addWithId(target);
	}
	

	/**
	 * 业务层一定要区分是add还是update
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void update(StoreMaterialInfo target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		StoreMaterialInfoDAO.getInstance().updpate(target);
		StoreMaterialInfoRedisDAO.getInstance().delete(target.getId());
	}
	
	public void delete(long id) {
		StoreMaterialInfoDAO.getInstance().delete(id);
		StoreMaterialInfoRedisDAO.getInstance().delete(id);
	}

	public StoreMaterialInfo get(long id) {
		StoreMaterialInfo target = StoreMaterialInfoRedisDAO.getInstance().get(id);
		if(target == null){
			target = StoreMaterialInfoDAO.getInstance().get(id);
			if(target != null){
				StoreMaterialInfoRedisDAO.getInstance().save(target);
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
			long idL = Long.valueOf(id);
			StoreMaterialInfo target = this.get(idL);			
			if(target != null){
				long newVer = target.getVer();
				if(clientSynVer.getVer() < newVer){
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data );
				}
			}else{
				MainLog.info(LogModule.StoreMaterialInfo, "MaterialDataHolder[getSynItem]", "获取详情对象为空[id="+id+"]");
			}
		}else{
			MainLog.info(LogModule.StoreMaterialInfo, "MaterialDataHolder[getSynItem]", "数据同步失败[id="+id+"]");
		}
		return item;
	}
	
}

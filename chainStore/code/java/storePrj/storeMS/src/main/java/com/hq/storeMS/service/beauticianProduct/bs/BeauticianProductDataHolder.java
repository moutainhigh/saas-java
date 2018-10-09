package com.hq.storeMS.service.beauticianProduct.bs;

import com.hq.storeMS.service.beauticianProduct.data.BeauticianProduct;
import com.hq.storeMS.service.beauticianProduct.data.BeauticianProductDAO;
import com.hq.storeMS.service.beauticianProduct.data.BeauticianProductRedisDAO;
import com.hq.storeMS.common.datasyn.IntfDataHolder;
import com.hq.storeMS.common.datasyn.info.DataSynItem;
import com.hq.storeMS.common.datasyn.info.DataSynType;
import com.hq.storeMS.common.datasyn.info.DataSynVer;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class BeauticianProductDataHolder implements IntfDataHolder{
	
	public static BeauticianProductDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(BeauticianProductDataHolder.class);
	}
	
	final private DataSynType synType = DataSynType.BeauticianProduct;
	
	/**
	 * 业务层一定要区分是add还是update,用此方法的时候id必须是long型的自增字段
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void addWithId(BeauticianProduct target) {
		BeauticianProductDAO.getInstance().addWithId(target);
	}
	

	/**
	 * 业务层一定要区分是add还是update
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void update(BeauticianProduct target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		BeauticianProductDAO.getInstance().updpate(target);
		BeauticianProductRedisDAO.getInstance().delete(target.getId());
	}
	
	public void delete(String id) {
		BeauticianProductDAO.getInstance().delete(id);
		BeauticianProductRedisDAO.getInstance().delete(id);
	}

	public BeauticianProduct get(String id) {
		BeauticianProduct target = BeauticianProductRedisDAO.getInstance().get(id);
		if(target == null){
			target = BeauticianProductDAO.getInstance().get(id);
			if(target != null){
				BeauticianProductRedisDAO.getInstance().save(target);
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
		BeauticianProduct target = this.get(id);			
		if(target != null){
			long newVer = target.getVer();
			if(clientSynVer.getVer() < newVer){
				String data = DataSynMgr.getInstance().toClientData(target);
				item = DataSynItem.newInstance(clientSynVer, newVer, data );
			}
		}else{
			MainLog.info(LogModule.BeauticianProduct, "BeauticianProductDataHolder[getSynItem]", "获取详情对象为空[id="+id+"]");
		}
		return item;
	}
	
}

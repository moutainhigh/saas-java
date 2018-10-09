package com.hq.chainMS.service.chainProduct.bs;

import org.apache.commons.lang3.math.NumberUtils;

import com.hq.chainMS.common.datasyn.IntfDataHolder;
import com.hq.chainMS.common.datasyn.info.DataSynItem;
import com.hq.chainMS.common.datasyn.info.DataSynType;
import com.hq.chainMS.common.datasyn.info.DataSynVer;
import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.common.log.MainLog;
import com.hq.chainMS.service.chainProduct.data.ChainProduct;
import com.hq.chainMS.service.chainProduct.data.ChainProductCacheDAO;
import com.hq.chainMS.service.chainProduct.data.ChainProductDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class ChainProductDataHolder implements IntfDataHolder{
	
	public static ChainProductDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(ChainProductDataHolder.class);
	}
	
	final private DataSynType synType = DataSynType.ChainProduct;
	
	public void addWithId(ChainProduct target) {
		ChainProductDAO.getInstance().addWithId(target);
	}
	
	public void delete(ChainProduct target) {
		ChainProductDAO.getInstance().delete(target.getId());
		ChainProductCacheDAO.getInstance().delete(target);
	}
	
	public void update(ChainProduct target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		ChainProductDAO.getInstance().updpate(target);
		ChainProductCacheDAO.getInstance().delete(target);
	}
	
	public ChainProduct get(long id) {
		ChainProduct target = ChainProductCacheDAO.getInstance().get(id);
		if(target == null){
			target = ChainProductDAO.getInstance().get(id);
			if(target != null){
				ChainProductCacheDAO.getInstance().save(target);
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
			ChainProduct target = this.get(Long.valueOf(id));			
			if(target != null){
				long newVer = target.getVer();
				if(clientSynVer.getVer() < newVer){
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data );
				}
			}else{
				MainLog.info(LogModule.ChainProduct, "ChainProductDataHolder[getSynItem]", "获取详情对象为空[id="+id+"]");
			}
		}else{
			MainLog.info(LogModule.ChainProduct, "ChainProductDataHolder[getSynItem]", "数据同步失败[id="+id+"]");
		}
		return item;
	}
	
}

package com.hq.storeMS.service.storeGoods.bs;

import org.apache.commons.lang3.math.NumberUtils;

import com.hq.storeMS.common.datasyn.IntfDataHolder;
import com.hq.storeMS.common.datasyn.info.DataSynItem;
import com.hq.storeMS.common.datasyn.info.DataSynType;
import com.hq.storeMS.common.datasyn.info.DataSynVer;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.storeGoods.data.StoreGoods;
import com.hq.storeMS.service.storeGoods.data.StoreGoodsCacheDAO;
import com.hq.storeMS.service.storeGoods.data.StoreGoodsDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class StoreGoodsDataHolder implements IntfDataHolder{
	
	public static StoreGoodsDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(StoreGoodsDataHolder.class);
	}
	
	final private DataSynType synType = DataSynType.StoreGoods;
	
	public void addWithId(StoreGoods target) {
		StoreGoodsDAO.getInstance().addWithId(target);
	}

	public void delete(StoreGoods target) {
		StoreGoodsDAO.getInstance().delete(target.getId());
		StoreGoodsCacheDAO.getInstance().delete(target);
	}
	
	public void update(StoreGoods target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		StoreGoodsDAO.getInstance().updpate(target);
		StoreGoodsCacheDAO.getInstance().delete(target);
	}

	public StoreGoods get(long id) {
		StoreGoods target = StoreGoodsCacheDAO.getInstance().get(id);
		if(target == null){
			target = StoreGoodsDAO.getInstance().get(id);
			if(target != null){
				StoreGoodsCacheDAO.getInstance().save(target);
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
			StoreGoods target = this.get(Long.valueOf(id));			
			if(target != null){
				long newVer = target.getVer();
				if(clientSynVer.getVer() < newVer){
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data );
				}
			}else{
				MainLog.info(LogModule.StoreGoods, "StoreGoodsDataHolder[getSynItem]", "获取详情对象为空[id="+id+"]");
			}
		}else{
			MainLog.info(LogModule.StoreGoods, "StoreGoodsDataHolder[getSynItem]", "数据同步失败[id="+id+"]");
		}
		return item;
	}
}

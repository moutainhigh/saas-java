package com.hq.chainMS.service.chainGoods.bs;

import org.apache.commons.lang3.math.NumberUtils;

import com.hq.chainMS.common.datasyn.IntfDataHolder;
import com.hq.chainMS.common.datasyn.info.DataSynItem;
import com.hq.chainMS.common.datasyn.info.DataSynType;
import com.hq.chainMS.common.datasyn.info.DataSynVer;
import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.common.log.MainLog;
import com.hq.chainMS.service.chainGoods.data.ChainGoods;
import com.hq.chainMS.service.chainGoods.data.ChainGoodsCacheDAO;
import com.hq.chainMS.service.chainGoods.data.ChainGoodsDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class ChainGoodsDataHolder implements IntfDataHolder{
	
	public static ChainGoodsDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(ChainGoodsDataHolder.class);
	}
	
	final private DataSynType synType = DataSynType.ChainGoods;
	
	public void addWithId(ChainGoods target) {
		ChainGoodsDAO.getInstance().addWithId(target);
	}

	public void delete(ChainGoods target) {
		ChainGoodsDAO.getInstance().delete(target.getId());
		ChainGoodsCacheDAO.getInstance().delete(target);
	}
	
	public void update(ChainGoods target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		ChainGoodsDAO.getInstance().updpate(target);
		ChainGoodsCacheDAO.getInstance().delete(target);
	}

	public ChainGoods get(long id) {
		ChainGoods target = ChainGoodsCacheDAO.getInstance().get(id);
		if(target == null){
			target = ChainGoodsDAO.getInstance().get(id);
			if(target != null){
				ChainGoodsCacheDAO.getInstance().save(target);
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
			ChainGoods target = this.get(Long.valueOf(id));			
			if(target != null){
				long newVer = target.getVer();
				if(clientSynVer.getVer() < newVer){
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data );
				}
			}else{
				MainLog.info(LogModule.ChainGoods, "ChainGoodsDataHolder[getSynItem]", "获取详情对象为空[id="+id+"]");
			}
		}else{
			MainLog.info(LogModule.ChainGoods, "ChainGoodsDataHolder[getSynItem]", "数据同步失败[id="+id+"]");
		}
		return item;
	}
	
}

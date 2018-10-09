package com.hq.chainMS.service.chain.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.hq.chainMS.common.datasyn.IntfDataHolder;
import com.hq.chainMS.common.datasyn.info.DataSynItem;
import com.hq.chainMS.common.datasyn.info.DataSynType;
import com.hq.chainMS.common.datasyn.info.DataSynVer;
import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.common.log.MainLog;
import com.hq.chainMS.service.chain.apiData.ChainQueryForm;
import com.hq.chainMS.service.chain.data.Chain;
import com.hq.chainMS.service.chain.data.ChainCacheDAO;
import com.hq.chainMS.service.chain.data.ChainDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class ChainDataHolder implements IntfDataHolder{
	
	public static ChainDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(ChainDataHolder.class);
	}
	
	final private DataSynType synType = DataSynType.Chain;
	
	public void addAndReturnId(Chain target) {
		ChainDAO.getInstance().addAndReturnId(target);
		ChainCacheDAO.getInstance().delete(target);
	}

	public void update(Chain target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		ChainDAO.getInstance().updpate(target);
		ChainCacheDAO.getInstance().delete(target);
	}
	
	public void delete(Chain target) {
		ChainDAO.getInstance().delete(target.getId());
		ChainCacheDAO.getInstance().delete(target);
	}

	public Chain get(long id) {
		Chain target = ChainCacheDAO.getInstance().get(id);
		if(target == null){
			target = ChainDAO.getInstance().get(id);
			if(target != null){
				ChainCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public Chain findByNumber(String number) {
		Chain target = ChainCacheDAO.getInstance().getOne(number);
		if(target == null){
			target = ChainDAO.getInstance().findByNumber(number);
			if(target != null){
				ChainCacheDAO.getInstance().saveOne(number, target);
			}
		}
		return target;
	}
	
	public List<Chain> findChainByCond(ChainQueryForm queryForm) {
		List<Chain> list = ChainCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)){
			list = ChainDAO.getInstance().findChainByCond(queryForm);
			if(CollectionUtils.isNotEmpty(list)){
				ChainCacheDAO.getInstance().saveList(queryForm, list);
			}
		}
		return list;
	}
	
	public DataSynType getSynType() {
		return synType;
	}

	public DataSynItem getSynItem(DataSynVer clientSynVer){
		DataSynItem item = null;
		String id = clientSynVer.getId();
		
		if(NumberUtils.isNumber(id )){
			long idL = Long.valueOf(id);
			Chain target = this.get(idL);			
			if(target != null){
				long newVer = target.getVer();
				if(clientSynVer.getVer() < newVer){
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data );
				}
			}else{
				MainLog.info(LogModule.Chain, "ChainDataHolder[getSynItem]", "获取详情对象为空[id="+id+"]");
			}
		}else{
			MainLog.info(LogModule.Chain, "ChainDataHolder[getSynItem]", "数据同步失败[id="+id+"]");
		}
		return item;
	}
}

package com.hq.chainMS.service.chainUser.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.hq.chainMS.common.datasyn.IntfDataHolder;
import com.hq.chainMS.common.datasyn.info.DataSynItem;
import com.hq.chainMS.common.datasyn.info.DataSynType;
import com.hq.chainMS.common.datasyn.info.DataSynVer;
import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.common.log.MainLog;
import com.hq.chainMS.service.chainUser.apiData.ChainUserQueryForm;
import com.hq.chainMS.service.chainUser.data.ChainUser;
import com.hq.chainMS.service.chainUser.data.ChainUserCacheDAO;
import com.hq.chainMS.service.chainUser.data.ChainUserDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class ChainUserDataHolder implements IntfDataHolder{
	
	public static ChainUserDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(ChainUserDataHolder.class);
	}
	
	final private DataSynType synType = DataSynType.ChainUser;
	
	public void addAndReturnId(ChainUser target) {
		ChainUserDAO.getInstance().addAndReturnId(target);
		ChainUserCacheDAO.getInstance().delete(target);
	}

	public void updpate(ChainUser target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		ChainUserDAO.getInstance().updpate(target);
		ChainUserCacheDAO.getInstance().delete(target);
	}
	
	public ChainUser get(long id) {
		ChainUser target = ChainUserCacheDAO.getInstance().get(id);
		if(target == null){
			target = ChainUserDAO.getInstance().get(id);
			if(target != null){
				ChainUserCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public ChainUser findByPhone(long phone) {
		ChainUser target = ChainUserCacheDAO.getInstance().getOne(String.valueOf(phone));
		if(target == null){
			target = ChainUserDAO.getInstance().findByPhone(phone);
			if(target != null){
				ChainUserCacheDAO.getInstance().saveOne(String.valueOf(phone),target);
			}
		}
		return target;
	}
	
	public List<ChainUser> findByCond(ChainUserQueryForm queryForm) {
		List<ChainUser> list = ChainUserCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)) {
			list = ChainUserDAO.getInstance().findByCond(queryForm);
			if(CollectionUtils.isNotEmpty(list)) {
				ChainUserCacheDAO.getInstance().saveList(queryForm, list);
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
		
		if(NumberUtils.isNumber(id)){
			long idL = Long.valueOf(id);
			ChainUser target = this.get(idL);
			if(target != null){
				long newVer = target.getVer();
				if(clientSynVer.getVer() < newVer){
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data );
				}
			}else{
				MainLog.info(LogModule.ChainUser, "ChainUserDataHolder[getSynItem]", "获取详情对象为空[id="+id+"]");
			}
		}else{
			MainLog.info(LogModule.ChainUser, "ChainUserDataHolder[getSynItem]", "数据同步失败[id="+id+"]");
		}
		return item;
	}
}

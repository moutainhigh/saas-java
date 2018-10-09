package com.hq.storeManagerMS.service.muser.bs;

import org.apache.commons.lang3.math.NumberUtils;

import com.hq.storeManagerMS.common.datasyn.IntfDataHolder;
import com.hq.storeManagerMS.common.datasyn.info.DataSynItem;
import com.hq.storeManagerMS.common.datasyn.info.DataSynType;
import com.hq.storeManagerMS.common.datasyn.info.DataSynVer;
import com.hq.storeManagerMS.common.log.LogModule;
import com.hq.storeManagerMS.common.log.MainLog;
import com.hq.storeManagerMS.service.muser.data.MUser;
import com.hq.storeManagerMS.service.muser.data.MUserCacheDAO;
import com.hq.storeManagerMS.service.muser.data.MUserDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class MUserDataHolder implements IntfDataHolder{
	
	public static MUserDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(MUserDataHolder.class);
	}
	
	final private DataSynType synType = DataSynType.MUser;
	
	public void addAndReturnId(MUser target) {
		MUserDAO.getInstance().addAndReturnId(target);
		MUserCacheDAO.getInstance().delete(target);
	}

	public void delete(MUser target) {
		MUserDAO.getInstance().delete(target.getId());
		MUserCacheDAO.getInstance().delete(target);
	}
	
	public void update(MUser target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		MUserDAO.getInstance().updpate(target);
		MUserCacheDAO.getInstance().delete(target);
	}

	public MUser get(long id) {
		MUser target = MUserCacheDAO.getInstance().get(id);
		if(target == null){
			target = MUserDAO.getInstance().get(id);
			if(target != null){
				MUserCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public MUser findByAccount(String account) {
		MUser target = MUserCacheDAO.getInstance().getOne(account);
		if(target == null){
			target = MUserDAO.getInstance().findByAccount(account);
			if(target != null){
				MUserCacheDAO.getInstance().saveOne(account, target);
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
			MUser target = this.get(idL);
			if(target != null){
				long newVer = target.getVer();
				if(clientSynVer.getVer() < newVer){
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data );
				}
			}else{
				MainLog.info(LogModule.MUser, "MUserDataHolder[getSynItem]", "获取详情对象为空[id="+id+"]");
			}
		}else{
			MainLog.info(LogModule.MUser, "MUserDataHolder[getSynItem]", "数据同步失败[id="+id+"]");
		}
		return item;
	}
}

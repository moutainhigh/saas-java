package com.hq.storeMS.service.opuser.bs;

import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import com.hq.storeMS.common.datasyn.IntfDataHolder;
import com.hq.storeMS.common.datasyn.info.DataSynItem;
import com.hq.storeMS.common.datasyn.info.DataSynType;
import com.hq.storeMS.common.datasyn.info.DataSynVer;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.opuser.apiData.OPuserQueryApiForm;
import com.hq.storeMS.service.opuser.data.OPUser;
import com.hq.storeMS.service.opuser.data.OPUserCacheDAO;
import com.hq.storeMS.service.opuser.data.OPUserDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class OPUserDataHolder implements IntfDataHolder{
	
	public static OPUserDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(OPUserDataHolder.class);
	}
	
	final private DataSynType synType = DataSynType.OPUser;
	
	public void addAndReturnId(OPUser target) {
		OPUserDAO.getInstance().addAndReturnId(target);
	}

	public void updpate(OPUser target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		OPUserDAO.getInstance().updpate(target);
		OPUserCacheDAO.getInstance().delete(target);
	}
	
	public void delete(OPUser target) {
		OPUserDAO.getInstance().delete(target.getId());
		OPUserCacheDAO.getInstance().delete(target);
	}

	public OPUser get(long id) {
		OPUser target = OPUserCacheDAO.getInstance().get(id);
		if(target == null){
			target = OPUserDAO.getInstance().get(id);
			if(target != null){
				OPUserCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public OPUser findByName(String name) {
		return OPUserDAO.getInstance().findByName(name);
	}
	
	public OPUser findByPhone(long phone) {
		return OPUserDAO.getInstance().findByPhone(phone);
	}
	
	public List<OPUser> findOPuserList(OPuserQueryApiForm queryForm) {
		return OPUserDAO.getInstance().findOPuserList(queryForm);
	}
	
	public DataSynType getSynType() {
		return synType;
	}

	public DataSynItem getSynItem(DataSynVer clientSynVer){
		DataSynItem item = null;
		String id = clientSynVer.getId();
		
		if(NumberUtils.isNumber(id )){
			long idL = Long.valueOf(id);
			OPUser target = this.get(idL);			
			long newVer = target.getVer();
			if(clientSynVer.getVer() < newVer){
				String data = DataSynMgr.getInstance().toClientData(target);
				item = DataSynItem.newInstance(clientSynVer, newVer, data );
			}
		}else{
			MainLog.info(LogModule.OPUser, "OPUserDataHolder[getSynItem]", "数据同步失败[id="+id+"]");
		}
		return item;
	}
	
}

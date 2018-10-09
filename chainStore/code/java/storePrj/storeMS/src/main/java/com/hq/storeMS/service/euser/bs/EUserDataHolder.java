package com.hq.storeMS.service.euser.bs;

import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import com.hq.storeMS.common.datasyn.IntfDataHolder;
import com.hq.storeMS.common.datasyn.info.DataSynItem;
import com.hq.storeMS.common.datasyn.info.DataSynType;
import com.hq.storeMS.common.datasyn.info.DataSynVer;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.euser.data.EUser;
import com.hq.storeMS.service.euser.data.EUserCacheDAO;
import com.hq.storeMS.service.euser.data.EUserDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

/** 
 * @ClassName: EUserDataHolder 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author helen 
 * @date 2018年1月19日 下午2:49:54 
 *  
 */
public class EUserDataHolder implements IntfDataHolder {

	public static EUserDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(EUserDataHolder.class);
	}
	
	final private DataSynType synType = DataSynType.EUser;
	
	public void addAndReturnId(EUser euser) {
		EUserDAO.getInstance().addAndReturnId(euser);
	}
	
	public void update(EUser target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		EUserDAO.getInstance().updpate(target);
		EUserCacheDAO.getInstance().delete(target);
	}
	
	public void delete(EUser target) {
		EUserDAO.getInstance().delete(target.getId());
		EUserCacheDAO.getInstance().delete(target);
	}

	public EUser get(long id) {
		EUser target = EUserCacheDAO.getInstance().get(id);
		if(target == null){
			target = EUserDAO.getInstance().get(id);
			if(target != null){
				EUserCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public EUser findByPhone(long phone) {
		return EUserDAO.getInstance().findByPhone(phone);
	}
	
	public List<EUser> getList(int pageItemCount,int pageNo) {
		return EUserDAO.getInstance().getList(pageItemCount,pageNo);
	}
	
	@Override
	public DataSynItem getSynItem(DataSynVer clientSynVer){
		DataSynItem item = null;
		String id = clientSynVer.getId();
		
		if(NumberUtils.isNumber(id)){
			long idL = Long.valueOf(id);
			EUser target = this.get(idL);
			if(target != null){
				long newVer = target.getVer();
				if(clientSynVer.getVer() < newVer){
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data );
				}
			}else{
				MainLog.info(LogModule.EUser, "EUserDataHolder[getSynItem]", "获取详情对象为空[id="+id+"]");
			}
		}else{
			MainLog.info(LogModule.EUser, "EUserDataHolder[getSynItem]", "数据同步失败[id="+id+"]");
		}
		return item;
	}

	public DataSynType getSynType() {
		return synType;
	}
	

}

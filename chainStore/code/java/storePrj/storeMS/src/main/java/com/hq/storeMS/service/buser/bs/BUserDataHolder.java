package com.hq.storeMS.service.buser.bs;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.math.NumberUtils;

import com.hq.storeMS.common.datasyn.IntfDataHolder;
import com.hq.storeMS.common.datasyn.info.DataSynItem;
import com.hq.storeMS.common.datasyn.info.DataSynType;
import com.hq.storeMS.common.datasyn.info.DataSynVer;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.buser.apiData.BUserCommQueryForm;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.buser.data.BUserCacheDAO;
import com.hq.storeMS.service.buser.data.BUserCount;
import com.hq.storeMS.service.buser.data.BUserDAO;
import com.hq.storeMS.service.detailDataVersion.bs.DetailDataVersionDataHolder;
import com.hq.storeMS.service.detailDataVersion.bs.DetailDataVersionMgr;
import com.hq.storeMS.service.detailDataVersion.data.DataVersionEnum;
import com.hq.storeMS.service.detailDataVersion.data.DetailDataVersion;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class BUserDataHolder implements IntfDataHolder{
	
	public static BUserDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(BUserDataHolder.class);
	}
	
	final private DataSynType synType = DataSynType.BUser;
	final private DataVersionEnum dataVersionEnum = DataVersionEnum.StoreBUser;
	
	//级联更新版本域信息
	private void cascadeUpdate(Set<Long> storeIds) {
		for (Long storeId : storeIds) {
			cascadeUpdate(storeId);
		}
	}
	
	//级联更新版本域信息
	private void cascadeUpdate(long storeId) {
		DetailDataVersion detailDataVer = DetailDataVersionMgr.getInstance().get(storeId);
		Map<Integer, Long> detailDataVerMap = detailDataVer.getDetailDataVerMap();
		Long ver = detailDataVerMap.get(dataVersionEnum.ordinal());
		if(ver == null) {
			detailDataVerMap.put(dataVersionEnum.ordinal(), 0L);
		}else {
			detailDataVerMap.put(dataVersionEnum.ordinal(), ver + 1);
		}
		DetailDataVersionDataHolder.getInstance().update(detailDataVer);
	}
	
	public void addAndReturnId(BUser target) {
		BUserDAO.getInstance().addAndReturnId(target);
	}

	public void updpate(BUser target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		BUserDAO.getInstance().updpate(target);
		BUserCacheDAO.getInstance().delete(target);
		cascadeUpdate(target.getStoreIdSet());
	}
	
	public void delete(BUser target) {
		BUserDAO.getInstance().delete(target.getId());
		BUserCacheDAO.getInstance().delete(target);
		cascadeUpdate(target.getStoreIdSet());
	}

	public BUser get(long id) {
		BUser target = BUserCacheDAO.getInstance().get(id);
		if(target == null){
			target = BUserDAO.getInstance().get(id);
			if(target != null){
				BUserCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public List<BUser> findListByCond(BUserCommQueryForm queryForm) {
		return BUserDAO.getInstance().findByCond(queryForm);
	}
	
	public BUser findOneByCond(BUserCommQueryForm queryForm) {
		return BUserDAO.getInstance().findOne(queryForm);
	}
	
	public BUserCount getCount(BUserCommQueryForm queryForm) {
		return BUserDAO.getInstance().getCount(queryForm);
	}
	
	//升级priAccountNum为空的数据
	public List<BUser> findPriAccountNumNotExists() {
		return BUserDAO.getInstance().findPriAccountNumNotExists();
	}
	
	public DataSynType getSynType() {
		return synType;
	}

	public DataSynItem getSynItem(DataSynVer clientSynVer){
		DataSynItem item = null;
		String id = clientSynVer.getId();
		
		if(NumberUtils.isNumber(id)){
			long idL = Long.valueOf(id);
			BUser target = this.get(idL);
			if(target != null){
				long newVer = target.getVer();
				if(clientSynVer.getVer() < newVer){
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data );
				}
			}else{
				MainLog.info(LogModule.BUser, "BUserDataHolder[getSynItem]", "获取详情对象为空[id="+id+"]");
			}
		}else{
			MainLog.info(LogModule.BUser, "BUserDataHolder[getSynItem]", "数据同步失败[id="+id+"]");
		}
		return item;
	}
}

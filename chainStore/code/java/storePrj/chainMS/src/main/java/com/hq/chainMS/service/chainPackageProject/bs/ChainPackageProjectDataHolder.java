package com.hq.chainMS.service.chainPackageProject.bs;

import org.apache.commons.lang3.math.NumberUtils;

import com.hq.chainMS.common.datasyn.IntfDataHolder;
import com.hq.chainMS.common.datasyn.info.DataSynItem;
import com.hq.chainMS.common.datasyn.info.DataSynType;
import com.hq.chainMS.common.datasyn.info.DataSynVer;
import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.common.log.MainLog;
import com.hq.chainMS.service.chainPackageProject.data.ChainPackageProject;
import com.hq.chainMS.service.chainPackageProject.data.ChainPackageProjectCacheDAO;
import com.hq.chainMS.service.chainPackageProject.data.ChainPackageProjectDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class ChainPackageProjectDataHolder implements IntfDataHolder {

	public static ChainPackageProjectDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(ChainPackageProjectDataHolder.class);
	}

	final private DataSynType synType = DataSynType.ChainPackageProject;

	public void addWithId(ChainPackageProject target) {
		ChainPackageProjectDAO.getInstance().addWithId(target);
	}

	public void update(ChainPackageProject target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		ChainPackageProjectDAO.getInstance().updpate(target);
		ChainPackageProjectCacheDAO.getInstance().delete(target);
	}

	public void delete(ChainPackageProject target) {
		ChainPackageProjectDAO.getInstance().delete(target.getId());
		ChainPackageProjectCacheDAO.getInstance().delete(target);
	}

	public ChainPackageProject get(long id) {
		ChainPackageProject target = ChainPackageProjectCacheDAO.getInstance().get(id);
		if(target == null){
			target = ChainPackageProjectDAO.getInstance().get(id);
			if(target != null){
				ChainPackageProjectCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public DataSynType getSynType() {
		return synType;
	}

	public DataSynItem getSynItem(DataSynVer clientSynVer) {
		DataSynItem item = null;
		String id = clientSynVer.getId();
		if(NumberUtils.isNumber( id )){
			ChainPackageProject target = this.get(Long.valueOf(id));
			if(target != null){
				long newVer = target.getVer();
				if (clientSynVer.getVer() < newVer) {
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data);
				}
			}else{
				MainLog.error(LogModule.ChainPackageProject, "ChainPackageProjectDataHolder[getSynItem]", "获取详情对象为空[id="+id+"]");
			}
		}else{
			MainLog.error(LogModule.ChainPackageProject, "ChainPackageProjectDataHolder[getSynItem]", "数据同步失败[id="+id+"]");
		}
		return item;
	}
}

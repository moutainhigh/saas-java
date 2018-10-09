package com.hq.storeMS.service.chainPackageProject.bs;

import org.apache.commons.lang3.math.NumberUtils;

import com.hq.chainClient.service.chainPackageProject.bs.ChainPackageProjectClientMgr;
import com.hq.chainClient.service.chainPackageProject.bs.PackageProjectDetailClientMgr;
import com.hq.chainClient.service.chainPackageProject.data.ChainPackageProject;
import com.hq.chainClient.service.chainPackageProject.data.PackageProjectDetail;
import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.datasyn.IntfDataHolder;
import com.hq.storeMS.common.datasyn.info.DataSynItem;
import com.hq.storeMS.common.datasyn.info.DataSynType;
import com.hq.storeMS.common.datasyn.info.DataSynVer;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.common.validate.AppIdThreadLocal;
import com.hq.storeMS.service.chainPackageProject.data.ChainPackageProjectCacheDAO;
import com.hq.storeMS.service.chainPackageProject.data.PackageProjectDetailCacheDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class ChainPackageProjectDataHolder implements IntfDataHolder{
	
	public static ChainPackageProjectDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(ChainPackageProjectDataHolder.class);
	}
	
	final private DataSynType synType = DataSynType.ChainPackageProject;
	
	public ChainPackageProject get(long id) {
		ChainPackageProject data = ChainPackageProjectCacheDAO.getInstance().get(id);
		if(data!=null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = ChainPackageProjectClientMgr.getInstance().get(id);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
	public PackageProjectDetail getPackageProjectDetail(String packageProjectId, long chainId) {
		PackageProjectDetail data = PackageProjectDetailCacheDAO.getInstance().get(chainId, packageProjectId);
		if(data!=null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = PackageProjectDetailClientMgr.getInstance().getPackageProjectDetail(chainId, packageProjectId);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
	public DataSynType getSynType() {
		return synType;
	}

	public DataSynItem getSynItem(DataSynVer clientSynVer){
		DataSynItem item = null;
		String id = clientSynVer.getId();
		if(NumberUtils.isNumber(id)){
			ChainPackageProject target = this.get(Long.valueOf(id));			
			if(target != null){
				long newVer = target.getVer();
				if(clientSynVer.getVer() < newVer){
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data );
				}
			}else{
				MainLog.info(LogModule.ChainPackageProject, "ChainPackageProjectDataHolder[getSynItem]", "获取详情对象为空[id="+id+"]");
			}
		}else{
			MainLog.info(LogModule.ChainPackageProject, "ChainPackageProjectDataHolder[getSynItem]", "数据同步失败[id="+id+"]");
		}
		return item;
	}
}

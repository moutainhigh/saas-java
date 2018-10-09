package com.hq.storeMS.service.serverConfig.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.hq.storeMS.common.datasyn.IntfDataHolder;
import com.hq.storeMS.common.datasyn.info.DataSynItem;
import com.hq.storeMS.common.datasyn.info.DataSynType;
import com.hq.storeMS.common.datasyn.info.DataSynVer;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.serverConfig.apiData.ServerConfigQueryForm;
import com.hq.storeMS.service.serverConfig.data.ServerConfig;
import com.hq.storeMS.service.serverConfig.data.ServerConfigCacheDAO;
import com.hq.storeMS.service.serverConfig.data.ServerConfigDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class ServerConfigDataHolder implements IntfDataHolder{

	public static ServerConfigDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(ServerConfigDataHolder.class);
	}
	
	final private DataSynType synType = DataSynType.ServerConfig;
	
	public void addAndReturnId(ServerConfig target) {
		ServerConfigDAO.getInstance().addAndReturnId(target);
		ServerConfigCacheDAO.getInstance().delete(target);
	}

	public void update(ServerConfig target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		ServerConfigDAO.getInstance().updpate(target);
		ServerConfigCacheDAO.getInstance().delete(target);
	}
	
	public void delete(ServerConfig target) {
		ServerConfigDAO.getInstance().delete(target.getId());
		ServerConfigCacheDAO.getInstance().delete(target);
	}
	
	public ServerConfig get(long id) {
		ServerConfig target = ServerConfigCacheDAO.getInstance().get(id);
		if(target == null){
			target = ServerConfigDAO.getInstance().get(id);
			if(target != null){
				ServerConfigCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public ServerConfig findOne(String appNameCh, String appNameEn, String appVersion) {
		String key = AppUtils.joinByUnderline(appNameCh, appNameEn, appVersion);
		ServerConfig target = ServerConfigCacheDAO.getInstance().getOne(key);
		if(target == null){
			target = ServerConfigDAO.getInstance().findOne(appNameCh,appNameEn,appVersion);
			if(target != null){
				ServerConfigCacheDAO.getInstance().saveOne(key, target);
			}
		}
		return target;
	}
	
	public List<ServerConfig> findList(ServerConfigQueryForm queryForm) {
		List<ServerConfig> list = ServerConfigCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)){
			list = ServerConfigDAO.getInstance().findList(queryForm);
			if(CollectionUtils.isNotEmpty(list)){
				ServerConfigCacheDAO.getInstance().saveList(queryForm, list);
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
			ServerConfig target = this.get(idL);			
			if(target != null){
				long newVer = target.getVer();
				if(clientSynVer.getVer() < newVer){
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data );
				}
			}else{
				MainLog.info(LogModule.ServerConfig, "ServerConfigDataHolder[getSynItem]", "获取详情对象为空[id="+id+"]");
			}
		}else{
			MainLog.info(LogModule.ServerConfig, "ServerConfigDataHolder[getSynItem]", "数据同步失败[id="+id+"]");
		}
		return item;
	}

}

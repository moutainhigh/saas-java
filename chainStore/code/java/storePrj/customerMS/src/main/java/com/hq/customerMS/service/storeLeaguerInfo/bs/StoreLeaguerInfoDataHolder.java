package com.hq.customerMS.service.storeLeaguerInfo.bs;

import org.apache.commons.lang.math.NumberUtils;

import com.hq.customerMS.common.constants.ServerConstants;
import com.hq.customerMS.common.datasyn.IntfDataHolder;
import com.hq.customerMS.common.datasyn.info.DataSynItem;
import com.hq.customerMS.common.datasyn.info.DataSynType;
import com.hq.customerMS.common.datasyn.info.DataSynVer;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.common.log.MainLog;
import com.hq.customerMS.common.validate.AppIdThreadLocal;
import com.hq.customerMS.service.storeLeaguerInfo.data.StoreLeaguerInfoCacheDAO;
import com.hq.storeClient.service.storeLeaguerInfo.bs.StoreLeaguerInfoClientMgr;
import com.hq.storeClient.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class StoreLeaguerInfoDataHolder implements IntfDataHolder {

	public static StoreLeaguerInfoDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(StoreLeaguerInfoDataHolder.class);
	}

	final private DataSynType synType = DataSynType.StoreLeaguerInfo;

	public StoreLeaguerInfo get(long storeId) {
		StoreLeaguerInfo data = StoreLeaguerInfoCacheDAO.getInstance().get(storeId);
		if(data!=null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = StoreLeaguerInfoClientMgr.getInstance().get(storeId);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

	public DataSynType getSynType() {
		return synType;
	}

	public DataSynItem getSynItem(DataSynVer clientSynVer) {
		DataSynItem item = null;
		String id = clientSynVer.getId();
		if (NumberUtils.isNumber(id)) {
			StoreLeaguerInfo target = this.get(Long.valueOf(id));
			if (target != null) {
				long newVer = target.getVer();
				if (clientSynVer.getVer() < newVer) {
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data);
				}
			} else {
				MainLog.info(LogModule.StoreLeaguerInfo, "StoreLeaguerInfoDataHolder[getSynItem]", "获取详情对象为空[id=" + id + "]");
			}
		} else {
			MainLog.info(LogModule.StoreLeaguerInfo, "StoreLeaguerInfoDataHolder[getSynItem]", "数据同步失败[id=" + id + "]");
		}
		return item;
	}

}

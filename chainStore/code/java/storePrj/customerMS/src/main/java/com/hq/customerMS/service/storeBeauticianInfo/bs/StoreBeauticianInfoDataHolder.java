package com.hq.customerMS.service.storeBeauticianInfo.bs;

import org.apache.commons.lang3.math.NumberUtils;

import com.hq.customerMS.common.constants.ServerConstants;
import com.hq.customerMS.common.datasyn.IntfDataHolder;
import com.hq.customerMS.common.datasyn.info.DataSynItem;
import com.hq.customerMS.common.datasyn.info.DataSynType;
import com.hq.customerMS.common.datasyn.info.DataSynVer;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.common.log.MainLog;
import com.hq.customerMS.common.validate.AppIdThreadLocal;
import com.hq.customerMS.service.storeBeauticianInfo.data.StoreBeauticianInfoCacheDAO;
import com.hq.storeClient.service.storeBeauticianInfo.bs.StoreBeauticianInfoClientMgr;
import com.hq.storeClient.service.storeBeauticianInfo.data.StoreBeauticianInfo;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class StoreBeauticianInfoDataHolder implements IntfDataHolder {

	public static StoreBeauticianInfoDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(StoreBeauticianInfoDataHolder.class);
	}

	final private DataSynType synType = DataSynType.StoreBeauticianInfo;

	public StoreBeauticianInfo get(long id) {
		StoreBeauticianInfo data = StoreBeauticianInfoCacheDAO.getInstance().get(id);
		if(data!=null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = StoreBeauticianInfoClientMgr.getInstance().get(id);
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
			StoreBeauticianInfo target = this.get(Long.valueOf(id));
			if (target != null) {
				long newVer = target.getVer();
				if (clientSynVer.getVer() < newVer) {
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data);
				}
			} else {
				MainLog.info(LogModule.StoreBeauticianInfo, "StoreBeauticianInfoDataHolder[getSynItem]", "获取详情对象为空[id=" + id + "]");
			}
		} else {
			MainLog.info(LogModule.StoreBeauticianInfo, "StoreBeauticianInfoDataHolder[getSynItem]", "数据同步失败[id=" + id + "]");
		}
		return item;
	}

}

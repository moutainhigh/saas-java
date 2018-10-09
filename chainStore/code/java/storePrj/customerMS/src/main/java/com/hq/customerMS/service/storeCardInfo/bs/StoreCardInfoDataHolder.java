package com.hq.customerMS.service.storeCardInfo.bs;

import org.apache.commons.lang.math.NumberUtils;

import com.hq.customerMS.common.constants.ServerConstants;
import com.hq.customerMS.common.datasyn.IntfDataHolder;
import com.hq.customerMS.common.datasyn.info.DataSynItem;
import com.hq.customerMS.common.datasyn.info.DataSynType;
import com.hq.customerMS.common.datasyn.info.DataSynVer;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.common.log.MainLog;
import com.hq.customerMS.common.validate.AppIdThreadLocal;
import com.hq.customerMS.service.storeCardInfo.data.StoreCardInfoCacheDAO;
import com.hq.storeClient.service.storeCardInfo.bs.StoreCardInfoClientMgr;
import com.hq.storeClient.service.storeCardInfo.data.StoreCardInfo;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class StoreCardInfoDataHolder implements IntfDataHolder {

	public static StoreCardInfoDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(StoreCardInfoDataHolder.class);
	}

	final private DataSynType synType = DataSynType.StoreCardInfo;

	public StoreCardInfo get(long storeId) {
		StoreCardInfo data = StoreCardInfoCacheDAO.getInstance().get(storeId);
		if(data!=null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = StoreCardInfoClientMgr.getInstance().get(storeId);
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
			StoreCardInfo target = this.get(Long.valueOf(id));
			if (target != null) {
				long newVer = target.getVer();
				if (clientSynVer.getVer() < newVer) {
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data);
				}
			} else {
				MainLog.error(LogModule.StoreCardInfo, "StoreCardInfoDataHolder[getSynItem]", "获取详情对象为空[id=" + id + "]");
			}
		} else {
			MainLog.error(LogModule.StoreCardInfo, "StoreCardInfoDataHolder[getSynItem]", "数据同步失败[id=" + id + "]");
		}
		return item;
	}
}

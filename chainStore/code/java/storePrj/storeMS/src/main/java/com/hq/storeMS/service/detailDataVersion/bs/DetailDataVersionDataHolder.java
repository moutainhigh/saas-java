package com.hq.storeMS.service.detailDataVersion.bs;

import org.apache.commons.lang3.math.NumberUtils;

import com.hq.storeMS.common.datasyn.IntfDataHolder;
import com.hq.storeMS.common.datasyn.info.DataSynItem;
import com.hq.storeMS.common.datasyn.info.DataSynType;
import com.hq.storeMS.common.datasyn.info.DataSynVer;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.detailDataVersion.data.DetailDataVersion;
import com.hq.storeMS.service.detailDataVersion.data.DetailDataVersionCacheDAO;
import com.hq.storeMS.service.detailDataVersion.data.DetailDataVersionDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class DetailDataVersionDataHolder implements IntfDataHolder {

	public static DetailDataVersionDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(DetailDataVersionDataHolder.class);
	}

	final private DataSynType synType = DataSynType.DetailDataVersion;

	public void addWithId(DetailDataVersion target) {
		DetailDataVersionDAO.getInstance().addWithId(target);
	}

	public void update(DetailDataVersion target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		DetailDataVersionDAO.getInstance().updpate(target);
		DetailDataVersionCacheDAO.getInstance().delete(target);
	}

	public void delete(DetailDataVersion target) {
		DetailDataVersionDAO.getInstance().delete(target.getId());
		DetailDataVersionCacheDAO.getInstance().delete(target);
	}

	public DetailDataVersion get(long id) {
		DetailDataVersion target = DetailDataVersionCacheDAO.getInstance().get(id);
		if (target == null) {
			target = DetailDataVersionDAO.getInstance().get(id);
			if (target != null) {
				DetailDataVersionCacheDAO.getInstance().save(target);
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

		if (NumberUtils.isNumber(id)) {
			DetailDataVersion target = this.get(Long.valueOf(id));
			if (target != null) {
				long newVer = target.getVer();
				if (clientSynVer.getVer() < newVer) {
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data);
				}
			} else {
				MainLog.info(LogModule.DetailDataVersion, "DetailDataVersionDataHolder[getSynItem]", "获取详情对象为空[id=" + id + "]");
			}
		} else {
			MainLog.info(LogModule.DetailDataVersion, "DetailDataVersionDataHolder[getSynItem]", "数据同步失败[id=" + id + "]");
		}

		return item;
	}

}

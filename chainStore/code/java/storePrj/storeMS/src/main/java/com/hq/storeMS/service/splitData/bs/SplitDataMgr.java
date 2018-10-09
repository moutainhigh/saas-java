package com.hq.storeMS.service.splitData.bs;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.common.util.PageUtil;
import com.hq.storeMS.common.validate.ValidateInfoThreadLocal;
import com.hq.storeMS.common.validate.info.ValidateInfo;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.splitData.bs.mgrHelper.BuserPriAccountMgr;
import com.hq.storeMS.service.store.bs.StoreDataHolder;
import com.hq.storeMS.service.store.data.Store;
import com.hq.storeMS.service.store.data.StoreState;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * 用于数据割接的脚本  [一般情况下，每个环境只需要执行一次即可 但设计的时候 需要设计成可以重复执行]
 * @author kevin
 *
 */
public class SplitDataMgr {

	public static SplitDataMgr getInstance() {
		return HotSwap.getInstance().getSingleton(SplitDataMgr.class);
	}

	private AtomicBoolean isInit = new AtomicBoolean(false);

	public void init() {
		try {
			if (isInit.compareAndSet(false, true)) {
				splitData();
				BuserPriAccountMgr.getInstance().updateData();
			}
		} catch (Exception e) {
			MainLog.error(LogModule.Tmp, "SplitDataMgr[init]", "初始化SplitDataMgr失败", e);
		}
	}
	
	private void splitData() {
		int pageItemCount = 100;
		int count = (int)StoreDataHolder.getInstance().allStoreCount();
		int totalPage = PageUtil.getInstance().getTotalPage(count, pageItemCount);
		for (int i = 0; i < totalPage; i++) {
			List<Store> stores = StoreDataHolder.getInstance().findPage(pageItemCount, i+1);
			for (Store store : stores) {
				int entityState = store.getEntityState();
				int state = store.getState();
				if(entityState != EntityState.Deleted.ordinal() && state == StoreState.Open.ordinal()) {
					long bossId = store.getBossId();
					long storeId = store.getId();
					ValidateInfo authInfo = new ValidateInfo();
					authInfo.setBossId(bossId);
					authInfo.setStoreId(storeId);
					ValidateInfoThreadLocal.getInstance().set(authInfo);
					SplitDataMgrHelper.getInstance().splitData(storeId);
					ValidateInfoThreadLocal.getInstance().set(null);
				}
			}
		}
	}
}

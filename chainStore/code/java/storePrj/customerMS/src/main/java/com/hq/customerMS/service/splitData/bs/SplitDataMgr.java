package com.hq.customerMS.service.splitData.bs;

import java.util.concurrent.atomic.AtomicBoolean;

import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.common.log.MainLog;
import com.hq.customerMS.service.splitData.bs.updateData.CUserPriAccountMgr;
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
			}
		} catch (Exception e) {
			MainLog.error(LogModule.Tmp, "SplitDataMgr[init]", "初始化SplitDataMgr失败", e);
		}
	}
	
	private void splitData() {
		CUserPriAccountMgr.getInstance().updateData();
	}
}

package com.hq.storeMS.service.sysConf.bs;

import java.util.concurrent.atomic.AtomicBoolean;

import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * 系统配置参数【暂时没用上】
 * 
 * @author kevin
 *
 */
public class SysConfMgr {

	public static SysConfMgr getInstance() {
		return HotSwap.getInstance().getSingleton(SysConfMgr.class);
	}

	private AtomicBoolean isInit = new AtomicBoolean(false);

	public void init() {
		try {
			if (isInit.compareAndSet(false, true)) {
				initData();
			}
		} catch (Exception e) {
			MainLog.error(LogModule.Tmp, "SysConfMgr[init]", "初始化SysConfMgr失败", e);
		}
	}

	private void initData() {
	}
}

package com.hq.customerMS.service.sysDataInit.bs;

import java.util.concurrent.atomic.AtomicBoolean;

import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.common.log.MainLog;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * 用于系统启动时，初始化一些必要的数据信息
 * @author kevin
 *
 */
public class SysDataInitMgr {

	public static SysDataInitMgr getInstance(){
		return HotSwap.getInstance().getSingleton(SysDataInitMgr.class);
	}
	
	private AtomicBoolean isInit = new AtomicBoolean(false);

	public void init() {
		try {
			if (isInit.compareAndSet(false, true)) {
				initData();
			}
		} catch (Exception e) {
			MainLog.error(LogModule.Tmp, "SysDataInitMgr[init]", "初始化SysDataInitMgr失败", e);
		}
	}
	
	private void initData() {
	}
}

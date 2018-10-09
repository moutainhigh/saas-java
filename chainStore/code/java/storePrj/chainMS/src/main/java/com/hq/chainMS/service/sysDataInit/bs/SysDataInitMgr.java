package com.hq.chainMS.service.sysDataInit.bs;

import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.common.log.MainLog;
import com.hq.chainMS.service.areaCode.bs.AreaCodeMgr;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * 用于系统启动时，初始化一些必要的数据信息
 * 
 * @author kevin
 *
 */
public class SysDataInitMgr {

	public static SysDataInitMgr getInstance() {
		return HotSwap.getInstance().getSingleton(SysDataInitMgr.class);
	}

	public void init() {
		// 国际手机区号数据初始化
		MainLog.info(LogModule.StartInfo, "AreaCodeMgr[init]", "AreaCodeMgr.getInstance().init()");
		AreaCodeMgr.getInstance().init();
	}
}

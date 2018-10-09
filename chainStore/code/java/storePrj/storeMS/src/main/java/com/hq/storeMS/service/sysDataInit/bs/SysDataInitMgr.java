package com.hq.storeMS.service.sysDataInit.bs;

import java.util.concurrent.atomic.AtomicBoolean;

import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.areaCode.bs.AreaCodeMgr;
import com.hq.storeMS.service.buserRole.bs.BuserRoleMgr;
import com.hq.storeMS.service.storeVipType.bs.StoreVipTypeMgr;
import com.hq.storeMS.service.workFlowType.bs.WorkFlowTypeMgr;
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
		// 初始化会员类型
		MainLog.info(LogModule.StartInfo, "StoreVipTypeMgr[init]", "StoreVipTypeMgr.getInstance().init()");
		StoreVipTypeMgr.getInstance().init();

		// 国际手机区号数据初始化
		MainLog.info(LogModule.StartInfo, "AreaCodeMgr[init]", "AreaCodeMgr.getInstance().init()");
		AreaCodeMgr.getInstance().init();

		// 初始化工作流类型
		MainLog.info(LogModule.StartInfo, "WorkFlowTypeMgr[init]", "WorkFlowTypeMgr.getInstance().init()");
		WorkFlowTypeMgr.getInstance().init();

		// 初始化老板的商户会员等级信息
		MainLog.info(LogModule.StartInfo, "BuserRoleMgr[init]", "BuserRoleMgr.getInstance().init()");
		BuserRoleMgr.getInstance().init();
	}
}

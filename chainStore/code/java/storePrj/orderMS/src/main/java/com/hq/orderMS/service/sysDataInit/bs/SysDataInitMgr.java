package com.hq.orderMS.service.sysDataInit.bs;

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
	
	public void init(){
	}
}

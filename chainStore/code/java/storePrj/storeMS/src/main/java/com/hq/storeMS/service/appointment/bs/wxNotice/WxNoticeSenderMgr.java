package com.hq.storeMS.service.appointment.bs.wxNotice;

import java.util.concurrent.atomic.AtomicBoolean;

import com.hq.storeMS.common.config.StoreMSCfg;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.appointment.data.Appointment;
import com.zenmind.common.asynExecutor.IntfErrorHandler;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * 智美预约小程序发送通知的Mgr类
 * 使用前需要先初始化
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class WxNoticeSenderMgr {
	public static WxNoticeSenderMgr getInstance(){		
		return HotSwap.getInstance().getSingleton(WxNoticeSenderMgr.class);
	}
	
	private WxNoticeSender sender;
	private boolean open = false;
	private AtomicBoolean isInit = new AtomicBoolean(false);
	
	public void send(Appointment appointment) {
		if(isOpen()){
			boolean success = sender.addNotice(appointment);
			if(!success) { //添加失败时，要继续尝试，否则此次通知会丢失
				this.send(appointment);
			}
		}
	}
	
	public void init(StoreMSCfg storeMSCfg) {
		if(isInit.compareAndSet(false, true)){
			try {
				sender = WxNoticeSender.newInstance(storeMSCfg.getWxNoticeSenderNThread(), new IntfErrorHandler<Appointment>() {
					@Override
					public void handle(Appointment target, String msg, Throwable e) {
						MainLog.error(LogModule.Appointment, "WxNoticeSender[sendNotice]", "智美预约小程序通知发送失败", e);
					}
				});
				sender.init();
				open = true;
				MainLog.info(LogModule.StartInfo, "WxNoticeSenderMgr[init]", "初始化WxNoticeSenderMgr成功");
			} catch (Exception e) {
				MainLog.error(LogModule.StartInfo, "WxNoticeSenderMgr[init]", "初始化WxNoticeSenderMgr失败", e);
			}
		}
	}
	
	public boolean isOpen() {
		return open;
	}
}

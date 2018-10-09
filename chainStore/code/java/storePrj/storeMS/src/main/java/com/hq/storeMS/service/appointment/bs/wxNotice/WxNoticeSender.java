package com.hq.storeMS.service.appointment.bs.wxNotice;

import com.hq.storeMS.service.appointment.data.Appointment;
import com.zenmind.common.asynExecutor.AsynExecutor;
import com.zenmind.common.asynExecutor.IntfAsynTask;
import com.zenmind.common.asynExecutor.IntfErrorHandler;

/**
 * 智美预约小程序发送通知
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class WxNoticeSender {
	
	private AsynExecutor<Appointment> asynExecutor;
	
	public static WxNoticeSender newInstance(int nThreadsP, IntfErrorHandler<Appointment> errorHandlerP){
		WxNoticeSender target = new WxNoticeSender();
		if(nThreadsP < 10) {
			nThreadsP = 10;
		}
		final int queueSize = 1024*10;
		
		IntfAsynTask<Appointment> task = new IntfAsynTask<Appointment>(){
			@Override
			public void doTask(Appointment appointment) {
				WxNoticeHelper.getInstance().sendNotice(appointment);
			}
			
		};
		
		target.asynExecutor = AsynExecutor.newInstance(task, errorHandlerP, queueSize, nThreadsP);
		return target;
	}
	
	public boolean addNotice(Appointment appointment){
		boolean success = asynExecutor.addData(appointment);
		return success;
	}

	public void init() {
		asynExecutor.init();
	}
}

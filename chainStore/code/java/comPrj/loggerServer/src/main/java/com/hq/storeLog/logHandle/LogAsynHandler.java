package com.hq.storeLog.logHandle;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import com.hq.common.log.LogModule;
import com.hq.common.log.MainLog;
import com.hq.stream.log.StoreLog;
import com.zenmind.common.hotSwap.HotSwap;

public class LogAsynHandler {

	public static LogAsynHandler getInstance(){		
		return HotSwap.getInstance().getSingleton(LogAsynHandler.class);
	}
	
	private AtomicBoolean isInit = new AtomicBoolean(false);

	private BlockingQueue<StoreLog>  dataQueue = new LinkedBlockingQueue<StoreLog>(1024);
	
	public void init(){
		if(isInit.compareAndSet(false, true)){
			int nThreads = 30;
			initUpdateService(nThreads);
		}
	}
	
	public void addLog(StoreLog storeLog){
		if(dataQueue.size()<1000){
			dataQueue.add(storeLog);
		}else{
			MainLog.error(LogModule.Tmp, null, "处理速度不过，丢弃log");
		}
	}

	private void initUpdateService(int nThreads) {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(nThreads);
		
		for (int i = 0; i < nThreads; i++) {
			Runnable updateTask = newUpdateTask();
			newFixedThreadPool.submit(updateTask);
		}
	}

	private Runnable newUpdateTask() {
		return new Runnable() {
			@Override
			public void run() {
				while(true){
					StoreLog storeLog = null;
					try {
						storeLog = dataQueue.poll(1000, TimeUnit.SECONDS);
						if(storeLog!=null){
							LogHandleMgr.getInstance().handle(storeLog);
						}
					} catch (Throwable e) {
						MainLog.error(LogModule.Tmp, null, "LogAsynHandler 处理StoreLog失败",e);
					}				
				}
			}
		};
	}
}

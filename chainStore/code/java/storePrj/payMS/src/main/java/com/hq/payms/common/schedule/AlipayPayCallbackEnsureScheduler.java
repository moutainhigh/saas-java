package com.hq.payms.common.schedule;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.hq.payms.service.alipay.bs.handle.AlipayPayCallbackHelper;
import com.hq.payms.service.alipayRecord.bs.AlipayRecordMgr;
import com.hq.payms.service.alipayRecord.data.AlipayRecord;
import com.hq.payms.zenmind.zmAlipay.common.ZmAlipayCfg;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * 支付宝支付结果回调通知storeMS的保障调度
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class AlipayPayCallbackEnsureScheduler {
	
	public static AlipayPayCallbackEnsureScheduler getInstance(){		
		return HotSwap.getInstance().getSingleton(AlipayPayCallbackEnsureScheduler.class);
	}
	
	private BlockingQueue<AlipayRecord>  dataQueue = new LinkedBlockingQueue<AlipayRecord>(1024 * 10);
	
	public void init(ZmAlipayCfg zmAlipayCfg){
		int nThreads = zmAlipayCfg.getAlipayOrderQueryNThread();
		if(nThreads<4){
			//最少4条线程
			nThreads = 4;
		}
		initPutData2QueueService(zmAlipayCfg);
		initUpdateService(nThreads);
		
	}

	private void initPutData2QueueService(ZmAlipayCfg zmAlipayCfg) {
		ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutor.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				try {
					putData2Queue(zmAlipayCfg);					
				} catch (Throwable e) {
					System.out.println(new RuntimeException("AlipayPayCallbackEnsureScheduler putData2Queue error", e));
					e.printStackTrace();
				}
				
			}
		}, 10, 10, TimeUnit.SECONDS); //初始延迟10秒，后续每10秒执行一次
	}
	
	private void putData2Queue(ZmAlipayCfg zmAlipayCfg) throws Exception{
		/*
		 * 查询支付成功，但未通知storeMS成功, 且回调通知次数小于callbackMaxCount的记录
		 */
		int callbackMaxCount = zmAlipayCfg.getAlipayCallbackMaxCount();
		List<AlipayRecord> list = AlipayRecordMgr.getInstance().findNoCallbackSuccess(callbackMaxCount);
		for (AlipayRecord alipayRecord : list) {
			dataQueue.put(alipayRecord);
		}
	}
	
	private void putDataBack2Queue(AlipayRecord payRecord) {
		if(payRecord != null){
			try {
				dataQueue.put(payRecord); //放回队列
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
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
					AlipayRecord payRecord = null;
					try {
						payRecord = dataQueue.poll(1000, TimeUnit.SECONDS);
						doPayCallbackTask(payRecord);
					} catch (Throwable e) {
						putDataBack2Queue(payRecord);
						System.out.println(new RuntimeException("AlipayPayCallbackEnsureScheduler doPayCallbackTask error", e));
						e.printStackTrace();
					}				
				}
			}
		};
	}
	
	private void doPayCallbackTask(AlipayRecord payRecord) throws Exception {
		if(payRecord == null) return;
		//回调通知调用方微服务
		AlipayPayCallbackHelper.getInstance().callback(payRecord);
	}

}

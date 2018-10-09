package com.hq.payms.common.schedule;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.hq.payms.service.wxpay.bs.handle.WxpayPayCallbackHelper;
import com.hq.payms.service.wxpayRecord.bs.WxpayRecordMgr;
import com.hq.payms.service.wxpayRecord.data.WxpayRecord;
import com.hq.payms.zenmind.zmWxpay.common.ZmWXPayCfg;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * 微信支付结果回调通知storeMS的保障调度
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class WxpayPayCallbackEnsureScheduler {
	
	public static WxpayPayCallbackEnsureScheduler getInstance(){		
		return HotSwap.getInstance().getSingleton(WxpayPayCallbackEnsureScheduler.class);
	}
	
	private BlockingQueue<WxpayRecord>  dataQueue = new LinkedBlockingQueue<WxpayRecord>(1024 * 10);
	
	public void init(ZmWXPayCfg zmWXPayCfg){
		int nThreads = zmWXPayCfg.getWxpayOrderQueryNThread();
		if(nThreads<4){
			//最少4条线程
			nThreads = 4;
		}
		initPutData2QueueService(zmWXPayCfg);
		initUpdateService(nThreads);
		
	}

	private void initPutData2QueueService(ZmWXPayCfg zmWXPayCfg) {
		ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutor.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				try {
					putData2Queue(zmWXPayCfg);					
				} catch (Throwable e) {
					System.out.println(new RuntimeException("WxpayPayCallbackEnsureScheduler putData2Queue error", e));
					e.printStackTrace();
				}
				
			}
		}, 10, 10, TimeUnit.SECONDS); //初始延迟10秒，后续每10秒执行一次
	}
	
	private void putData2Queue(ZmWXPayCfg zmWXPayCfg) throws Exception{
		/*
		 * 查询支付成功，但未通知storeMS成功,且回调通知次数小于callbackMaxCount的记录
		 */
		int callbackMaxCount = zmWXPayCfg.getWxpayCallbackMaxCount();
		List<WxpayRecord> list = WxpayRecordMgr.getInstance().findNoCallbackSuccess(callbackMaxCount);
		for (WxpayRecord alipayRecord : list) {
			dataQueue.put(alipayRecord);
		}
	}
	
	private void putDataBack2Queue(WxpayRecord payRecord) {
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
					WxpayRecord payRecord = null;
					try {
						payRecord = dataQueue.poll(1000, TimeUnit.SECONDS);
						doPayCallbackTask(payRecord);
					} catch (Throwable e) {
						putDataBack2Queue(payRecord);
						System.out.println(new RuntimeException("WxpayPayCallbackEnsureScheduler doPayCallbackTask error", e));
						e.printStackTrace();
					}				
				}
			}
		};
	}
	
	private void doPayCallbackTask(WxpayRecord payRecord) throws Exception {
		if(payRecord == null) return;
		//回调通知调用方微服务
		WxpayPayCallbackHelper.getInstance().callback(payRecord);
	}

}

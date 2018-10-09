package com.hq.payms.common.schedule;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.alipay.demo.trade.model.result.AlipayF2FQueryResult;
import com.hq.payms.service.alipay.bs.AlipayMgr;
import com.hq.payms.service.alipay.bs.handle.AlipayBuildReqHelper;
import com.hq.payms.service.alipay.bs.handle.AlipayPayCallbackHelper;
import com.hq.payms.service.alipay.data.TradeQueryReq;
import com.hq.payms.service.alipayRecord.bs.AlipayRecordMgr;
import com.hq.payms.service.alipayRecord.data.AlipayRecord;
import com.hq.payms.zenmind.zmAlipay.common.ZmAlipayCfg;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * 支付宝支付结果查询调度
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class AlipayOrderQueryScheduler {
	
	public static AlipayOrderQueryScheduler getInstance(){		
		return HotSwap.getInstance().getSingleton(AlipayOrderQueryScheduler.class);
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
					System.out.println(new RuntimeException("AlipayOrderQueryScheduler putData2Queue error", e));
					e.printStackTrace();
				}
				
			}
		}, 10, 10, TimeUnit.SECONDS); //初始延迟10秒，后续每10秒执行一次
	}
	
	private void putData2Queue(ZmAlipayCfg zmAlipayCfg) throws Exception{
		/*
		 * 查询未支付成功的记录
		 * AlipayRecord也自定义一个超时时间，避免一直查询
		 */
		int authorityNotifyTimeout = zmAlipayCfg.getAlipayAuthorityNotifyTimeout();
		int timeoutExpress = zmAlipayCfg.getAlipayTimeoutExpress();
		List<AlipayRecord> list = AlipayRecordMgr.getInstance().findNoPaySuccessByRange(authorityNotifyTimeout, timeoutExpress);
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
						doOrderQueryTask(payRecord);
					} catch (Throwable e) {
						putDataBack2Queue(payRecord);
						System.out.println(new RuntimeException("AlipayOrderQueryScheduler doOrderQueryTask error", e));
						e.printStackTrace();
					}				
				}
			}
		};
	}
	
	private void doOrderQueryTask(AlipayRecord payRecord) throws Exception {
		if(payRecord == null) return;
		//调支付宝查询订单的接口，看是否支付成功，如成功，则更新商户侧订单状态为支付成功
		TradeQueryReq reqParam = AlipayBuildReqHelper.getInstance()
				.buildTradeQueryReq(payRecord.getStoreId(), payRecord.getOutTradeNo());
		AlipayF2FQueryResult result = AlipayMgr.getInstance().doTradeQuery(reqParam);
		
		payRecord.setTradeStatus(result.getResponse().getTradeStatus());
		payRecord.setTradeNo(result.getResponse().getTradeNo());
		
		if(result.isTradeSuccess()) { //交易成功; 如果一直不成功，支付宝会超时关闭订单，此时查询自动终止
			payRecord.setPaySuccessTime(System.currentTimeMillis());
			AlipayRecordMgr.getInstance().update(payRecord);
			
			//回调通知调用方微服务
			AlipayPayCallbackHelper.getInstance().callback(payRecord);
			
		}else if(result.isTradeClosed()) { //交易关闭,记录交易状态，以终止查询
			AlipayRecordMgr.getInstance().update(payRecord);
		}
		
		
	}

}

package com.hq.payms.common.schedule;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.hq.payms.service.wxpay.bs.WxpayMgr;
import com.hq.payms.service.wxpay.bs.handle.WxpayBuildReqHelper;
import com.hq.payms.service.wxpay.bs.handle.WxpayPayCallbackHelper;
import com.hq.payms.service.wxpay.data.OrderQueryOrCloseReq;
import com.hq.payms.service.wxpay.data.OrderQueryResp;
import com.hq.payms.service.wxpayRecord.bs.WxpayRecordMgr;
import com.hq.payms.service.wxpayRecord.data.WxpayRecord;
import com.hq.payms.zenmind.zmWxpay.common.ZmWXPayCfg;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * 微信支付结果查询调度<br>
 * 
 * 扫码支付：<br>
 * 1、5分钟内，没有收到微信支付成功通知，建议商户主动调查询订单API；<br>
 * 2、以查单结果为准，订单支付成功，建议商户置商户侧订单为支付成功，给用户发货。<br>
 * 
 * 刷卡支付：<br>
 * 提交支付请求后微信会同步返回支付结果。当返回结果为“系统错误”时，商户系统等待5秒后调用【查询订单API】，查询支付实际交易结果；<br>
 * 当返回结果为“USERPAYING”时，商户系统可设置间隔时间(建议10秒)重新查询支付结果，直到支付成功或超时(建议30秒)；<br>
 * 
 * 刷卡支付的验证密码规则:<br>
 * ◆ 支付金额>1000元的交易需要验证用户支付密码 
 * ◆ 用户账号每天最多有5笔交易可以免密，超过后需要验证密码 
 * ◆ 微信支付后台判断用户支付行为有异常情况，符合免密规则的交易也会要求验证密码 
 * 注：基于一定的风控策略，存在随时需要验密的可能性。 
 * 
 * 总体来说，都是以主动查询的结果为准
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class WxpayOrderQueryScheduler {
	
	public static WxpayOrderQueryScheduler getInstance(){		
		return HotSwap.getInstance().getSingleton(WxpayOrderQueryScheduler.class);
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
					System.out.println(new RuntimeException("WxpayOrderQueryScheduler putData2Queue error", e));
					e.printStackTrace();
				}
				
			}
		}, 10, 10, TimeUnit.SECONDS); //初始延迟10秒，后续每10秒执行一次
	}
	
	private void putData2Queue(ZmWXPayCfg zmWXPayCfg) throws Exception{
		/*
		 * 查询未支付成功的记录
		 * 微信扫码支付生成二维码时不生成内部交易号，如果用户一直不支付，主动查询时可能无查询结果，所以自己定义一个超时时间，避免一直查询
		 */
		int authorityNotifyTimeout = zmWXPayCfg.getWxpayAuthorityNotifyTimeout();
		int wxpayTimeExpire = zmWXPayCfg.getWxpayTimeExpire();
		List<WxpayRecord> list = WxpayRecordMgr.getInstance().findNoPaySuccessByRange(authorityNotifyTimeout, wxpayTimeExpire);
		for (WxpayRecord wxpayRecord : list) {
			dataQueue.put(wxpayRecord);
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
						doOrderQueryTask(payRecord);
					} catch (Throwable e) {
						putDataBack2Queue(payRecord);
						
						System.out.println(new RuntimeException("WxpayOrderQueryScheduler doOrderQueryTask error", e));
						e.printStackTrace();
					}				
				}
			}
		};
	}
	
	private void doOrderQueryTask(WxpayRecord payRecord) throws Exception {
		if(payRecord == null) return;
		//调微信查询订单的接口，看是否支付成功，如成功，则更新商户侧订单状态为支付成功
		OrderQueryOrCloseReq reqParam = WxpayBuildReqHelper.getInstance()
				.buildOrderQueryOrCloseReq(payRecord.getStoreId(), payRecord.getOut_trade_no());
		OrderQueryResp orderQueryResp = WxpayMgr.getInstance().doOrderQuery(reqParam);
		
		payRecord.setTrade_state(orderQueryResp.getTrade_state());
		if(orderQueryResp.isTradeSuccess()) { //交易成功; 如果一直不成功，微信会超时关闭订单，此时查询自动终止
			payRecord.setPaySuccessTime(System.currentTimeMillis());
			payRecord.setTransaction_id(orderQueryResp.getTransaction_id());
			WxpayRecordMgr.getInstance().update(payRecord);
			
			//回调通知调用方微服务
			WxpayPayCallbackHelper.getInstance().callback(payRecord);
			
		}else if(orderQueryResp.isTradeClose()) { //交易关闭,记录交易状态，以终止查询
			WxpayRecordMgr.getInstance().update(payRecord);
		}
		
	}

}

package com.hq.payms.service.wxpay.bs.handle;

import com.hq.payms.service.pay.apiData.callback.PayCallbackForm;
import com.hq.payms.service.pay.bs.PayMgr;
import com.hq.payms.service.wxpayRecord.bs.WxpayRecordMgr;
import com.hq.payms.service.wxpayRecord.data.WxpayRecord;
import com.zenmind.common.asynExecutor.AsynExecutor;
import com.zenmind.common.asynExecutor.IntfAsynTask;
import com.zenmind.common.asynExecutor.IntfErrorHandler;
import com.zenmind.common.hotSwap.HotSwap;

public class WxpayPayCallbackHelper {
	
	public static WxpayPayCallbackHelper getInstance() {
		return HotSwap.getInstance().getSingleton(WxpayPayCallbackHelper.class);
	}
	
	private WxpayPayCallbackHelper() {
		init(10);
	}
	
	private AsynExecutor<WxpayRecord> asynExecutor;
	
	private void init(int nThreadsP){
		if(nThreadsP < 10) {
			nThreadsP = 10;
		}
		final int queueSize = 1024*10;
		IntfAsynTask<WxpayRecord> task = new IntfAsynTask<WxpayRecord>(){
			@Override
			public void doTask(WxpayRecord payRecord) {
				doCallbackTask(payRecord);
			}
			
		};
		IntfErrorHandler<WxpayRecord> errorHandler = new IntfErrorHandler<WxpayRecord>() {
			@Override
			public void handle(WxpayRecord target, String msg, Throwable e) {
				e.printStackTrace();
			}
		};
		asynExecutor = AsynExecutor.newInstance(task, errorHandler, queueSize, nThreadsP);
		asynExecutor.init();
	}
	
	/**
	 * 同步执行
	 * @param payRecord
	 */
	public void callback(WxpayRecord payRecord) {
		doCallbackTask(payRecord);
	}

    /**
     * 异步执行
     * @param payRecord
     */
	public void asyncCallback(WxpayRecord payRecord) {
		asynExecutor.addData(payRecord);
    }
	
    private void doCallbackTask(WxpayRecord payRecord) {
		//判断是否已通知成功过; 要重新查询数据库，避免多次payCallback
		WxpayRecord payRecordNow = WxpayRecordMgr.getInstance().findByOutTradeNo(payRecord.getOut_trade_no());
		if(payRecordNow.getCallbackSuccessTime() > 0L) {
			return;
		}
		//回调通知调用方微服务,回传outTradeNo和微信的transaction_id
		PayCallbackForm form = PayCallbackForm.fromWxpayRecord(payRecordNow);
		boolean callbackSuccess = PayMgr.getInstance().payCallback(form);
		//记录通知次数
		payRecordNow.setCallbackCount(payRecordNow.getCallbackCount() + 1);
		//通知成功，记录时间
		if(callbackSuccess) {
			payRecordNow.setCallbackSuccessTime(System.currentTimeMillis());
		}
		WxpayRecordMgr.getInstance().update(payRecordNow);
	}
}

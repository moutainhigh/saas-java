package com.hq.payms.service.alipay.bs.handle;

import com.hq.payms.service.alipayRecord.bs.AlipayRecordMgr;
import com.hq.payms.service.alipayRecord.data.AlipayRecord;
import com.hq.payms.service.pay.apiData.callback.PayCallbackForm;
import com.hq.payms.service.pay.bs.PayMgr;
import com.zenmind.common.asynExecutor.AsynExecutor;
import com.zenmind.common.asynExecutor.IntfAsynTask;
import com.zenmind.common.asynExecutor.IntfErrorHandler;
import com.zenmind.common.hotSwap.HotSwap;

public class AlipayPayCallbackHelper {
	
	public static AlipayPayCallbackHelper getInstance() {
		return HotSwap.getInstance().getSingleton(AlipayPayCallbackHelper.class);
	}

	private AlipayPayCallbackHelper() {
		init(10);
	}
	
	private AsynExecutor<AlipayRecord> asynExecutor;
	
	private void init(int nThreadsP){
		if(nThreadsP < 10) {
			nThreadsP = 10;
		}
		final int queueSize = 1024*10;
		IntfAsynTask<AlipayRecord> task = new IntfAsynTask<AlipayRecord>(){
			@Override
			public void doTask(AlipayRecord payRecord) {
				doCallbackTask(payRecord);
			}
		};
		IntfErrorHandler<AlipayRecord> errorHandler = new IntfErrorHandler<AlipayRecord>() {
			@Override
			public void handle(AlipayRecord target, String msg, Throwable e) {
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
	public void callback(AlipayRecord payRecord) {
		doCallbackTask(payRecord);
	}

    /**
     * 异步执行
     * @param payRecord
     */
	public void asyncCallback(AlipayRecord payRecord) {
		asynExecutor.addData(payRecord);
    }
    
    private void doCallbackTask(AlipayRecord payRecord) {
		//判断是否已通知成功过; 要重新查询数据库，避免多次payCallback
		AlipayRecord payRecordNow = AlipayRecordMgr.getInstance().findByOutTradeNo(payRecord.getOutTradeNo());
		if(payRecordNow.getCallbackSuccessTime() > 0L) {
			return;
		}
		//回调通知调用方微服务,根据订单来源回调,回传outTradeNo和支付宝的tradeNo
		PayCallbackForm form = PayCallbackForm.fromAlipayRecord(payRecordNow);
		boolean callbackSuccess = PayMgr.getInstance().payCallback(form);
		//记录通知次数
		payRecordNow.setCallbackCount(payRecordNow.getCallbackCount() + 1);
		//通知成功，记录时间
		if(callbackSuccess) {
			payRecordNow.setCallbackSuccessTime(System.currentTimeMillis());
			
		}
		AlipayRecordMgr.getInstance().update(payRecordNow);
	}
    
}

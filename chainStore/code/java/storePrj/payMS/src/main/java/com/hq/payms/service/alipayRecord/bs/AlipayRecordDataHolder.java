package com.hq.payms.service.alipayRecord.bs;

import java.util.List;

import com.hq.payms.service.alipayRecord.data.AlipayRecord;
import com.hq.payms.service.alipayRecord.data.AlipayRecordDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class AlipayRecordDataHolder {

	public static AlipayRecordDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(AlipayRecordDataHolder.class);
	}

	public List<AlipayRecord> findListByOutTradeNo(String outTradeNo) {
		return AlipayRecordDAO.getInstance().findListByOutTradeNo(outTradeNo);
	}

	/**
	 * 业务层一定要区分是add还是update,用此方法的时候id必须是long型的自增字段
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void addAndReturnId(AlipayRecord target) {
		AlipayRecordDAO.getInstance().addAndReturnId(target);
	}
	

	/**
	 * 业务层一定要区分是add还是update
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void updpate(AlipayRecord target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		AlipayRecordDAO.getInstance().updpate(target);
	}
	
	public void delete(long id) {
		AlipayRecordDAO.getInstance().delete(id);
	}

	public AlipayRecord get(long id) {
		return	AlipayRecordDAO.getInstance().get(id);
	}

	public List<AlipayRecord> findNoPaySuccessByMinutes(int period) {
		return	AlipayRecordDAO.getInstance().findNoPaySuccessByMinutes(period);
	}
	
	public List<AlipayRecord> findNoPaySuccess() {
		return	AlipayRecordDAO.getInstance().findNoPaySuccess();
	}
	
	
	/**
	 * 查询支付成功，但未通知storeMS成功的记录
	 * @return
	 */
	public List<AlipayRecord> findNoCallbackSuccess() {
		return AlipayRecordDAO.getInstance().findNoCallbackSuccess(); 
	}
	
	/**
	 * 查询支付成功，但未通知storeMS成功，且回调通知次数小于callbackCount的记录
	 * @param callbackMaxCount 通知的最大次数
	 * @return
	 */
	public List<AlipayRecord> findNoCallbackSuccess(int callbackMaxCount) {
		return AlipayRecordDAO.getInstance().findNoCallbackSuccess(callbackMaxCount); 
	}
	
	
	/**
	 * 查询大于官方回调通知超时时间，且小于交易超时时间，的未支付成功的记录
	 * @param authorityNotifyTimeoutSeconds 单位秒
	 * @param tradeTimeoutSeconds 单位秒
	 * @return
	 */
	public List<AlipayRecord> findNoPaySuccessByRange(int authorityNotifyTimeoutSeconds, int tradeTimeoutSeconds) {
		return AlipayRecordDAO.getInstance().findNoPaySuccessByRange(authorityNotifyTimeoutSeconds, tradeTimeoutSeconds); 
	}
	
}

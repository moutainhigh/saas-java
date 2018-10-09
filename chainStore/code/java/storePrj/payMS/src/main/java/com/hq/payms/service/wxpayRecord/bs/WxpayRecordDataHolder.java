package com.hq.payms.service.wxpayRecord.bs;

import java.util.List;

import com.hq.payms.service.wxpayRecord.data.WxpayRecord;
import com.hq.payms.service.wxpayRecord.data.WxpayRecordDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class WxpayRecordDataHolder {

	public static WxpayRecordDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(WxpayRecordDataHolder.class);
	}

	public List<WxpayRecord> findListByOutTradeNo(String out_trade_no) {
		return WxpayRecordDAO.getInstance().findListByOutTradeNo(out_trade_no);
	}

	/**
	 * 业务层一定要区分是add还是update,用此方法的时候id必须是long型的自增字段
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void addAndReturnId(WxpayRecord target) {
		WxpayRecordDAO.getInstance().addAndReturnId(target);
	}
	

	/**
	 * 业务层一定要区分是add还是update
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void updpate(WxpayRecord target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		WxpayRecordDAO.getInstance().updpate(target);
	}
	
	public void delete(long id) {
		WxpayRecordDAO.getInstance().delete(id);
	}

	public WxpayRecord get(long id) {
		return	WxpayRecordDAO.getInstance().get(id);
	}

	public List<WxpayRecord> findNoPaySuccessByMinutes(int period) {
		return	WxpayRecordDAO.getInstance().findNoPaySuccessByMinutes(period);
	}
	
	public List<WxpayRecord> findNoPaySuccess() {
		return	WxpayRecordDAO.getInstance().findNoPaySuccess();
	}
	
	/**
	 * 查询大于微信官方回调通知超时时间，且小于交易超时时间，的未支付成功的记录
	 * @param authorityNotifyTimeoutSeconds 单位秒
	 * @param tradeTimeoutSeconds 单位秒
	 * @return
	 */
	public List<WxpayRecord> findNoPaySuccessByRange(int authorityNotifyTimeoutSeconds, int tradeTimeoutSeconds) {
		return	WxpayRecordDAO.getInstance().findNoPaySuccessByRange(authorityNotifyTimeoutSeconds, tradeTimeoutSeconds);
	}
	
	/**
	 * 查询支付成功，但未通知storeMS成功的记录
	 * @return
	 */
	public List<WxpayRecord> findNoCallbackSuccess() {
		return WxpayRecordDAO.getInstance().findNoCallbackSuccess();
	}
	
	/**
	 * 查询支付成功，但未通知storeMS成功，且回调通知次数小于callbackMaxCount的记录
	 * @param callbackMaxCount 通知的最大次数
	 * @return
	 */
	public List<WxpayRecord> findNoCallbackSuccess(int callbackMaxCount) {
		return WxpayRecordDAO.getInstance().findNoCallbackSuccess(callbackMaxCount);
	}
}

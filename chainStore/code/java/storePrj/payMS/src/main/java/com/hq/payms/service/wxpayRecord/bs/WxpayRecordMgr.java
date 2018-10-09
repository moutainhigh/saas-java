package com.hq.payms.service.wxpayRecord.bs;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.payms.service.wxpayRecord.data.WxpayRecord;
import com.zenmind.common.hotSwap.HotSwap;

public class WxpayRecordMgr {

	public static WxpayRecordMgr getInstance(){
		return HotSwap.getInstance().getSingleton(WxpayRecordMgr.class);
	}

	public WxpayRecord findByOutTradeNo(String out_trade_no) {
		List<WxpayRecord> wxpayRecordList = WxpayRecordDataHolder.getInstance().findListByOutTradeNo(out_trade_no);
		if (CollectionUtils.isEmpty(wxpayRecordList)) {
			return null;
		}
		Collections.sort(wxpayRecordList, new Comparator<WxpayRecord>() {
			@Override
			public int compare(WxpayRecord o1, WxpayRecord o2) {
				if (o1.getId() > o2.getId())
					return -1;
				else if (o1.getId() < o2.getId())
					return 1;
				else
					return 0;
			}
		});
		return wxpayRecordList.get(0);
	}
	
	public WxpayRecord add(WxpayRecord target) {
		WxpayRecordDataHolder.getInstance().addAndReturnId(target);
		return target;
	}
	/**
	 * 业务层一定要区分是add还是update
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void update(WxpayRecord target) {
		WxpayRecordDataHolder.getInstance().updpate(target);
	}
	
	public WxpayRecord get(long id) {
		return WxpayRecordDataHolder.getInstance().get(id);
	}

	/**
	 * 查询指定分钟内未支付成功的记录
	 * @param period
	 * @return
	 */
	public List<WxpayRecord> findNoPaySuccessByMinutes(int period) {
		return WxpayRecordDataHolder.getInstance().findNoPaySuccessByMinutes(period);
	}
	
	/**
	 * 查询未支付成功的支付记录
	 * @return
	 */
	public List<WxpayRecord> findNoPaySuccess() {
		return WxpayRecordDataHolder.getInstance().findNoPaySuccess();
	}
	
	/**
	 * 查询大于微信官方回调通知超时时间，且小于交易超时时间，的未支付成功的记录
	 * @param authorityNotifyTimeoutSeconds 单位秒
	 * @param tradeTimeoutSeconds 单位秒
	 * @return
	 */
	public List<WxpayRecord> findNoPaySuccessByRange(int authorityNotifyTimeoutSeconds, int tradeTimeoutSeconds) {
		return	WxpayRecordDataHolder.getInstance().findNoPaySuccessByRange(authorityNotifyTimeoutSeconds, tradeTimeoutSeconds);
	}
	
	/**
	 * 查询支付成功，但未通知storeMS成功的记录
	 * @return
	 */
	public List<WxpayRecord> findNoCallbackSuccess() {
		return WxpayRecordDataHolder.getInstance().findNoCallbackSuccess();
	}
	
	/**
	 * 查询支付成功，但未通知storeMS成功，且回调通知次数小于callbackMaxCount的记录
	 * @param callbackMaxCount 通知的最大次数
	 * @return
	 */
	public List<WxpayRecord> findNoCallbackSuccess(int callbackMaxCount) {
		return WxpayRecordDataHolder.getInstance().findNoCallbackSuccess(callbackMaxCount);
	}
}

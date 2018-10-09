package com.hq.payms.service.alipayRecord.bs;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.payms.service.alipayRecord.data.AlipayRecord;
import com.zenmind.common.hotSwap.HotSwap;

public class AlipayRecordMgr {

	public static AlipayRecordMgr getInstance(){
		return HotSwap.getInstance().getSingleton(AlipayRecordMgr.class);
	}

	public AlipayRecord findByOutTradeNo(String outTradeNo) {
		List<AlipayRecord> wxpayRecordList = AlipayRecordDataHolder.getInstance().findListByOutTradeNo(outTradeNo);
		if (CollectionUtils.isEmpty(wxpayRecordList)) {
			return null;
		}
		Collections.sort(wxpayRecordList, new Comparator<AlipayRecord>() {
			@Override
			public int compare(AlipayRecord o1, AlipayRecord o2) {
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
	
	public AlipayRecord add(AlipayRecord target) {
		AlipayRecordDataHolder.getInstance().addAndReturnId(target);
		return target;
	}
	/**
	 * 业务层一定要区分是add还是update
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void update(AlipayRecord target) {
		AlipayRecordDataHolder.getInstance().updpate(target);
	}
	
	public AlipayRecord get(long id) {
		return AlipayRecordDataHolder.getInstance().get(id);
	}

	/**
	 * 查询指定分钟内未支付成功的记录
	 * @param period
	 * @return
	 */
	public List<AlipayRecord> findNoPaySuccessByMinutes(int period) {
		return AlipayRecordDataHolder.getInstance().findNoPaySuccessByMinutes(period);
	}
	
	/**
	 * 查询未支付成功的支付记录
	 * @return
	 */
	public List<AlipayRecord> findNoPaySuccess() {
		return AlipayRecordDataHolder.getInstance().findNoPaySuccess();
	}
	
	/**
	 * 查询支付成功，但未通知storeMS成功的记录
	 * @return
	 */
	public List<AlipayRecord> findNoCallbackSuccess() {
		return AlipayRecordDataHolder.getInstance().findNoCallbackSuccess(); 
	}
	
	/**
	 * 查询支付成功，但未通知storeMS成功，且回调通知次数小于callbackMaxCount的记录
	 * @param callbackMaxCount 通知的最大次数
	 * @return
	 */
	public List<AlipayRecord> findNoCallbackSuccess(int callbackMaxCount) {
		return AlipayRecordDataHolder.getInstance().findNoCallbackSuccess(callbackMaxCount); 
	}

	/**
	 * 查询大于官方回调通知超时时间，且小于交易超时时间，的未支付成功的记录
	 * @param authorityNotifyTimeoutSeconds 单位秒
	 * @param tradeTimeoutSeconds 单位秒
	 * @return
	 */
	public List<AlipayRecord> findNoPaySuccessByRange(int authorityNotifyTimeoutSeconds, int tradeTimeoutSeconds) {
		return AlipayRecordDataHolder.getInstance().findNoPaySuccessByRange(authorityNotifyTimeoutSeconds, tradeTimeoutSeconds); 
	}
}

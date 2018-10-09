package com.hq.payms.service.alipayRecord.data;

import java.util.Collections;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.payms.zenmind.zmAlipay.common.ZmAlipayConstants;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class AlipayRecordDAO extends MongodbDao<AlipayRecord> {

	public static AlipayRecordDAO getInstance(){
		return HotSwap.getInstance().getSingleton(AlipayRecordDAO.class);
	}
	
	/**
	 * 一个订单对应多条支付记录时，会抛异常
	 * @param out_trade_no
	 * @return
	 */
	@Deprecated
	public AlipayRecord findByOutTradeNo(String outTradeNo){
		Criteria criteria = new Criteria();  
		criteria.and("outTradeNo").is(outTradeNo);
	    Query query = new Query(criteria);  
		return findOne(query);
	}
	
	public List<AlipayRecord> findListByOutTradeNo(String outTradeNo){
		Criteria criteria = new Criteria();  
		criteria.and("outTradeNo").is(outTradeNo);
	    Query query = new Query(criteria);  
		return find(query);
	}

	/**
	 * 查询指定分钟内未支付成功的记录
	 * @param period
	 * @return
	 */
	public List<AlipayRecord> findNoPaySuccessByMinutes(int period) {
		if(period <= 0) {
			return Collections.emptyList();
		}
		final int _1MIN =  1000 * 60;
		Criteria criteria = new Criteria();
		criteria.and("tradeStatus").is(ZmAlipayConstants.TS_WAIT_BUYER_PAY);
		criteria.and("paySuccessTime").is(0L);
		criteria.and("createdTime").gt(System.currentTimeMillis() - period * _1MIN);
		Query query = new Query(criteria);  
		return find(query);
	}
	
	/**
	 * 查询大于官方回调通知超时时间，且小于交易超时时间，的未支付成功的记录
	 * @param authorityNotifyTimeoutSeconds 单位秒
	 * @param tradeTimeoutSeconds 单位秒
	 * @return
	 */
	public List<AlipayRecord> findNoPaySuccessByRange(int authorityNotifyTimeoutSeconds, int tradeTimeoutSeconds) {
		if(authorityNotifyTimeoutSeconds <= 0 || tradeTimeoutSeconds <= 0) {
			return Collections.emptyList();
		}
		Criteria criteria = new Criteria();
		criteria.and("tradeStatus").in(ZmAlipayConstants.TS_WAIT_BUYER_PAY);
		criteria.and("paySuccessTime").is(0L);
		criteria.and("createdTime").lt(System.currentTimeMillis() - authorityNotifyTimeoutSeconds * 1000)
			.gt(System.currentTimeMillis() - tradeTimeoutSeconds * 1000);
		Query query = new Query(criteria);  
		return find(query);
	}
	
	/**
	 * 查询仍在支付中的支付记录
	 * @return
	 */
	public List<AlipayRecord> findNoPaySuccess() {
		Criteria criteria = new Criteria();
		criteria.and("tradeStatus").is(ZmAlipayConstants.TS_WAIT_BUYER_PAY);
		criteria.and("paySuccessTime").is(0L);
		Query query = new Query(criteria);  
		return find(query);
	}
	
	/**
	 * 查询支付成功，但未通知storeMS成功的记录
	 * @return
	 */
	public List<AlipayRecord> findNoCallbackSuccess() {
		Criteria criteria = new Criteria();
		criteria.and("paySuccessTime").gt(0L);
		criteria.and("callbackSuccessTime").is(0L);
		Query query = new Query(criteria);  
		return find(query);
	}
	
	/**
	 * 查询支付成功，但未通知storeMS成功，且回调通知次数小于callbackMaxCount的记录
	 * @param callbackMaxCount 通知的最大次数
	 * @return
	 */
	public List<AlipayRecord> findNoCallbackSuccess(int callbackMaxCount) {
		Criteria criteria = new Criteria();
		criteria.and("paySuccessTime").gt(0L);
		criteria.and("callbackSuccessTime").is(0L);
		criteria.and("callbackCount").lt(callbackMaxCount);
		Query query = new Query(criteria);  
		return find(query);
	}
}
package com.hq.payms.service.wxpayRecord.data;

import java.util.Collections;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.payms.zenmind.zmWxpay.common.ZmWXPayConstants;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class WxpayRecordDAO extends MongodbDao<WxpayRecord> {

	public static WxpayRecordDAO getInstance(){
		return HotSwap.getInstance().getSingleton(WxpayRecordDAO.class);
	}
	
	/**
	 * 一个订单对应多条支付记录时，会抛异常
	 * @param out_trade_no
	 * @return
	 */
	@Deprecated
	public WxpayRecord findByOutTradeNo(String out_trade_no){
		Criteria criteria = new Criteria();  
		criteria.and("out_trade_no").is(out_trade_no);
	    Query query = new Query(criteria);  
		return findOne(query);
	}
	
	public List<WxpayRecord> findListByOutTradeNo(String out_trade_no){
		Criteria criteria = new Criteria();  
		criteria.and("out_trade_no").is(out_trade_no);
	    Query query = new Query(criteria);  
		return find(query);
	}

	/**
	 * 查询指定分钟内未支付成功的记录
	 * @param period
	 * @return
	 */
	public List<WxpayRecord> findNoPaySuccessByMinutes(int period) {
		if(period <= 0) {
			return Collections.emptyList();
		}
		final int _1MIN =  1000 * 60;
		Criteria criteria = new Criteria();
		criteria.and("trade_state").in(ZmWXPayConstants.TS_NOTPAY, ZmWXPayConstants.TS_USERPAYING);
		criteria.and("paySuccessTime").is(0L);
		criteria.and("createdTime").gt(System.currentTimeMillis() - period * _1MIN);
		Query query = new Query(criteria);  
		return find(query);
	}
	
	/**
	 * 查询大于官方回调通知超时时间，且小于交易超时时间，的未支付成功的记录<br>
	 * @param authorityNotifyTimeoutSeconds 单位秒
	 * @param tradeTimeoutSeconds 单位秒
	 * @return
	 */
	public List<WxpayRecord> findNoPaySuccessByRange(int authorityNotifyTimeoutSeconds, int tradeTimeoutSeconds) {
		if(authorityNotifyTimeoutSeconds <= 0 || tradeTimeoutSeconds <= 0) {
			return Collections.emptyList();
		}
		Criteria criteria = new Criteria();
		criteria.and("trade_state").in(ZmWXPayConstants.TS_NOTPAY, ZmWXPayConstants.TS_USERPAYING);
		criteria.and("paySuccessTime").is(0L);
		criteria.and("createdTime").lt(System.currentTimeMillis() - authorityNotifyTimeoutSeconds * 1000)
			.gt(System.currentTimeMillis() - tradeTimeoutSeconds * 1000);
		Query query = new Query(criteria);  
		return find(query);
	}
	
	/**
	 * 查询未支付成功的支付记录
	 * @return
	 */
	public List<WxpayRecord> findNoPaySuccess() {
		Criteria criteria = new Criteria();
		criteria.and("trade_state").in(ZmWXPayConstants.TS_NOTPAY, ZmWXPayConstants.TS_USERPAYING);
		criteria.and("paySuccessTime").is(0L);
		Query query = new Query(criteria);  
		return find(query);
	}
	
	/**
	 * 查询支付成功，但未通知storeMS成功的记录
	 * @return
	 */
	public List<WxpayRecord> findNoCallbackSuccess() {
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
	public List<WxpayRecord> findNoCallbackSuccess(int callbackMaxCount) {
		Criteria criteria = new Criteria();
		criteria.and("paySuccessTime").gt(0L);
		criteria.and("callbackSuccessTime").is(0L);
		criteria.and("callbackCount").lt(callbackMaxCount);
		Query query = new Query(criteria);  
		return find(query);
	}
	
}
package com.hq.storeMS.service.billRecord.apiData;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.util.AppUtils;

public class BillRecordQueryForm {
	private long storeId;
	private String tradeNoOrName;
	private long orderId;
	private String leaguerId;
	private Set<Integer> payType = new HashSet<Integer>();//支付方式 PayTypeEnum
	private int billType;//交易类型 BillTypeEnum
	private int state;//状态 TradeStateEnum
	private long maxTime;
	private long minTime;

	private int pageItemCount;
	private int pageNo;

	public static BillRecordQueryForm newInstance() {
		return new BillRecordQueryForm();
	}
	
	public String getListId() {
		return AppUtils.joinByUnderline(storeId, maxTime, minTime, orderId);
	}

	public long getStoreId() {
		return storeId;
	}
	
	public BillRecordQueryForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public String getTradeNoOrName() {
		return tradeNoOrName;
	}

	public BillRecordQueryForm setTradeNoOrName(String tradeNoOrName) {
		this.tradeNoOrName = tradeNoOrName;
		return this;
	}

	public long getOrderId() {
		return orderId;
	}

	public BillRecordQueryForm setOrderId(long orderId) {
		this.orderId = orderId;
		return this;
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public BillRecordQueryForm setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
		return this;
	}
	
	public Set<Integer> getPayType() {
		return payType;
	}

	public BillRecordQueryForm setPayType(String payType) {
		if (StringUtils.isNoneBlank(payType)) {
			String[] ss = payType.split(",");
			for (String s : ss) {
				this.payType.add(Integer.valueOf(s));
			}
		}
		return this;
	}

	public int getBillType() {
		return billType;
	}

	public BillRecordQueryForm setBillType(int billType) {
		this.billType = billType;
		return this;
	}

	public int getState() {
		return state;
	}

	public BillRecordQueryForm setState(int state) {
		this.state = state;
		return this;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public BillRecordQueryForm setMaxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public long getMinTime() {
		return minTime;
	}

	public BillRecordQueryForm setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public BillRecordQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public BillRecordQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

}

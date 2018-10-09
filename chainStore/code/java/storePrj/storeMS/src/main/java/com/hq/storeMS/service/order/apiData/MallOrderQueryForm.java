package com.hq.storeMS.service.order.apiData;

import java.util.HashSet;
import java.util.Set;

import com.hq.orderRestClient.service.order.apiData.OrderQueryForm;
import com.hq.orderRestClient.service.order.data.OrderOriginEnum;
import com.hq.orderRestClient.service.order.data.OrderTypeEnum;
import com.hq.storeMS.service.order.data.OrderBeanHelper;
import com.hq.storeMS.service.orderTrack.apiData.OrderTrackQueryForm;

public class MallOrderQueryForm {
	private long storeId;
	private long minTime;
	private long maxTime;

	private String numberOrName;
	// OrderTrackStatusEnum
	private Set<Integer> status = new HashSet<Integer>();
	private String leaguerId;

	private int pageItemCount;
	private int pageNo;

	public static MallOrderQueryForm newInstance() {
		MallOrderQueryForm data = new MallOrderQueryForm();
		return data;
	}

	public OrderQueryForm toOrderQueryForm() {
		OrderQueryForm data = OrderQueryForm.newInstance();
		data.setOrderType(OrderTypeEnum.PURCHASE.ordinal()).setStoreId(storeId).setMinTime(minTime).setMaxTime(maxTime)
				.setNumberOrName(numberOrName).setLeaguerId(leaguerId);
		for (Integer s : status) {
			data.addStatus(OrderBeanHelper.getInstance().trackStatus2OrderStatus(s));
		}
		data.addOrigin(OrderOriginEnum.CUSTOMER.ordinal());
		return data;
	}
	
	public OrderTrackQueryForm toOrderTrackQueryForm() {
		OrderTrackQueryForm data = OrderTrackQueryForm.newInstance();
		data.setStoreId(storeId).setMinTime(minTime).setMaxTime(maxTime);
		return data;
	}

	public long getStoreId() {
		return storeId;
	}

	public MallOrderQueryForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public long getMinTime() {
		return minTime;
	}

	public MallOrderQueryForm setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public MallOrderQueryForm setMaxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public String getNumberOrName() {
		return numberOrName;
	}

	public MallOrderQueryForm setNumberOrName(String numberOrName) {
		this.numberOrName = numberOrName;
		return this;
	}

	public Set<Integer> getStatus() {
		return status;
	}

	public MallOrderQueryForm setStatus(Set<Integer> status) {
		this.status = status;
		return this;
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public MallOrderQueryForm setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public MallOrderQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public MallOrderQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}
}

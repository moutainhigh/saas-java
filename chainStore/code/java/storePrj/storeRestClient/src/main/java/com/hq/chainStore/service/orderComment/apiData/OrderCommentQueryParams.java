package com.hq.chainStore.service.orderComment.apiData;

public class OrderCommentQueryParams {
	private long cuserId;
	private long storeId;
	private long beauticianId;
	private long orderId;

	private int pageItemCount;
	private int pageNo;

	public static OrderCommentQueryParams newInstance() {
		OrderCommentQueryParams params = new OrderCommentQueryParams();
		params.cuserId = 0L;
		params.storeId = 0L;
		params.beauticianId = 0L;
		params.orderId = 0L;

		params.pageItemCount = 0;
		params.pageNo = 1;
		return params;
	}

	public OrderCommentQueryParams setBeauticianId(long beauticianId) {
		this.beauticianId = beauticianId;
		return this;
	}

	public long getOrderId() {
		return orderId;
	}

	public OrderCommentQueryParams setOrderId(long orderId) {
		this.orderId = orderId;
		return this;
	}

	public long getCuserId() {
		return cuserId;
	}

	public OrderCommentQueryParams setCuserId(long cuserId) {
		this.cuserId = cuserId;
		return this;
	}

	public long getStoreId() {
		return storeId;
	}

	public OrderCommentQueryParams setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public OrderCommentQueryParams setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public OrderCommentQueryParams setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public long getBeauticianId() {
		return beauticianId;
	}

}

package com.hq.orderRestClient.service.order.apiData;

public class OrderUpdateApiForm {
	private long storeId;
	private int updateType;

	private OrderUpdateInfoApiForm updateInfoData;
	private OrderUpdateStatusApiForm updateStatusData;
	private OrderUpdatePayItemApiForm updatePayItemApiForm;
	private OrderDeleteForm orderDeleteForm;
	private OrderUpdateChargeBackForm orderUpdateChargeBackForm;
	private PayOrderWithNoteApiForm payOrderWithNoteApiForm;//支付订单,添加备注
	
	public static OrderUpdateApiForm newInstance() {
		return new OrderUpdateApiForm();
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public OrderUpdateInfoApiForm getUpdateInfoData() {
		return updateInfoData;
	}

	public void setUpdateInfoData(OrderUpdateInfoApiForm updateInfoData) {
		this.updateInfoData = updateInfoData;
	}

	public OrderUpdateStatusApiForm getUpdateStatusData() {
		return updateStatusData;
	}

	public void setUpdateStatusData(
			OrderUpdateStatusApiForm updateStatusData) {
		this.updateStatusData = updateStatusData;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public OrderUpdatePayItemApiForm getUpdatePayItemApiForm() {
		return updatePayItemApiForm;
	}

	public void setUpdatePayItemApiForm(
			OrderUpdatePayItemApiForm updatePayItemApiForm) {
		this.updatePayItemApiForm = updatePayItemApiForm;
	}

	public OrderDeleteForm getOrderDeleteForm() {
		return orderDeleteForm;
	}

	public void setOrderDeleteForm(OrderDeleteForm orderDeleteForm) {
		this.orderDeleteForm = orderDeleteForm;
	}

	public OrderUpdateChargeBackForm getOrderUpdateChargeBackForm() {
		return orderUpdateChargeBackForm;
	}

	public void setOrderUpdateChargeBackForm(OrderUpdateChargeBackForm orderUpdateChargeBackForm) {
		this.orderUpdateChargeBackForm = orderUpdateChargeBackForm;
	}

	public PayOrderWithNoteApiForm getPayOrderWithNoteApiForm() {
		return payOrderWithNoteApiForm;
	}

	public void setPayOrderWithNoteApiForm(PayOrderWithNoteApiForm payOrderWithNoteApiForm) {
		this.payOrderWithNoteApiForm = payOrderWithNoteApiForm;
	}

}

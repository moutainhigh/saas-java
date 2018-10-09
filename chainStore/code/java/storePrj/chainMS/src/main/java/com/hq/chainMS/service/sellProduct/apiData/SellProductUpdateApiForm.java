package com.hq.chainMS.service.sellProduct.apiData;

public class SellProductUpdateApiForm {
	private int updateType;

	private SellProductAllotForm allotSellProductForm;
	private SellProductBatchAllotForm batchAllotSellProductForm;
	
	private SellProductUpdateStateForm updateStateForm;
	private SellProductBatchUpdateStateForm batchUpdateStateForm;

	public static SellProductUpdateApiForm newInstance() {
		return new SellProductUpdateApiForm();
	}

	public SellProductUpdateType getSellProductUpdateType() {
		return SellProductUpdateType.valueOf(updateType);
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public SellProductAllotForm getAllotSellProductForm() {
		return allotSellProductForm;
	}

	public void setAllotSellProductForm(SellProductAllotForm allotSellProductForm) {
		this.allotSellProductForm = allotSellProductForm;
	}

	public SellProductBatchAllotForm getBatchAllotSellProductForm() {
		return batchAllotSellProductForm;
	}

	public void setBatchAllotSellProductForm(SellProductBatchAllotForm batchAllotSellProductForm) {
		this.batchAllotSellProductForm = batchAllotSellProductForm;
	}

	public SellProductUpdateStateForm getUpdateStateForm() {
		return updateStateForm;
	}

	public void setUpdateStateForm(SellProductUpdateStateForm updateStateForm) {
		this.updateStateForm = updateStateForm;
	}

	public SellProductBatchUpdateStateForm getBatchUpdateStateForm() {
		return batchUpdateStateForm;
	}

	public void setBatchUpdateStateForm(SellProductBatchUpdateStateForm batchUpdateStateForm) {
		this.batchUpdateStateForm = batchUpdateStateForm;
	}

}

package com.hq.storeClient.service.storeGoods.apiData;

public class StoreGoodsUpdateForm {
	private int updateType;

	private GoodsAddForm goodsAddForm;
	private GoodsRemoveForm goodsRemoveForm;
	private GoodsUpdateForm goodsUpdateForm;

	private GoodsTypeAddForm goodsTypeAddForm;
	private GoodsTypeRemoveForm goodsTypeRemoveForm;
	private GoodsTypeUpdateForm goodsTypeUpdateForm;

	private GoodsAddToTopForm goodsAddToTopForm;
	private GoodsCancelTopForm goodsCancelTopForm;

	private GoodsUpdateStateForm goodsUpdateStateForm;
	private GoodsBatchUpdateStateForm goodsBatchUpdateStateForm;
	
	private GoodsBatchCancelForm goodsBatchCancelForm;
	private GoodsBatchPullForm goodsBatchPullForm;
	private GoodsCancelForm goodsCancelForm;
	private GoodsPullForm goodsPullForm;
	
	public static StoreGoodsUpdateForm newInstance() {
		return new StoreGoodsUpdateForm();
	}

	public StoreGoodsUpdateType getStoreGoodsUpdateType() {
		return StoreGoodsUpdateType.valueOf(updateType);
	}
	
	public void setStoreGoodsUpdateType(StoreGoodsUpdateType storeGoodsUpdateType) {
		updateType = storeGoodsUpdateType.ordinal();
	}
	
	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public GoodsAddForm getGoodsAddForm() {
		return goodsAddForm;
	}

	public void setGoodsAddForm(GoodsAddForm goodsAddForm) {
		this.goodsAddForm = goodsAddForm;
	}

	public GoodsRemoveForm getGoodsRemoveForm() {
		return goodsRemoveForm;
	}

	public void setGoodsRemoveForm(GoodsRemoveForm goodsRemoveForm) {
		this.goodsRemoveForm = goodsRemoveForm;
	}

	public GoodsUpdateForm getGoodsUpdateForm() {
		return goodsUpdateForm;
	}

	public void setGoodsUpdateForm(GoodsUpdateForm goodsUpdateForm) {
		this.goodsUpdateForm = goodsUpdateForm;
	}

	public GoodsTypeAddForm getGoodsTypeAddForm() {
		return goodsTypeAddForm;
	}

	public void setGoodsTypeAddForm(GoodsTypeAddForm goodsTypeAddForm) {
		this.goodsTypeAddForm = goodsTypeAddForm;
	}

	public GoodsTypeRemoveForm getGoodsTypeRemoveForm() {
		return goodsTypeRemoveForm;
	}

	public void setGoodsTypeRemoveForm(GoodsTypeRemoveForm goodsTypeRemoveForm) {
		this.goodsTypeRemoveForm = goodsTypeRemoveForm;
	}

	public GoodsTypeUpdateForm getGoodsTypeUpdateForm() {
		return goodsTypeUpdateForm;
	}

	public void setGoodsTypeUpdateForm(GoodsTypeUpdateForm goodsTypeUpdateForm) {
		this.goodsTypeUpdateForm = goodsTypeUpdateForm;
	}

	public GoodsAddToTopForm getGoodsAddToTopForm() {
		return goodsAddToTopForm;
	}

	public void setGoodsAddToTopForm(GoodsAddToTopForm goodsAddToTopForm) {
		this.goodsAddToTopForm = goodsAddToTopForm;
	}

	public GoodsCancelTopForm getGoodsCancelTopForm() {
		return goodsCancelTopForm;
	}

	public void setGoodsCancelTopForm(GoodsCancelTopForm goodsCancelTopForm) {
		this.goodsCancelTopForm = goodsCancelTopForm;
	}

	public GoodsUpdateStateForm getGoodsUpdateStateForm() {
		return goodsUpdateStateForm;
	}

	public void setGoodsUpdateStateForm(
			GoodsUpdateStateForm goodsUpdateStateForm) {
		this.goodsUpdateStateForm = goodsUpdateStateForm;
	}

	public GoodsBatchUpdateStateForm getGoodsBatchUpdateStateForm() {
		return goodsBatchUpdateStateForm;
	}

	public void setGoodsBatchUpdateStateForm(
			GoodsBatchUpdateStateForm goodsBatchUpdateStateForm) {
		this.goodsBatchUpdateStateForm = goodsBatchUpdateStateForm;
	}

	public GoodsBatchCancelForm getGoodsBatchCancelForm() {
		return goodsBatchCancelForm;
	}

	public void setGoodsBatchCancelForm(GoodsBatchCancelForm goodsBatchCancelForm) {
		this.goodsBatchCancelForm = goodsBatchCancelForm;
	}

	public GoodsBatchPullForm getGoodsBatchPullForm() {
		return goodsBatchPullForm;
	}

	public void setGoodsBatchPullForm(GoodsBatchPullForm goodsBatchPullForm) {
		this.goodsBatchPullForm = goodsBatchPullForm;
	}

	public GoodsCancelForm getGoodsCancelForm() {
		return goodsCancelForm;
	}

	public void setGoodsCancelForm(GoodsCancelForm goodsCancelForm) {
		this.goodsCancelForm = goodsCancelForm;
	}

	public GoodsPullForm getGoodsPullForm() {
		return goodsPullForm;
	}

	public void setGoodsPullForm(GoodsPullForm goodsPullForm) {
		this.goodsPullForm = goodsPullForm;
	}

}

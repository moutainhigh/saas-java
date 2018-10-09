package com.hq.chainClient.service.chainGoods.apiData;

public class ChainGoodsUpdateForm {
	private int updateType;

	private GoodsAddForm goodsAddForm;
	private GoodsRemoveForm goodsRemoveForm;
	private GoodsUpdateForm goodsUpdateForm;
	private GoodsUpdateStateForm goodsUpdateStateForm;
	private GoodsBatchUpdateStateForm goodsBatchUpdateStateForm;

	private GoodsTypeAddForm goodsTypeAddForm;
	private GoodsTypeRemoveForm goodsTypeRemoveForm;
	private GoodsTypeUpdateForm goodsTypeUpdateForm;

	private GoodsAllotForm goodsAllotForm;
	private GoodsBatchAllotForm goodsBatchAllotForm;
	
	public static ChainGoodsUpdateForm newInstance() {
		return new ChainGoodsUpdateForm();
	}

	public ChainGoodsUpdateType getChainGoodsUpdateType() {
		return ChainGoodsUpdateType.valueOf(updateType);
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

	public GoodsUpdateStateForm getGoodsUpdateStateForm() {
		return goodsUpdateStateForm;
	}

	public void setGoodsUpdateStateForm(GoodsUpdateStateForm goodsUpdateStateForm) {
		this.goodsUpdateStateForm = goodsUpdateStateForm;
	}

	public GoodsBatchUpdateStateForm getGoodsBatchUpdateStateForm() {
		return goodsBatchUpdateStateForm;
	}

	public void setGoodsBatchUpdateStateForm(GoodsBatchUpdateStateForm goodsBatchUpdateStateForm) {
		this.goodsBatchUpdateStateForm = goodsBatchUpdateStateForm;
	}

	public GoodsAllotForm getGoodsAllotForm() {
		return goodsAllotForm;
	}

	public void setGoodsAllotForm(GoodsAllotForm goodsAllotForm) {
		this.goodsAllotForm = goodsAllotForm;
	}

	public GoodsBatchAllotForm getGoodsBatchAllotForm() {
		return goodsBatchAllotForm;
	}

	public void setGoodsBatchAllotForm(GoodsBatchAllotForm goodsBatchAllotForm) {
		this.goodsBatchAllotForm = goodsBatchAllotForm;
	}

}

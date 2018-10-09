package com.hq.chainStore.service.storeCardInfo.apiData;

public class StoreCardInfoUpdateApiForm {
	private int updateType;
	
	private AddPrdCardTop addPrdCardTop;
	private CancelPrdCardTop cancelPrdCardTop;

	private AddMembershipCard addMembershipCard;
	private DelMembershipCard delMembershipCard;
	private UpdMembershipCard updMembershipCard;
	private UpdMemberCardStateData updateMemberCardStateData;
	private BatchUpdMemberCardStateData batchUpdateMemberCardStateData;// 批量上下架

	
	private AddProductCardForm addProductCard;
	private DelProductCardForm delProductCard;
	private UpdProductCardForm updProductCard;
	private UpdProductCardStateData updateProductCardStateData;
	private BatchUpdProductCardStateData batchUpdateProductCardStateData;// 批量上下架

	private PrdCardTypeAddForm prdCardTypeAddForm;
	private PrdCardTypeRemoveForm prdCardTypeRemoveForm;
	private PrdCardTypeUpdateForm prdCardTypeUpdateForm;
	
	/*******************遗留字段****************/
	private AddDiscountCard addDiscountCard;
	private DelDiscountCard delDiscountCard;
	private UpdDiscountCard updDiscountCard;
	
	public static StoreCardInfoUpdateApiForm newInstance() {
		return new StoreCardInfoUpdateApiForm();
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public AddMembershipCard getAddMembershipCard() {
		return addMembershipCard;
	}

	public void setAddMembershipCard(AddMembershipCard addMembershipCard) {
		this.addMembershipCard = addMembershipCard;
	}

	public DelMembershipCard getDelMembershipCard() {
		return delMembershipCard;
	}

	public void setDelMembershipCard(DelMembershipCard delMembershipCard) {
		this.delMembershipCard = delMembershipCard;
	}

	public UpdMembershipCard getUpdMembershipCard() {
		return updMembershipCard;
	}

	public void setUpdMembershipCard(UpdMembershipCard updMembershipCard) {
		this.updMembershipCard = updMembershipCard;
	}

	public AddDiscountCard getAddDiscountCard() {
		return addDiscountCard;
	}

	public void setAddDiscountCard(AddDiscountCard addDiscountCard) {
		this.addDiscountCard = addDiscountCard;
	}

	public DelDiscountCard getDelDiscountCard() {
		return delDiscountCard;
	}

	public void setDelDiscountCard(DelDiscountCard delDiscountCard) {
		this.delDiscountCard = delDiscountCard;
	}

	public UpdDiscountCard getUpdDiscountCard() {
		return updDiscountCard;
	}

	public void setUpdDiscountCard(UpdDiscountCard updDiscountCard) {
		this.updDiscountCard = updDiscountCard;
	}

	public AddProductCardForm getAddProductCard() {
		return addProductCard;
	}

	public void setAddProductCard(AddProductCardForm addProductCard) {
		this.addProductCard = addProductCard;
	}

	public DelProductCardForm getDelProductCard() {
		return delProductCard;
	}

	public void setDelProductCard(DelProductCardForm delProductCard) {
		this.delProductCard = delProductCard;
	}

	public UpdProductCardForm getUpdProductCard() {
		return updProductCard;
	}

	public void setUpdProductCard(UpdProductCardForm updProductCard) {
		this.updProductCard = updProductCard;
	}

	public UpdMemberCardStateData getUpdateMemberCardStateData() {
		return updateMemberCardStateData;
	}

	public void setUpdateMemberCardStateData(
			UpdMemberCardStateData updateMemberCardStateData) {
		this.updateMemberCardStateData = updateMemberCardStateData;
	}

	public BatchUpdMemberCardStateData getBatchUpdateMemberCardStateData() {
		return batchUpdateMemberCardStateData;
	}

	public void setBatchUpdateMemberCardStateData(
			BatchUpdMemberCardStateData batchUpdateMemberCardStateData) {
		this.batchUpdateMemberCardStateData = batchUpdateMemberCardStateData;
	}

	public UpdProductCardStateData getUpdateProductCardStateData() {
		return updateProductCardStateData;
	}

	public void setUpdateProductCardStateData(
			UpdProductCardStateData updateProductCardStateData) {
		this.updateProductCardStateData = updateProductCardStateData;
	}

	public BatchUpdProductCardStateData getBatchUpdateProductCardStateData() {
		return batchUpdateProductCardStateData;
	}

	public void setBatchUpdateProductCardStateData(
			BatchUpdProductCardStateData batchUpdateProductCardStateData) {
		this.batchUpdateProductCardStateData = batchUpdateProductCardStateData;
	}

	public PrdCardTypeAddForm getPrdCardTypeAddForm() {
		return prdCardTypeAddForm;
	}

	public void setPrdCardTypeAddForm(PrdCardTypeAddForm prdCardTypeAddForm) {
		this.prdCardTypeAddForm = prdCardTypeAddForm;
	}

	public PrdCardTypeRemoveForm getPrdCardTypeRemoveForm() {
		return prdCardTypeRemoveForm;
	}

	public void setPrdCardTypeRemoveForm(PrdCardTypeRemoveForm prdCardTypeRemoveForm) {
		this.prdCardTypeRemoveForm = prdCardTypeRemoveForm;
	}

	public PrdCardTypeUpdateForm getPrdCardTypeUpdateForm() {
		return prdCardTypeUpdateForm;
	}

	public void setPrdCardTypeUpdateForm(PrdCardTypeUpdateForm prdCardTypeUpdateForm) {
		this.prdCardTypeUpdateForm = prdCardTypeUpdateForm;
	}

	public AddPrdCardTop getAddPrdCardTop() {
		return addPrdCardTop;
	}

	public void setAddPrdCardTop(AddPrdCardTop addPrdCardTop) {
		this.addPrdCardTop = addPrdCardTop;
	}

	public CancelPrdCardTop getCancelPrdCardTop() {
		return cancelPrdCardTop;
	}

	public void setCancelPrdCardTop(CancelPrdCardTop cancelPrdCardTop) {
		this.cancelPrdCardTop = cancelPrdCardTop;
	}
}

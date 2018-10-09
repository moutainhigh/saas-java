package com.hq.chainMS.service.chainCard.apiData;

import java.util.List;

public class ChainCardUpdateApiForm {
	private int updateType;

	private AddMembershipCard addMembershipCard;
	private List<AddMembershipCard> addMembershipCardList;
	private DelMembershipCard delMembershipCard;
	private UpdMembershipCard updMembershipCard;
	private UpdMemberCardStateData updateMemberCardStateData;
	private BatchUpdMemberCardStateData batchUpdateMemberCardStateData;

	private AddProductCardForm addProductCard;
	private DelProductCardForm delProductCard;
	private UpdProductCardForm updProductCard;
	private UpdProductCardStateData updateProductCardStateData;
	private BatchUpdProductCardStateData batchUpdateProductCardStateData;

	private PrdCardTypeAddForm prdCardTypeAddForm;
	private PrdCardTypeRemoveForm prdCardTypeRemoveForm;
	private PrdCardTypeUpdateForm prdCardTypeUpdateForm;
	
	private MemberCardAllotForm memberCardAllotForm;
	private MemberCardBatchAllotForm memberCardBatchAllotForm;
	private ProductCardAllotForm productCardAllotForm;
	private ProductCardBatchAllotForm productCardBatchAllotForm;

	public static ChainCardUpdateApiForm newInstance() {
		return new ChainCardUpdateApiForm();
	}
	
	public ChainCardUpdateType getChainCardUpdateType() {
		return ChainCardUpdateType.valueOf(updateType);
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

	public List<AddMembershipCard> getAddMembershipCardList() {
		return addMembershipCardList;
	}

	public void setAddMembershipCardList(List<AddMembershipCard> addMembershipCardList) {
		this.addMembershipCardList = addMembershipCardList;
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

	public UpdMemberCardStateData getUpdateMemberCardStateData() {
		return updateMemberCardStateData;
	}

	public void setUpdateMemberCardStateData(UpdMemberCardStateData updateMemberCardStateData) {
		this.updateMemberCardStateData = updateMemberCardStateData;
	}

	public BatchUpdMemberCardStateData getBatchUpdateMemberCardStateData() {
		return batchUpdateMemberCardStateData;
	}

	public void setBatchUpdateMemberCardStateData(BatchUpdMemberCardStateData batchUpdateMemberCardStateData) {
		this.batchUpdateMemberCardStateData = batchUpdateMemberCardStateData;
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

	public UpdProductCardStateData getUpdateProductCardStateData() {
		return updateProductCardStateData;
	}

	public void setUpdateProductCardStateData(UpdProductCardStateData updateProductCardStateData) {
		this.updateProductCardStateData = updateProductCardStateData;
	}

	public BatchUpdProductCardStateData getBatchUpdateProductCardStateData() {
		return batchUpdateProductCardStateData;
	}

	public void setBatchUpdateProductCardStateData(BatchUpdProductCardStateData batchUpdateProductCardStateData) {
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

	public MemberCardAllotForm getMemberCardAllotForm() {
		return memberCardAllotForm;
	}

	public void setMemberCardAllotForm(MemberCardAllotForm memberCardAllotForm) {
		this.memberCardAllotForm = memberCardAllotForm;
	}

	public MemberCardBatchAllotForm getMemberCardBatchAllotForm() {
		return memberCardBatchAllotForm;
	}

	public void setMemberCardBatchAllotForm(MemberCardBatchAllotForm memberCardBatchAllotForm) {
		this.memberCardBatchAllotForm = memberCardBatchAllotForm;
	}

	public ProductCardAllotForm getProductCardAllotForm() {
		return productCardAllotForm;
	}

	public void setProductCardAllotForm(ProductCardAllotForm productCardAllotForm) {
		this.productCardAllotForm = productCardAllotForm;
	}

	public ProductCardBatchAllotForm getProductCardBatchAllotForm() {
		return productCardBatchAllotForm;
	}

	public void setProductCardBatchAllotForm(ProductCardBatchAllotForm productCardBatchAllotForm) {
		this.productCardBatchAllotForm = productCardBatchAllotForm;
	}
}

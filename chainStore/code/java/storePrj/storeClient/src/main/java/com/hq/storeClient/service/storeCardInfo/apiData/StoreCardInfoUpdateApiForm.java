package com.hq.storeClient.service.storeCardInfo.apiData;

import java.util.List;

public class StoreCardInfoUpdateApiForm {
	private int updateType;

	private AddPrdCardTop addPrdCardTop;
	private CancelPrdCardTop cancelPrdCardTop;

	private AddMembershipCard addMembershipCard;
	private List<AddMembershipCard> addMembershipCardList;
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

	/************************* 连锁店数据同步 **************************************/
	private CardBatchCancelForm cardBatchCancelForm;
	private CardBatchPullForm cardBatchPullForm;
	private CardCancelForm cardCancelForm;
	private CardPullForm cardPullForm;

	private MemberCardBatchCancelForm memberCardBatchCancelForm;
	private MemberCardBatchPullForm memberCardBatchPullForm;
	private MemberCardCancelForm memberCardCancelForm;
	private MemberCardPullForm memberCardPullForm;

	/************************* 连锁店数据同步 **************************************/

	public static StoreCardInfoUpdateApiForm newInstance() {
		return new StoreCardInfoUpdateApiForm();
	}

	public StoreCardInfoUpdateType getStoreCardInfoUpdateType() {
		return StoreCardInfoUpdateType.valueOf(updateType);
	}

	public void setStoreCardInfoUpdateType(StoreCardInfoUpdateType storeCardInfoUpdateType) {
		updateType = storeCardInfoUpdateType.ordinal();
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

	public void setUpdateMemberCardStateData(UpdMemberCardStateData updateMemberCardStateData) {
		this.updateMemberCardStateData = updateMemberCardStateData;
	}

	public BatchUpdMemberCardStateData getBatchUpdateMemberCardStateData() {
		return batchUpdateMemberCardStateData;
	}

	public void setBatchUpdateMemberCardStateData(BatchUpdMemberCardStateData batchUpdateMemberCardStateData) {
		this.batchUpdateMemberCardStateData = batchUpdateMemberCardStateData;
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

	public CardBatchCancelForm getCardBatchCancelForm() {
		return cardBatchCancelForm;
	}

	public void setCardBatchCancelForm(CardBatchCancelForm cardBatchCancelForm) {
		this.cardBatchCancelForm = cardBatchCancelForm;
	}

	public CardBatchPullForm getCardBatchPullForm() {
		return cardBatchPullForm;
	}

	public void setCardBatchPullForm(CardBatchPullForm cardBatchPullForm) {
		this.cardBatchPullForm = cardBatchPullForm;
	}

	public CardCancelForm getCardCancelForm() {
		return cardCancelForm;
	}

	public void setCardCancelForm(CardCancelForm cardCancelForm) {
		this.cardCancelForm = cardCancelForm;
	}

	public CardPullForm getCardPullForm() {
		return cardPullForm;
	}

	public void setCardPullForm(CardPullForm cardPullForm) {
		this.cardPullForm = cardPullForm;
	}

	public MemberCardBatchCancelForm getMemberCardBatchCancelForm() {
		return memberCardBatchCancelForm;
	}

	public void setMemberCardBatchCancelForm(MemberCardBatchCancelForm memberCardBatchCancelForm) {
		this.memberCardBatchCancelForm = memberCardBatchCancelForm;
	}

	public MemberCardBatchPullForm getMemberCardBatchPullForm() {
		return memberCardBatchPullForm;
	}

	public void setMemberCardBatchPullForm(MemberCardBatchPullForm memberCardBatchPullForm) {
		this.memberCardBatchPullForm = memberCardBatchPullForm;
	}

	public MemberCardCancelForm getMemberCardCancelForm() {
		return memberCardCancelForm;
	}

	public void setMemberCardCancelForm(MemberCardCancelForm memberCardCancelForm) {
		this.memberCardCancelForm = memberCardCancelForm;
	}

	public MemberCardPullForm getMemberCardPullForm() {
		return memberCardPullForm;
	}

	public void setMemberCardPullForm(MemberCardPullForm memberCardPullForm) {
		this.memberCardPullForm = memberCardPullForm;
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

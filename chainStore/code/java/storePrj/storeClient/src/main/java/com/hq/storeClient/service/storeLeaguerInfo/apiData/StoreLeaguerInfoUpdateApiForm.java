package com.hq.storeClient.service.storeLeaguerInfo.apiData;

import java.util.List;

public class StoreLeaguerInfoUpdateApiForm {
	private long storeId;

	private int updateType;

	private List<LeaguerAddApiForm> leaguerAddInfoDataList;
	private LeaguerUpdateInfoApiForm leaguerUpdateInfoData;
	private LeaguerAddApiForm leaguerAddInfoData;
	private LeaguerDelApiForm leaguerDelInfoData;

	private String leaguerIds;// ","分割

	private AddAttention addAttention;
	private RemoveAttention removeAttention;

	private UpdateMemberCardForm updateMemberCardForm;
	private RechargeMemberCardForm rechargeMemberCardForm;
	private PurchaseProductCardForm purchaseProductCardForm;
	private ReduceProductCardCountForm reduceProductCardCountForm;

	// 标签
	private LeaguerLabelAddForm leaguerLabelAddForm;
	private LeaguerLabelRemoveForm leaguerLabelRemoveForm;
	private LeaguerLabelUpdateForm leaguerLabelUpdateForm;
	private List<LeaguerLabelAddForm> leaguerLabelAddForms;
	
	private SaveFollowUserForm saveFollowUserForm;

	public static StoreLeaguerInfoUpdateApiForm newInstance() {
		return new StoreLeaguerInfoUpdateApiForm();
	}

	public StoreLeaguerInfoUpdateType getUpdateTypeEnum() {
		return StoreLeaguerInfoUpdateType.valueOf(updateType);
	}

	public void setUpdateTypeEnum(StoreLeaguerInfoUpdateType updateType) {
		this.updateType = updateType.ordinal();
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public LeaguerUpdateInfoApiForm getLeaguerUpdateInfoData() {
		return leaguerUpdateInfoData;
	}

	public void setLeaguerUpdateInfoData(LeaguerUpdateInfoApiForm leaguerUpdateInfoData) {
		this.leaguerUpdateInfoData = leaguerUpdateInfoData;
	}

	public LeaguerAddApiForm getLeaguerAddInfoData() {
		return leaguerAddInfoData;
	}

	public void setLeaguerAddInfoData(LeaguerAddApiForm leaguerAddInfoData) {
		this.leaguerAddInfoData = leaguerAddInfoData;
	}

	public AddAttention getAddAttention() {
		return addAttention;
	}

	public void setAddAttention(AddAttention addAttention) {
		this.addAttention = addAttention;
	}

	public RemoveAttention getRemoveAttention() {
		return removeAttention;
	}

	public void setRemoveAttention(RemoveAttention removeAttention) {
		this.removeAttention = removeAttention;
	}

	public LeaguerDelApiForm getLeaguerDelInfoData() {
		return leaguerDelInfoData;
	}

	public void setLeaguerDelInfoData(LeaguerDelApiForm leaguerDelInfoData) {
		this.leaguerDelInfoData = leaguerDelInfoData;
	}

	public UpdateMemberCardForm getUpdateMemberCardForm() {
		return updateMemberCardForm;
	}

	public void setUpdateMemberCardForm(UpdateMemberCardForm updateMemberCardForm) {
		this.updateMemberCardForm = updateMemberCardForm;
	}

	public RechargeMemberCardForm getRechargeMemberCardForm() {
		return rechargeMemberCardForm;
	}

	public void setRechargeMemberCardForm(RechargeMemberCardForm rechargeMemberCardForm) {
		this.rechargeMemberCardForm = rechargeMemberCardForm;
	}

	public PurchaseProductCardForm getPurchaseProductCardForm() {
		return purchaseProductCardForm;
	}

	public void setPurchaseProductCardForm(PurchaseProductCardForm purchaseProductCardForm) {
		this.purchaseProductCardForm = purchaseProductCardForm;
	}

	public ReduceProductCardCountForm getReduceProductCardCountForm() {
		return reduceProductCardCountForm;
	}

	public void setReduceProductCardCountForm(ReduceProductCardCountForm reduceProductCardCountForm) {
		this.reduceProductCardCountForm = reduceProductCardCountForm;
	}

	public List<LeaguerAddApiForm> getLeaguerAddInfoDataList() {
		return leaguerAddInfoDataList;
	}

	public void setLeaguerAddInfoDataList(List<LeaguerAddApiForm> leaguerAddInfoDataList) {
		this.leaguerAddInfoDataList = leaguerAddInfoDataList;
	}

	public String getLeaguerIds() {
		return leaguerIds;
	}

	public void setLeaguerIds(String leaguerIds) {
		this.leaguerIds = leaguerIds;
	}

	public LeaguerLabelAddForm getLeaguerLabelAddForm() {
		return leaguerLabelAddForm;
	}

	public void setLeaguerLabelAddForm(LeaguerLabelAddForm leaguerLabelAddForm) {
		this.leaguerLabelAddForm = leaguerLabelAddForm;
	}

	public LeaguerLabelRemoveForm getLeaguerLabelRemoveForm() {
		return leaguerLabelRemoveForm;
	}

	public void setLeaguerLabelRemoveForm(LeaguerLabelRemoveForm leaguerLabelRemoveForm) {
		this.leaguerLabelRemoveForm = leaguerLabelRemoveForm;
	}

	public LeaguerLabelUpdateForm getLeaguerLabelUpdateForm() {
		return leaguerLabelUpdateForm;
	}

	public void setLeaguerLabelUpdateForm(LeaguerLabelUpdateForm leaguerLabelUpdateForm) {
		this.leaguerLabelUpdateForm = leaguerLabelUpdateForm;
	}

	public List<LeaguerLabelAddForm> getLeaguerLabelAddForms() {
		return leaguerLabelAddForms;
	}

	public void setLeaguerLabelAddForms(List<LeaguerLabelAddForm> leaguerLabelAddForms) {
		this.leaguerLabelAddForms = leaguerLabelAddForms;
	}

	public SaveFollowUserForm getSaveFollowUserForm() {
		return saveFollowUserForm;
	}

	public void setSaveFollowUserForm(SaveFollowUserForm saveFollowUserForm) {
		this.saveFollowUserForm = saveFollowUserForm;
	}

}

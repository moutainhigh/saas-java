package com.hq.storeMS.service.leaguerAffair.apiData;

public class LeaguerAffairUpdateApiForm {
	private long storeId;
	private int updateType;

	private AddLeaguerMembershipCard addMembershipCard;
	private DelLeaguerMembershipCard delMembershipCard;
	private AddLeaguerDiscountCard addDiscountCard;
	private DelLeaguerDiscountCard delDiscountCard;
	private AddLeaguerProductCard addProductCard;
	private DelLeaguerProductCard delProductCard;

	private AddArchives addArchives;
	private AddAlarmClock addAlarmClock;
	private DelArchives delArchives;
	private DelAlarmClock delAlarmClock;

	public static LeaguerAffairUpdateApiForm newInstance() {
		return new LeaguerAffairUpdateApiForm();
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

	public AddLeaguerMembershipCard getAddMembershipCard() {
		return addMembershipCard;
	}

	public void setAddMembershipCard(AddLeaguerMembershipCard addMembershipCard) {
		this.addMembershipCard = addMembershipCard;
	}

	public DelLeaguerMembershipCard getDelMembershipCard() {
		return delMembershipCard;
	}

	public void setDelMembershipCard(DelLeaguerMembershipCard delMembershipCard) {
		this.delMembershipCard = delMembershipCard;
	}

	public AddLeaguerDiscountCard getAddDiscountCard() {
		return addDiscountCard;
	}

	public void setAddDiscountCard(AddLeaguerDiscountCard addDiscountCard) {
		this.addDiscountCard = addDiscountCard;
	}

	public DelLeaguerDiscountCard getDelDiscountCard() {
		return delDiscountCard;
	}

	public void setDelDiscountCard(DelLeaguerDiscountCard delDiscountCard) {
		this.delDiscountCard = delDiscountCard;
	}

	public AddArchives getAddArchives() {
		return addArchives;
	}

	public void setAddArchives(AddArchives addArchives) {
		this.addArchives = addArchives;
	}

	public AddAlarmClock getAddAlarmClock() {
		return addAlarmClock;
	}

	public void setAddAlarmClock(AddAlarmClock addAlarmClock) {
		this.addAlarmClock = addAlarmClock;
	}

	public DelArchives getDelArchives() {
		return delArchives;
	}

	public void setDelArchives(DelArchives delArchives) {
		this.delArchives = delArchives;
	}

	public DelAlarmClock getDelAlarmClock() {
		return delAlarmClock;
	}

	public void setDelAlarmClock(DelAlarmClock delAlarmClock) {
		this.delAlarmClock = delAlarmClock;
	}

	public AddLeaguerProductCard getAddProductCard() {
		return addProductCard;
	}

	public void setAddProductCard(AddLeaguerProductCard addProductCard) {
		this.addProductCard = addProductCard;
	}

	public DelLeaguerProductCard getDelProductCard() {
		return delProductCard;
	}

	public void setDelProductCard(DelLeaguerProductCard delProductCard) {
		this.delProductCard = delProductCard;
	}

}

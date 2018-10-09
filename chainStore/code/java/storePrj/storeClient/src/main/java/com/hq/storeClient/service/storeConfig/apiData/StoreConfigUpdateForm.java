package com.hq.storeClient.service.storeConfig.apiData;

public class StoreConfigUpdateForm {
	// 操作类型 对应StoreConfigUpdateType枚举
	private int updateType;

	// 客户基础属性
	private BaseAttributeStatusForm baseAttributeStatusForm;// 启禁用 是否必填

	// 客户扩展属性
	private ExpandAttributeAddForm expandAttributeAddForm;
	private ExpandAttributeSortForm expandAttributeSortForm;// 升降操作
	private ExpandAttributeUpdateForm expandAttributeUpdateForm;// 基本信息修改
	private ExpandAttributeStatusForm expandAttributeStatusForm;// 启禁用 是否必填

	// 客户来源
	private LeaguerOriginAddForm leaguerOriginAddForm;
	private LeaguerOriginRemoveForm leaguerOriginRemoveForm;
	private LeaguerOriginUpdateForm leaguerOriginUpdateForm;

	// 客户类型设置
	private LeaguerTypeUpdateForm leaguerTypeUpdateForm;

	// 预约时间段设置
	private AppointTimeUpdateForm appointTimeUpdateForm;

	// 取消预约原因
	private CancelAppointAddForm cancelAppointAddForm;
	private CancelAppointRemoveForm cancelAppointRemoveForm;
	private CancelAppointUpdateForm cancelAppointUpdateForm;

	// 数据共享设置
	private ShareDataForm shareDataForm;

	// 会员分析设置
	private LeaguerAnalysisUpdateForm leaguerAnalysisUpdateForm;
	//会员生日提醒
	private LeaguerBirthdayUpdateForm leaguerBirthdayUpdateForm;

	public static StoreConfigUpdateForm newInstance() {
		return new StoreConfigUpdateForm();
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public BaseAttributeStatusForm getBaseAttributeStatusForm() {
		return baseAttributeStatusForm;
	}

	public void setBaseAttributeStatusForm(BaseAttributeStatusForm baseAttributeStatusForm) {
		this.baseAttributeStatusForm = baseAttributeStatusForm;
	}

	public ExpandAttributeAddForm getExpandAttributeAddForm() {
		return expandAttributeAddForm;
	}

	public void setExpandAttributeAddForm(ExpandAttributeAddForm expandAttributeAddForm) {
		this.expandAttributeAddForm = expandAttributeAddForm;
	}

	public ExpandAttributeSortForm getExpandAttributeSortForm() {
		return expandAttributeSortForm;
	}

	public void setExpandAttributeSortForm(ExpandAttributeSortForm expandAttributeSortForm) {
		this.expandAttributeSortForm = expandAttributeSortForm;
	}

	public ExpandAttributeUpdateForm getExpandAttributeUpdateForm() {
		return expandAttributeUpdateForm;
	}

	public void setExpandAttributeUpdateForm(ExpandAttributeUpdateForm expandAttributeUpdateForm) {
		this.expandAttributeUpdateForm = expandAttributeUpdateForm;
	}

	public ExpandAttributeStatusForm getExpandAttributeStatusForm() {
		return expandAttributeStatusForm;
	}

	public void setExpandAttributeStatusForm(ExpandAttributeStatusForm expandAttributeStatusForm) {
		this.expandAttributeStatusForm = expandAttributeStatusForm;
	}

	public LeaguerOriginAddForm getLeaguerOriginAddForm() {
		return leaguerOriginAddForm;
	}

	public void setLeaguerOriginAddForm(LeaguerOriginAddForm leaguerOriginAddForm) {
		this.leaguerOriginAddForm = leaguerOriginAddForm;
	}

	public LeaguerOriginRemoveForm getLeaguerOriginRemoveForm() {
		return leaguerOriginRemoveForm;
	}

	public void setLeaguerOriginRemoveForm(LeaguerOriginRemoveForm leaguerOriginRemoveForm) {
		this.leaguerOriginRemoveForm = leaguerOriginRemoveForm;
	}

	public LeaguerOriginUpdateForm getLeaguerOriginUpdateForm() {
		return leaguerOriginUpdateForm;
	}

	public void setLeaguerOriginUpdateForm(LeaguerOriginUpdateForm leaguerOriginUpdateForm) {
		this.leaguerOriginUpdateForm = leaguerOriginUpdateForm;
	}

	public LeaguerTypeUpdateForm getLeaguerTypeUpdateForm() {
		return leaguerTypeUpdateForm;
	}

	public void setLeaguerTypeUpdateForm(LeaguerTypeUpdateForm leaguerTypeUpdateForm) {
		this.leaguerTypeUpdateForm = leaguerTypeUpdateForm;
	}

	public AppointTimeUpdateForm getAppointTimeUpdateForm() {
		return appointTimeUpdateForm;
	}

	public void setAppointTimeUpdateForm(AppointTimeUpdateForm appointTimeUpdateForm) {
		this.appointTimeUpdateForm = appointTimeUpdateForm;
	}

	public CancelAppointAddForm getCancelAppointAddForm() {
		return cancelAppointAddForm;
	}

	public void setCancelAppointAddForm(CancelAppointAddForm cancelAppointAddForm) {
		this.cancelAppointAddForm = cancelAppointAddForm;
	}

	public CancelAppointRemoveForm getCancelAppointRemoveForm() {
		return cancelAppointRemoveForm;
	}

	public void setCancelAppointRemoveForm(CancelAppointRemoveForm cancelAppointRemoveForm) {
		this.cancelAppointRemoveForm = cancelAppointRemoveForm;
	}

	public CancelAppointUpdateForm getCancelAppointUpdateForm() {
		return cancelAppointUpdateForm;
	}

	public void setCancelAppointUpdateForm(CancelAppointUpdateForm cancelAppointUpdateForm) {
		this.cancelAppointUpdateForm = cancelAppointUpdateForm;
	}

	public ShareDataForm getShareDataForm() {
		return shareDataForm;
	}

	public void setShareDataForm(ShareDataForm shareDataForm) {
		this.shareDataForm = shareDataForm;
	}

	public LeaguerBirthdayUpdateForm getLeaguerBirthdayUpdateForm() {
		return leaguerBirthdayUpdateForm;
	}

	public void setLeaguerBirthdayUpdateForm(LeaguerBirthdayUpdateForm leaguerBirthdayUpdateForm) {
		this.leaguerBirthdayUpdateForm = leaguerBirthdayUpdateForm;
	}

	public LeaguerAnalysisUpdateForm getLeaguerAnalysisUpdateForm() {
		return leaguerAnalysisUpdateForm;
	}

	public void setLeaguerAnalysisUpdateForm(LeaguerAnalysisUpdateForm leaguerAnalysisUpdateForm) {
		this.leaguerAnalysisUpdateForm = leaguerAnalysisUpdateForm;
	}

}

package com.hq.storeMS.service.storePackageProject.apiData;

public class StorePackageProjectUpdateForm {
	private int updateType;

	private PackageProjectAddForm packageProjectAddForm;
	private PackageProjectRemoveForm packageProjectRemoveForm;
	private PackageProjectUpdateForm packageProjectUpdateForm;
	private PackageProjectUpdateStateForm projectUpdateStateForm;
	private PackageProjectBatchUpdateStateForm projectBatchUpdateStateForm;
	
	private PackageProjectTypeAddForm packageProjectTypeAddForm;
	private PackageProjectTypeRemoveForm packageProjectTypeRemoveForm;
	private PackageProjectTypeUpdateForm packageProjectTypeUpdateForm;
	
	private PkgPrjAddTopForm pkgPrjAddTopForm;
	private PkgPrjCancelTopForm pkgPrjCancelTopForm;
	
	/**********************************同步连锁店数据***************************************/
	private PackageBatchCancelForm packageBatchCancelForm;
	private PackageBatchPullForm packageBatchPullForm;
	private PackageCancelForm packageCancelForm;
	private PackagePullForm packagePullForm;

	public static StorePackageProjectUpdateForm newInstance() {
		return new StorePackageProjectUpdateForm();
	}
	
	public StorePackageProjectUpdateType getStorePackageProjectUpdateType() {
		return StorePackageProjectUpdateType.valueOf(updateType);
	}

	public void setStorePackageProjectUpdateType(StorePackageProjectUpdateType storePackageProjectUpdateType) {
		updateType = storePackageProjectUpdateType.ordinal();
	}
	
	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public PackageProjectAddForm getPackageProjectAddForm() {
		return packageProjectAddForm;
	}

	public void setPackageProjectAddForm(PackageProjectAddForm packageProjectAddForm) {
		this.packageProjectAddForm = packageProjectAddForm;
	}

	public PackageProjectRemoveForm getPackageProjectRemoveForm() {
		return packageProjectRemoveForm;
	}

	public void setPackageProjectRemoveForm(PackageProjectRemoveForm packageProjectRemoveForm) {
		this.packageProjectRemoveForm = packageProjectRemoveForm;
	}

	public PackageProjectUpdateForm getPackageProjectUpdateForm() {
		return packageProjectUpdateForm;
	}

	public void setPackageProjectUpdateForm(PackageProjectUpdateForm packageProjectUpdateForm) {
		this.packageProjectUpdateForm = packageProjectUpdateForm;
	}

	public PackageProjectUpdateStateForm getProjectUpdateStateForm() {
		return projectUpdateStateForm;
	}

	public void setProjectUpdateStateForm(PackageProjectUpdateStateForm projectUpdateStateForm) {
		this.projectUpdateStateForm = projectUpdateStateForm;
	}

	public PackageProjectBatchUpdateStateForm getProjectBatchUpdateStateForm() {
		return projectBatchUpdateStateForm;
	}

	public void setProjectBatchUpdateStateForm(PackageProjectBatchUpdateStateForm projectBatchUpdateStateForm) {
		this.projectBatchUpdateStateForm = projectBatchUpdateStateForm;
	}

	public PackageProjectTypeAddForm getPackageProjectTypeAddForm() {
		return packageProjectTypeAddForm;
	}

	public void setPackageProjectTypeAddForm(PackageProjectTypeAddForm packageProjectTypeAddForm) {
		this.packageProjectTypeAddForm = packageProjectTypeAddForm;
	}

	public PackageProjectTypeRemoveForm getPackageProjectTypeRemoveForm() {
		return packageProjectTypeRemoveForm;
	}

	public void setPackageProjectTypeRemoveForm(PackageProjectTypeRemoveForm packageProjectTypeRemoveForm) {
		this.packageProjectTypeRemoveForm = packageProjectTypeRemoveForm;
	}

	public PackageProjectTypeUpdateForm getPackageProjectTypeUpdateForm() {
		return packageProjectTypeUpdateForm;
	}

	public void setPackageProjectTypeUpdateForm(PackageProjectTypeUpdateForm packageProjectTypeUpdateForm) {
		this.packageProjectTypeUpdateForm = packageProjectTypeUpdateForm;
	}

	public PackageBatchCancelForm getPackageBatchCancelForm() {
		return packageBatchCancelForm;
	}

	public void setPackageBatchCancelForm(PackageBatchCancelForm packageBatchCancelForm) {
		this.packageBatchCancelForm = packageBatchCancelForm;
	}

	public PackageBatchPullForm getPackageBatchPullForm() {
		return packageBatchPullForm;
	}

	public void setPackageBatchPullForm(PackageBatchPullForm packageBatchPullForm) {
		this.packageBatchPullForm = packageBatchPullForm;
	}

	public PackageCancelForm getPackageCancelForm() {
		return packageCancelForm;
	}

	public void setPackageCancelForm(PackageCancelForm packageCancelForm) {
		this.packageCancelForm = packageCancelForm;
	}

	public PackagePullForm getPackagePullForm() {
		return packagePullForm;
	}

	public void setPackagePullForm(PackagePullForm packagePullForm) {
		this.packagePullForm = packagePullForm;
	}

	public PkgPrjAddTopForm getPkgPrjAddTopForm() {
		return pkgPrjAddTopForm;
	}

	public void setPkgPrjAddTopForm(PkgPrjAddTopForm pkgPrjAddTopForm) {
		this.pkgPrjAddTopForm = pkgPrjAddTopForm;
	}

	public PkgPrjCancelTopForm getPkgPrjCancelTopForm() {
		return pkgPrjCancelTopForm;
	}

	public void setPkgPrjCancelTopForm(PkgPrjCancelTopForm pkgPrjCancelTopForm) {
		this.pkgPrjCancelTopForm = pkgPrjCancelTopForm;
	}
}

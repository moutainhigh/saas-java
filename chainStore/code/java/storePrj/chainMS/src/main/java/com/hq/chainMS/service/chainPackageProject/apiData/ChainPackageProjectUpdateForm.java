package com.hq.chainMS.service.chainPackageProject.apiData;

public class ChainPackageProjectUpdateForm {
	private int updateType;

	private PackageProjectAddForm packageProjectAddForm;
	private PackageProjectRemoveForm packageProjectRemoveForm;
	private PackageProjectUpdateForm packageProjectUpdateForm;
	private PackageProjectUpdateStateForm projectUpdateStateForm;
	private PackageProjectBatchUpdateStateForm projectBatchUpdateStateForm;
	
	private PackageProjectTypeAddForm packageProjectTypeAddForm;
	private PackageProjectTypeRemoveForm packageProjectTypeRemoveForm;
	private PackageProjectTypeUpdateForm packageProjectTypeUpdateForm;
	
	private PackageProjectAllotForm packageProjectAllotForm;
	private PackageProjectBatchAllotForm packageProjectBatchAllotForm;

	public static ChainPackageProjectUpdateForm newInstance() {
		return new ChainPackageProjectUpdateForm();
	}
	
	public ChainPackageProjectUpdateType getChainPackageProjectUpdateType() {
		return ChainPackageProjectUpdateType.valueOf(updateType);
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

	public PackageProjectAllotForm getPackageProjectAllotForm() {
		return packageProjectAllotForm;
	}

	public void setPackageProjectAllotForm(PackageProjectAllotForm packageProjectAllotForm) {
		this.packageProjectAllotForm = packageProjectAllotForm;
	}

	public PackageProjectBatchAllotForm getPackageProjectBatchAllotForm() {
		return packageProjectBatchAllotForm;
	}

	public void setPackageProjectBatchAllotForm(PackageProjectBatchAllotForm packageProjectBatchAllotForm) {
		this.packageProjectBatchAllotForm = packageProjectBatchAllotForm;
	}
}

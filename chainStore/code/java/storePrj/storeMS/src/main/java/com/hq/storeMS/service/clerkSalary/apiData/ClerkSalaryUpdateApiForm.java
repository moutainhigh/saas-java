package com.hq.storeMS.service.clerkSalary.apiData;

public class ClerkSalaryUpdateApiForm {
	
	private long storeId;
	
	private long clerkId;
	
	private int updateType;
	
	private RemoveClerkSalaryRecordApiForm removeClerkSalaryRecordApiForm;
	
	private AddClerkSalaryRecordApiForm addClerkSalaryRecordApiForm;
	
	private AddClerkPayrollApiForm addClerkPayrollApiForm;
	
	public static ClerkSalaryUpdateApiForm newInstance(){
		return new ClerkSalaryUpdateApiForm();
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getClerkId() {
		return clerkId;
	}

	public void setClerkId(long clerkId) {
		this.clerkId = clerkId;
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public RemoveClerkSalaryRecordApiForm getRemoveClerkSalaryRecordApiForm() {
		return removeClerkSalaryRecordApiForm;
	}

	public void setRemoveClerkSalaryRecordApiForm(RemoveClerkSalaryRecordApiForm removeClerkSalaryRecordApiForm) {
		this.removeClerkSalaryRecordApiForm = removeClerkSalaryRecordApiForm;
	}

	public AddClerkSalaryRecordApiForm getAddClerkSalaryRecordApiForm() {
		return addClerkSalaryRecordApiForm;
	}

	public void setAddClerkSalaryRecordApiForm(AddClerkSalaryRecordApiForm addClerkSalaryRecordApiForm) {
		this.addClerkSalaryRecordApiForm = addClerkSalaryRecordApiForm;
	}

	public AddClerkPayrollApiForm getAddClerkPayrollApiForm() {
		return addClerkPayrollApiForm;
	}

	public void setAddClerkPayrollApiForm(AddClerkPayrollApiForm addClerkPayrollApiForm) {
		this.addClerkPayrollApiForm = addClerkPayrollApiForm;
	}


	
}

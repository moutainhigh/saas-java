package com.hq.chainStore.service.clerkSalary.apiData;

public class RemoveClerkSalaryRecordApiForm {
	
	private long id;
	
	public static RemoveClerkSalaryRecordApiForm newInstance(){
		return new RemoveClerkSalaryRecordApiForm();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}

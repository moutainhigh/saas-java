package com.hq.chainStore.service.clerkSalary.apiData;

public class AddClerkSalaryRecordApiForm {
	
	//对应员工工资
	private float salary;

	//对应员工提成
	private float percentage;
	
	//对应修改人name
	private String userName;
	
	public static AddClerkSalaryRecordApiForm newInstance(){
		return new AddClerkSalaryRecordApiForm();
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}

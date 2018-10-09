package com.hq.storeMS.service.clerkSalary.data;

import javax.persistence.Id;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class ClerkSalaryRecords {

	@Id
	private long id;
	//对应月份日期
	private String date;
	//对应员工工资
	private float salary;
	//对应员工提成
	private float percentage;
	//对应修改人name
	private String userName;

	private long createTime;
	
	public static ClerkSalaryRecords newInstance(long idP){
		ClerkSalaryRecords clerkSalaryRecords = new ClerkSalaryRecords();
		clerkSalaryRecords.createTime = System.currentTimeMillis();
		clerkSalaryRecords.id = idP;
		return clerkSalaryRecords;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
}

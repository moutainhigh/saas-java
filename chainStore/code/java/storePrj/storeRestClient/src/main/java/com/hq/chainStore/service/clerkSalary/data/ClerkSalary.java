package com.hq.chainStore.service.clerkSalary.data;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.common.dataSyn.bs.IntfSynData;

@Table(name = "clerkSalary")
public class ClerkSalary implements IntfSynData{
	
	@Id
	private String id;
	
	private long storeId;
	
	private long clerkId;
	
	//对应员工薪酬记录
	private Map<String,ClerkSalaryRecords> clerkSalaryRecordsMap = new HashMap<String,ClerkSalaryRecords>();
	
	//对应员工薪酬记录
	private List<ClerkSalaryRecords> clerkSalaryRecordsList = new LinkedList<ClerkSalaryRecords>();
	
	//对应员工各月份工资单
	private Map<String,ClerkPayroll> clerkPayrollMap = new HashMap<String,ClerkPayroll>();
	
	//对应员工工资
	private float salary;
	
	//对应员工提成
	private float percentage;
	
	private long createdTime;
	
	private long lastUpdateTime;
	
	private long ver;
	
	public static ClerkSalary newInstance(){
		ClerkSalary clerkSalary = new ClerkSalary();
		return clerkSalary;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Map<String, ClerkSalaryRecords> getClerkSalaryRecordsMap() {
		return clerkSalaryRecordsMap;
	}

	public void setClerkSalaryRecordsMap(Map<String, ClerkSalaryRecords> clerkSalaryRecordsMap) {
		this.clerkSalaryRecordsMap = clerkSalaryRecordsMap;
	}

	public List<ClerkSalaryRecords> getClerkSalaryRecordsList() {
		return clerkSalaryRecordsList;
	}

	public void setClerkSalaryRecordsList(List<ClerkSalaryRecords> clerkSalaryRecordsList) {
		this.clerkSalaryRecordsList = clerkSalaryRecordsList;
	}

	public Map<String, ClerkPayroll> getClerkPayrollMap() {
		return clerkPayrollMap;
	}

	public void setClerkPayrollMap(Map<String, ClerkPayroll> clerkPayrollMap) {
		this.clerkPayrollMap = clerkPayrollMap;
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

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public long getVer() {
		return ver;
	}

	public void setVer(long ver) {
		this.ver = ver;
	}
	
	public void incrVer() {
		this.ver = ver + 1;
	}
	
	@Override
	public Object targetId() {
		return this.id;
	}

	@Override
	public long targetVer() {
		return this.ver;
	}
	
}

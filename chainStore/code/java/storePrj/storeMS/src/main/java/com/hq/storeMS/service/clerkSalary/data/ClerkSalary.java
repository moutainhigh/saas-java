package com.hq.storeMS.service.clerkSalary.data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.storeMS.service.clerkSalary.apiData.AddClerkPayrollApiForm;
import com.hq.storeMS.service.clerkSalary.apiData.AddClerkSalaryRecordApiForm;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.dao.classinfo.IndexField;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "clerkSalary")
public class ClerkSalary {
	
	@Id
	private String id;
	@IndexField
	private long storeId;
	
	private long clerkId;
	
	//对应员工薪酬记录 （不用）
	private Map<String,ClerkSalaryRecords> clerkSalaryRecordsMap = new HashMap<String,ClerkSalaryRecords>();
	
	private long salaryRecordsIdIndex = 1;
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
	
	public static ClerkSalary newInstance(long storeId,long buserId){
		ClerkSalary clerkSalary = new ClerkSalary();
		clerkSalary.id = storeId +"_"+ buserId;
		clerkSalary.storeId = storeId;
		clerkSalary.clerkId = buserId;
		
		clerkSalary.createdTime = System.currentTimeMillis();
		clerkSalary.lastUpdateTime = System.currentTimeMillis();
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

	public long getSalaryRecordsIdIndex() {
		return salaryRecordsIdIndex;
	}

	public void setSalaryRecordsIdIndex(long salaryRecordsIdIndex) {
		this.salaryRecordsIdIndex = salaryRecordsIdIndex;
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
	
	public boolean removeRecords(long id){
		boolean success = false;
		Iterator<ClerkSalaryRecords> it = this.clerkSalaryRecordsList.iterator();
		while(it.hasNext()){
			ClerkSalaryRecords item = it.next();
			if(item.getId() == id){
				it.remove();
				success = true;
				break;
			}
		}
		return success;
	}
	
	public boolean addRecord(AddClerkSalaryRecordApiForm formInfo){
		this.salary = formInfo.getSalary();
		this.percentage = formInfo.getPercentage();
		
		ClerkSalaryRecords clerkSalaryRecords = ClerkSalaryRecords.newInstance(this.salaryRecordsIdIndex);
		this.salaryRecordsIdIndex ++;
		FastBeanCopyer.getInstance().copy(formInfo, clerkSalaryRecords);
		
		//只保存10条记录 删除最早记录
		if(this.clerkSalaryRecordsList.size() >= 10){
			this.clerkSalaryRecordsList.remove(0);
			this.clerkSalaryRecordsList.add(clerkSalaryRecords);
		}else{
			this.clerkSalaryRecordsList.add(clerkSalaryRecords);
		}
		return true;
	}
	
	public boolean addPayroll(AddClerkPayrollApiForm formInfo){
		//改为前台传日期
		String date = formInfo.getDate();
		if(date != null){
			ClerkPayroll clerkPayroll = ClerkPayroll.newInstance(date);
			FastBeanCopyer.getInstance().copy(formInfo, clerkPayroll);
			this.clerkPayrollMap.put(date, clerkPayroll);
			return true;
		}else{
			return false;
		}
	}
	
}

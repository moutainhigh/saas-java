package com.hq.testClass.robot.clerkSalary.robot;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.clerkSalary.apiData.AddClerkPayrollApiForm;
import com.hq.chainStore.service.clerkSalary.apiData.AddClerkSalaryRecordApiForm;
import com.hq.chainStore.service.clerkSalary.apiData.ClerkSalaryUpdateApiForm;
import com.hq.chainStore.service.clerkSalary.apiData.ClerkSalaryUpdateType;
import com.hq.chainStore.service.clerkSalary.apiData.RemoveClerkSalaryRecordApiForm;
import com.hq.chainStore.service.clerkSalary.bs.ClerkSalaryMgr;
import com.hq.chainStore.service.clerkSalary.data.ClerkSalary;

public class ClerkSalaryRobot {
	
	private ClerkSalaryRobotData data;
	
	public static ClerkSalaryRobot newRandom(){
		int mark = RandomUtils.nextInt(0, 10000);
		return newInstance(mark);
	}

	public static ClerkSalaryRobot newInstance(int mark){
		ClerkSalaryRobot robot = new ClerkSalaryRobot();
		robot.data = ClerkSalaryRobotData.newInstance(mark);
		return robot;
	}
	
	public ClerkSalary addRecord(long storeId,long clerkId){
		ClerkSalaryUpdateApiForm clerkSalaryUpdateApiForm = ClerkSalaryUpdateApiForm.newInstance();
		clerkSalaryUpdateApiForm.setUpdateType(ClerkSalaryUpdateType.AddClerkSalaryRecord.ordinal());
		clerkSalaryUpdateApiForm.setStoreId(storeId);
		clerkSalaryUpdateApiForm.setClerkId(clerkId);
		
		AddClerkSalaryRecordApiForm addClerkSalaryRecordApiForm = AddClerkSalaryRecordApiForm.newInstance();
		addClerkSalaryRecordApiForm.setSalary(data.getSalary());
		addClerkSalaryRecordApiForm.setPercentage(data.getPercentage());
		addClerkSalaryRecordApiForm.setUserName(data.getUserName());
		
		clerkSalaryUpdateApiForm.setAddClerkSalaryRecordApiForm(addClerkSalaryRecordApiForm);
		
		ClerkSalaryMgr.getInstance().update(clerkSalaryUpdateApiForm);
		
		ClerkSalary clerkSalary =ClerkSalaryMgr.getInstance().findById(storeId,clerkId);
		data.setStoreId(storeId);
		data.setClerkId(clerkId);
		data.setId(clerkSalary.getId());
		
		return clerkSalary;
	}
	
	public void removeRecord(long id){
		ClerkSalaryUpdateApiForm clerkSalaryUpdateApiForm = ClerkSalaryUpdateApiForm.newInstance();
		clerkSalaryUpdateApiForm.setUpdateType(ClerkSalaryUpdateType.RemoveClerkSalaryRecord.ordinal());
		clerkSalaryUpdateApiForm.setStoreId(data.getStoreId());
		clerkSalaryUpdateApiForm.setClerkId(data.getClerkId());
		
		RemoveClerkSalaryRecordApiForm removeClerkSalaryRecordApiForm = RemoveClerkSalaryRecordApiForm.newInstance();
		removeClerkSalaryRecordApiForm.setId(id);
		clerkSalaryUpdateApiForm.setRemoveClerkSalaryRecordApiForm(removeClerkSalaryRecordApiForm);
		ClerkSalaryMgr.getInstance().update(clerkSalaryUpdateApiForm);
	}
	
	public ClerkSalary addPayroll(long storeId,long clerkId){
		ClerkSalaryUpdateApiForm clerkSalaryUpdateApiForm = ClerkSalaryUpdateApiForm.newInstance();
		clerkSalaryUpdateApiForm.setUpdateType(ClerkSalaryUpdateType.AddClerkPayroll.ordinal());
		clerkSalaryUpdateApiForm.setStoreId(storeId);
		clerkSalaryUpdateApiForm.setClerkId(clerkId);
		
		AddClerkPayrollApiForm addClerkPayrollApiForm = AddClerkPayrollApiForm.newInstance();
		addClerkPayrollApiForm.setSalary(data.getSalary());
		addClerkPayrollApiForm.setPercentage(data.getPercentage());
		addClerkPayrollApiForm.setWorkdays(data.getWorkdays());
		addClerkPayrollApiForm.setRealSalary(data.getRealSalary());
		addClerkPayrollApiForm.setRealPercentage(data.getRealPercentage());
		addClerkPayrollApiForm.setRealWages(data.getRealWages());
		addClerkPayrollApiForm.setOtherIncome(data.getOtherIncome());
		addClerkPayrollApiForm.setOtherIncomeNotes(data.getOtherIncomeNotes());
		addClerkPayrollApiForm.setSalaryNotes(data.getSalaryNotes());
		addClerkPayrollApiForm.setOrderAmount(data.getOrderAmount());
		addClerkPayrollApiForm.setPercentageNotes(data.getPercentageNotes());
		addClerkPayrollApiForm.setRealWagesNotes(data.getRealWagesNotes());
		addClerkPayrollApiForm.setTotalWages(data.getTotalWages());
		addClerkPayrollApiForm.setDate(data.getDate());
		
		clerkSalaryUpdateApiForm.setAddClerkPayrollApiForm(addClerkPayrollApiForm);
		
		ClerkSalaryMgr.getInstance().update(clerkSalaryUpdateApiForm);
		
		ClerkSalary clerkSalary =ClerkSalaryMgr.getInstance().findById(storeId,clerkId);
		data.setStoreId(storeId);
		data.setClerkId(clerkId);
		data.setId(clerkSalary.getId());
		
		return clerkSalary;
	}
	
	public ClerkSalary getById(long storeId, long clerkId){
		return ClerkSalaryMgr.getInstance().findById(storeId, clerkId);
	}
	
	public List<ClerkSalary> findByStoreId(long storeId){
		final int pageItemCount = 10;
		final int pageNo = 1;
		return ClerkSalaryMgr.getInstance().findByStoreId(storeId,pageItemCount,pageNo);
	}

	public ClerkSalaryRobotData getData() {
		return data;
	}

	public void setData(ClerkSalaryRobotData data) {
		this.data = data;
	}

	public String getId(){
		return this.data.getId();
	}
	
	
	
}

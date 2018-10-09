package com.hq.storeMS.service.clerkSalary.bs;

import java.util.List;

import com.hq.storeMS.service.clerkSalary.apiData.AddClerkPayrollApiForm;
import com.hq.storeMS.service.clerkSalary.apiData.AddClerkSalaryRecordApiForm;
import com.hq.storeMS.service.clerkSalary.apiData.RemoveClerkSalaryRecordApiForm;
import com.hq.storeMS.service.clerkSalary.data.ClerkSalary;
import com.zenmind.common.hotSwap.HotSwap;

public class ClerkSalaryMgr {

	public static ClerkSalaryMgr getInstance(){
		return HotSwap.getInstance().getSingleton(ClerkSalaryMgr.class);
	}
	
	public void addWithId(ClerkSalary target) {
		ClerkSalaryDataHolder.getInstance().addWithId(target);
	}
	
	public ClerkSalary get(long storeId,long clerkId){
		ClerkSalary clerkSalary = ClerkSalaryDataHolder.getInstance().get(getClerkSalaryId(storeId,clerkId));
		
		if(clerkSalary == null){
			clerkSalary = ClerkSalary.newInstance(storeId,clerkId);
			addWithId(clerkSalary);
		}
		return clerkSalary;
	}
	
	public List<ClerkSalary> findByStoreId(long storeId,int pageItemCount,int pageNo){
		return ClerkSalaryDataHolder.getInstance().findByStoreId(storeId,pageItemCount,pageNo);
	}
	
	public void update(ClerkSalary target) {
		ClerkSalaryDataHolder.getInstance().update(target);
	}
	
	public boolean removeClerkSalaryRecord(long storeId,long clerkId,RemoveClerkSalaryRecordApiForm formInfo){
		boolean success = false;
		ClerkSalary clerkSalary = get(storeId,clerkId);
		
		success = clerkSalary.removeRecords(formInfo.getId());
		if(success){
			update(clerkSalary);
		}
		return success;
	}
	
	public boolean addClerkSalaryRecord(long storeId,long clerkId,AddClerkSalaryRecordApiForm formInfo){
		boolean success = false;
		ClerkSalary clerkSalary = get(storeId,clerkId);
		success = clerkSalary.addRecord(formInfo);
		if(success){
			update(clerkSalary);
		}
		return success;
	}
	
	public boolean addClerkPayroll(long storeId,long clerkId,AddClerkPayrollApiForm formInfo){
		boolean success = false;
		ClerkSalary clerkSalary = get(storeId,clerkId);
		success = clerkSalary.addPayroll(formInfo);
		if(success){
			update(clerkSalary);
		}
		return success;
	}
	
	private String getClerkSalaryId(long storeId,long buserId){
		return storeId +"_"+ buserId;
	}
}

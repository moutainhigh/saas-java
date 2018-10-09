package com.hq.chainStore.service.clerkSalary.bs;

import java.util.List;

import com.hq.chainStore.service.clerkSalary.apiData.ClerkSalaryUpdateApiForm;
import com.hq.chainStore.service.clerkSalary.data.ClerkSalary;
import com.hq.chainStore.service.clerkSalary.data.ClerkSalaryDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;

public class ClerkSalaryMgr {

	public static ClerkSalaryMgr getInstance(){
		return HotSwap.getInstance().getSingleton(ClerkSalaryMgr.class);
	}
	
	public ClerkSalary findById(long storeId,long clerkId){
		final String findPath = "findById";
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("storeId", storeId).add("clerkId", clerkId);
		return ClerkSalaryDAO.getInstance().findOneWithReqParam(findPath, reqMap);
	}
	
	public List<ClerkSalary> findByStoreId(long storeId,int pageItemCount,int pageNo){
		final String findPath = "findByStoreId";
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("storeId", storeId);
		return ClerkSalaryDAO.getInstance().findWithReqParam(findPath, reqMap, pageItemCount, pageNo);
	}
	
	public void update(ClerkSalaryUpdateApiForm updateForm){
		ClerkSalaryDAO.getInstance().update(getClerkSalaryId(updateForm.getStoreId(),updateForm.getClerkId()), updateForm);
	}
	
	private String getClerkSalaryId(long storeId,long clerkId){
		return storeId + "_" + clerkId;
	}
	
}

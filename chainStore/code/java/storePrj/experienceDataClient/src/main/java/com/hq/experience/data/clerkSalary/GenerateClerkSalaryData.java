package com.hq.experience.data.clerkSalary;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.BaseGenerate;
import com.hq.StoreClientMgr;
import com.hq.chainStore.service.clerkSalary.apiData.AddClerkSalaryRecordApiForm;
import com.hq.chainStore.service.clerkSalary.apiData.ClerkSalaryUpdateApiForm;
import com.hq.chainStore.service.clerkSalary.apiData.ClerkSalaryUpdateType;
import com.hq.chainStore.service.clerkSalary.bs.ClerkSalaryMgr;
import com.hq.chainStore.service.storeClerkInfo.bs.StoreClerkInfoMgr;
import com.hq.chainStore.service.storeClerkInfo.data.ClerkInfo;
import com.hq.chainStore.service.storeClerkInfo.data.StoreClerkInfo;
import com.hq.experienceData.TClerkSalary;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;
import com.hq.zenmind.dao.rest.restSTImpl.CacheMgr4Test;
import com.hq.zenmind.dao.rest.restSTImpl.RestLogerImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestProxySTImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestTemplateMgr;

@Deprecated
public class GenerateClerkSalaryData extends BaseGenerate{
	
	public static void main(String[] args) throws Exception {
		long phone = 13660623958L;
		String service = "http://192.168.10.170:9114/ws/v1";
		String reportService = "http://192.168.10.170:9117/ws/v1";
		RestTemplateMgr.getInstance().init();
		StoreClientMgr.init(new RestLogerImpl(), new RestProxySTImpl(), new CacheMgr4Test(), service, reportService);
		new GenerateClerkSalaryData().genData(phone);
	}
	
	public GenerateClerkSalaryData(){
		super();
	}
	
	@Override
	public void genData(long phone){
		try {
			initEnv(phone);
			for (Long id : storeIds) {
				this.storeId = id;
				genClerkSalaryData();
			}
			System.out.println("Generate ClerkSalary Data success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void genClerkSalaryData() {
		List<TClerkSalary> clerkSalaries = TClerkSalary.buildTClerkSalaryList();
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().get("_sci"+this.storeId);
		List<ClerkInfo> list = new ArrayList<ClerkInfo>(storeClerkInfo.getClerkInfoMap().values());
		AccessTokenMgr.getInstance().removeOpIdTL();
		
		for (ClerkInfo info : list) {
			AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
			TClerkSalary tClerkSalary = clerkSalaries.get(RandomUtils.nextInt(0, clerkSalaries.size()));
			ClerkSalaryUpdateApiForm clerkSalaryUpdateApiForm = ClerkSalaryUpdateApiForm.newInstance();
			clerkSalaryUpdateApiForm.setUpdateType(ClerkSalaryUpdateType.AddClerkSalaryRecord.ordinal());
			clerkSalaryUpdateApiForm.setStoreId(storeId);
			clerkSalaryUpdateApiForm.setClerkId(info.getBuserId());
			AddClerkSalaryRecordApiForm addClerkSalaryRecordApiForm = AddClerkSalaryRecordApiForm.newInstance();
			addClerkSalaryRecordApiForm.setSalary(tClerkSalary.getSalary());
			addClerkSalaryRecordApiForm.setPercentage(tClerkSalary.getPercentage());
			clerkSalaryUpdateApiForm.setAddClerkSalaryRecordApiForm(addClerkSalaryRecordApiForm);
			ClerkSalaryMgr.getInstance().update(clerkSalaryUpdateApiForm);
			AccessTokenMgr.getInstance().removeOpIdTL();
		}
	}
}
	

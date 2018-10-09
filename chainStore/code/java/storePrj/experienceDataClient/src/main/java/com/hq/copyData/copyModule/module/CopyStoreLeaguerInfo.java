package com.hq.copyData.copyModule.module;

import java.util.List;

import com.hq.chainStore.service.common.EntityState;
import com.hq.chainStore.service.leaguerDetail.apiData.LeaguerDetailQueryForm;
import com.hq.chainStore.service.leaguerDetail.bs.LeaguerDetailMgr;
import com.hq.chainStore.service.leaguerDetail.data.LeaguerDetail;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.LeaguerAddApiForm;
import com.hq.chainStore.service.storeLeaguerInfo.bs.StoreLeaguerInfoMgr;
import com.hq.copyData.copyModule.AbstractCopyModule;
import com.hq.experienceData.TCustomer;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class CopyStoreLeaguerInfo extends AbstractCopyModule{
	
	public static CopyStoreLeaguerInfo newInstance(){
		CopyStoreLeaguerInfo data = new CopyStoreLeaguerInfo();
		return data;
	}
	
	public void copy(){
		AccessTokenMgr.getInstance().setOpIdTL(getSourceBossId());
		LeaguerDetailQueryForm queryForm = LeaguerDetailQueryForm.newInstance();
		queryForm.setStoreId(getSourceStoreId());
		List<LeaguerDetail> leaguerList = LeaguerDetailMgr.getInstance().getLeaguerDetailPageInfo(queryForm).getList();
		AccessTokenMgr.getInstance().removeOpIdTL();
		
		List<TCustomer> customers = TCustomer.buildTCustomerList();
		int total = leaguerList.size() > customers.size() ? customers.size() : leaguerList.size();
		
		AccessTokenMgr.getInstance().setOpIdTL(getTargetBossId());
		for (int i=0; i< total; i++) {
			if(leaguerList.get(i).getEntityState() == EntityState.Deleted.ordinal()) {
				continue;
			}
			LeaguerAddApiForm addForm = LeaguerAddApiForm.newInstance();
			FastBeanCopyer.getInstance().copy(leaguerList.get(i), addForm);
			addForm.setPhone(customers.get(i).getPhone());
			addForm.getBuserIds().clear();
			StoreLeaguerInfoMgr.getInstance().addLeaguerInfo(getTargetStoreId(), addForm);
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
		
		System.out.println("copy store leaguer info finish");
	}
}

package com.hq.experience.data.storeClerk;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hq.BaseGenerate;
import com.hq.StoreClientMgr;
import com.hq.chainStore.service.buser.apiData.BUserAddApiForm;
import com.hq.chainStore.service.buser.data.BUser;
import com.hq.chainStore.service.storeClerkInfo.bs.StoreAdminRoleInfo4Add;
import com.hq.chainStore.service.storeClerkInfo.bs.StoreClerkInfoMgr;
import com.hq.chainStore.service.storeClerkInfo.data.StoreClerkInfo;
import com.hq.experienceData.TApplyClerk;
import com.hq.experienceData.TClerk;
import com.hq.experienceData.TShopKeeper;
import com.hq.experienceData.TStoreAdminRole;
import com.hq.experienceData.service.DataConstants;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;
import com.hq.zenmind.dao.rest.restSTImpl.CacheMgr4Test;
import com.hq.zenmind.dao.rest.restSTImpl.RestLogerImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestProxySTImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestTemplateMgr;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class GenerateStoreClerkData extends BaseGenerate{
	
	public static void main(String[] args) {
		long phone = 13660623953L;
		
//		String storeService = "http://192.168.10.169:9110/storems/ws/v1";
//		String orderService = "http://192.168.10.169:9110/storereportms/ws/v1";
		
		String storeService = "https://www.zhimeitimes.com:9129/storems/ws/v1";
		String orderService = "https://www.zhimeitimes.com:9129/orderms/ws/v1";
		
		RestTemplateMgr.getInstance().init();
		StoreClientMgr.init(new RestLogerImpl(), new RestProxySTImpl(), new CacheMgr4Test(), storeService, orderService);
		new GenerateStoreClerkData().genData(phone);
	}
	
	private Set<Integer> roleSet = new HashSet<Integer>();
	
	public GenerateStoreClerkData(){
		super();
		roleSet.add(1);
	}
	
	@Override
	public void genData(long phone){
		try {
			initEnv(phone);
			for (Long id : storeIds) {
				this.storeId = id;
				genStoreClerkData();
			}
			System.out.println("Generate StoreClerk Data success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void genStoreClerkData() {
		//添加岗位信息
		List<TStoreAdminRole> roles = TStoreAdminRole.buildTClerkList();
		for (TStoreAdminRole tStoreAdminRole : roles) {
			AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
			StoreClerkInfo storeClerk = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
			StoreAdminRoleInfo4Add addRoleInfo = StoreAdminRoleInfo4Add.newInstance();
			addRoleInfo.setDescript(tStoreAdminRole.getDescript());
			addRoleInfo.setName(tStoreAdminRole.getName());
			addRoleInfo.setPermSet(tStoreAdminRole.getPermSet());
			addRoleInfo.setRoleIdIndex(storeClerk.getRoleIdIndex()+1);
			addRoleInfo.setStoreClerkInfoId(storeClerk.getId());
			addRoleInfo.setStoreId(storeId);
			StoreClerkInfoMgr.getInstance().addStoreAdminRole(addRoleInfo);
			AccessTokenMgr.getInstance().removeOpIdTL();
		}
		
		//添加店员
		List<TClerk> tClerks = TClerk.buildTClerkList();
		for (TClerk tClerk : tClerks) {
			BUserAddApiForm formInfo = BUserAddApiForm.newInstance();
			FastBeanCopyer.getInstance().copy(tClerk, formInfo);
			formInfo.setPassword(DataConstants.PASSWORD);
			BUser buser = regBuser(formInfo, tClerk.getImgPath(), roleSet);
			AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
			StoreClerkInfoMgr.getInstance().addClerk(storeId, buser.getId());
			StoreClerkInfoMgr.getInstance().addRoleSet2Clerk(storeId, "_sci" + storeId, buser.getId(), tClerk.getJobs());
			AccessTokenMgr.getInstance().removeOpIdTL();
		}
		
		//添加店长
		List<TShopKeeper> tShopKeepers = TShopKeeper.buildTShopKeeperList();
		for (TShopKeeper tShopKeeper : tShopKeepers) {
			BUserAddApiForm formInfo = BUserAddApiForm.newInstance();
			FastBeanCopyer.getInstance().copy(tShopKeeper, formInfo);
			formInfo.setPassword(DataConstants.PASSWORD);
			BUser buser = regBuser(formInfo, tShopKeeper.getImgPath(), roleSet);
			AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
			StoreClerkInfoMgr.getInstance().addClerk(storeId, buser.getId());
			StoreClerkInfoMgr.getInstance().addRoleSet2Clerk(storeId, "_sci" + storeId, buser.getId(), tShopKeeper.getJobs());
			AccessTokenMgr.getInstance().removeOpIdTL();
		}

		//添加申请的员工
		List<TApplyClerk> tApplyClerks = TApplyClerk.buildTApplyClerkList();
		for (TApplyClerk tApplyClerk : tApplyClerks) {
			BUserAddApiForm formInfo = BUserAddApiForm.newInstance();
			FastBeanCopyer.getInstance().copy(tApplyClerk, formInfo);
			formInfo.setPassword(DataConstants.PASSWORD);
			BUser buser = regBuser(formInfo, tApplyClerk.getImgPath(), roleSet);
			AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
			StoreClerkInfoMgr.getInstance().applyClerkInfo(storeId, "_sci" + storeId, buser.getId());
			AccessTokenMgr.getInstance().removeOpIdTL();
		}
	}
}

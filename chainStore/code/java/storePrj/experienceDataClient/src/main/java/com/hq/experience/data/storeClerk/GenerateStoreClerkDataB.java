package com.hq.experience.data.storeClerk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.BaseGenerate;
import com.hq.StoreClientMgr;
import com.hq.chainStore.service.buser.apiData.BUserAddApiForm;
import com.hq.chainStore.service.buser.bs.BUserMgr;
import com.hq.chainStore.service.buser.data.BUser;
import com.hq.chainStore.service.common.GenderEnum;
import com.hq.chainStore.service.storeClerkInfo.bs.StoreAdminRoleInfo4Add;
import com.hq.chainStore.service.storeClerkInfo.bs.StoreClerkInfoMgr;
import com.hq.chainStore.service.storeClerkInfo.data.ApplyClerkInfo;
import com.hq.chainStore.service.storeClerkInfo.data.ClerkInfo;
import com.hq.chainStore.service.storeClerkInfo.data.StoreClerkInfo;
import com.hq.chainStore.service.storeClerkInfo.data.adminRole.StoreAdminRole;
import com.hq.experienceData.service.DataConstants;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;
import com.hq.zenmind.dao.rest.restSTImpl.CacheMgr4Test;
import com.hq.zenmind.dao.rest.restSTImpl.RestLogerImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestProxySTImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestTemplateMgr;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestResp;

public class GenerateStoreClerkDataB extends BaseGenerate{
	
	public static void main(String[] args) {
		long phone = 13660623954L;
		
//		String storeService = "http://192.168.10.169:9110/storems/ws/v1";
//		String orderService = "http://192.168.10.169:9110/storereportms/ws/v1";
		
		String storeService = "https://www.zhimeitimes.com:9129/storems/ws/v1";
		String orderService = "https://www.zhimeitimes.com:9129/orderms/ws/v1";
		
		RestTemplateMgr.getInstance().init();
		StoreClientMgr.init(new RestLogerImpl(), new RestProxySTImpl(), new CacheMgr4Test(), storeService, orderService);
		new GenerateStoreClerkDataB().genData(phone);
	}
	
	public GenerateStoreClerkDataB(){
		super();
	}
	
	@Override
	public void genData(long phone){
		try {
			initEnv(phone);
			AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
			StoreClerkInfo storeClerk = StoreClerkInfoMgr.getInstance().getByStoreId(5L);
			this.storeId = 14L;
			genStoreClerkData(storeClerk);
			AccessTokenMgr.getInstance().removeOpIdTL();
			System.out.println("Generate StoreClerk Data success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void genStoreClerkData(StoreClerkInfo storeClerk) {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreClerkInfo tmpStoreClerk = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
		AccessTokenMgr.getInstance().removeOpIdTL();
		
		//添加岗位信息
		List<StoreAdminRole> roles = new ArrayList<StoreAdminRole>(storeClerk.getRoleMap().values());
		Collections.sort(roles, new Comparator<StoreAdminRole>() {  
            @Override
            public int compare(StoreAdminRole o1, StoreAdminRole o2) {  
                return o1.getId() - o2.getId();  
            }
        });
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		for (StoreAdminRole role : roles) {
			if(role.getId() > 4){
				StoreAdminRoleInfo4Add addRoleInfo = StoreAdminRoleInfo4Add.newInstance();
				addRoleInfo.setDescript(role.getDescript());
				addRoleInfo.setName(role.getName());
				addRoleInfo.setPermSet(role.getPermSet());
				addRoleInfo.setRoleIdIndex(role.getId());
				addRoleInfo.setStoreClerkInfoId(tmpStoreClerk.getId());
				addRoleInfo.setStoreId(storeId);
				StoreClerkInfoMgr.getInstance().addStoreAdminRole(addRoleInfo);
			}
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
		
		//添加店员
		List<ClerkInfo> clerkInfos = new ArrayList<ClerkInfo>(storeClerk.getClerkInfoMap().values());
		Collections.sort(clerkInfos, new Comparator<ClerkInfo>() {  
            @Override  
            public int compare(ClerkInfo o1, ClerkInfo o2) {
            	return Long.compare(o1.getCreateTime(), o2.getCreateTime());
            }
        });
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		for (ClerkInfo clerkInfo : clerkInfos) {
			BUser bUser = BUserMgr.getInstance().get(clerkInfo.getBuserId());
			if(bUser.getRoleSet().contains(1)){
				BUserAddApiForm formInfo = BUserAddApiForm.newInstance();
				FastBeanCopyer.getInstance().copy(bUser, formInfo);
				formInfo.setPassword(DataConstants.PASSWORD);
				BUser buser = regBuser2(formInfo);
				StoreClerkInfoMgr.getInstance().addClerk(storeId, buser.getId());
				StoreClerkInfoMgr.getInstance().addRoleSet2Clerk(storeId, tmpStoreClerk.getId(), buser.getId(), clerkInfo.getRoleSet());
			}
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
		
		//添加申请的员工
		List<ApplyClerkInfo> applyClerkInfos = new ArrayList<ApplyClerkInfo>(storeClerk.getApplyClerkInfoMap().values());
		Collections.sort(applyClerkInfos, new Comparator<ApplyClerkInfo>() {  
            @Override  
            public int compare(ApplyClerkInfo o1, ApplyClerkInfo o2) {
            	return Long.compare(o1.getCreateTime(), o2.getCreateTime());
            }
        });
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		for (ApplyClerkInfo tApplyClerk : applyClerkInfos) {
			BUser bUser = BUserMgr.getInstance().get(tApplyClerk.getBuserId());
			BUserAddApiForm formInfo = BUserAddApiForm.newInstance();
			FastBeanCopyer.getInstance().copy(bUser, formInfo);
			formInfo.setPassword(DataConstants.PASSWORD);
			formInfo.setGender(GenderEnum.FEMALE.ordinal());
			BUser buser = regBuser2(formInfo);
			StoreClerkInfoMgr.getInstance().applyClerkInfo(storeId, tmpStoreClerk.getId(), buser.getId());
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	
	private BUser regBuser2(BUserAddApiForm formInfo){
		long phone = 13500000000L + RandomUtils.nextLong(100000000L, 999999999L);
		formInfo.setPhone(phone);
		BUser buser = doRegBuser(formInfo);
		while (buser==null) {
			formInfo.setPhone(formInfo.getPhone()+1);
			buser = doRegBuser(formInfo);
		}
		return buser;
	}
	
	private BUser doRegBuser(BUserAddApiForm formInfo){
		BUser buser = null;
		RestResp restResp = null;
		try {
			restResp = BUserMgr.getInstance().reg(formInfo);
			if(restResp!=null && restResp.gettJson() != null){
				buser = JsonUtil.getInstance().fromJson(restResp.gettJson(), BUser.class);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return buser;
	}
	
}

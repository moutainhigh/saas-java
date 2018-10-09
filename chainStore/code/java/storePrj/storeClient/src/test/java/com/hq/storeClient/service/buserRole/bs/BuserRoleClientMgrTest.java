package com.hq.storeClient.service.buserRole.bs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.service.buser.apiData.BUserCommQueryForm;
import com.hq.storeClient.service.buser.bs.BUserClientMgr;
import com.hq.storeClient.service.buser.data.BUser;
import com.hq.storeClient.service.buserRole.apiData.BatchPermForm;
import com.hq.storeClient.service.buserRole.apiData.BuserRoleUpdateInfoForm;
import com.hq.storeClient.service.buserRole.apiData.OperateEnum;
import com.hq.storeClient.service.buserRole.data.BuserRole;
import com.hq.storeClient.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;
import com.hq.storeClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class BuserRoleClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	//给系统所有的老板用户，新增[移除]指定的权限
	@Test
	public void testBatchPerm() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		BUserCommQueryForm queryForm = BUserCommQueryForm.newInstance();
		Set<Integer> roleSet = new HashSet<Integer>();
		roleSet.add(0);
		queryForm.setRoleSet(roleSet);
		PageResp<BUser> pageInfo = BUserClientMgr.getInstance().findByCond(queryForm);
		System.out.println(pageInfo.getTotalCount());
		
		Set<Long> buserIds=new HashSet<Long>();
		List<BUser> list = pageInfo.getList();
		for (BUser bUser : list) {
			buserIds.add(bUser.getId());
		}
		System.out.println(buserIds);
		Set<Integer> perms=new HashSet<Integer>();
		perms.add(StoreAdminPermEnum.STORE_CONFIG_ADMIN.ordinal());
		perms.add(StoreAdminPermEnum.DAYSNAPSHOT_ADMIN.ordinal());
		perms.add(StoreAdminPermEnum.OPLOG_ADMIN.ordinal());
		BatchPermForm inputForm = BatchPermForm.newInstance();
		inputForm.setBuserIds(buserIds);
		inputForm.setPerms(perms);
		inputForm.setOperate(OperateEnum.Add.ordinal());
		System.out.println(JsonUtil.getInstance().toJson(inputForm));
		BuserRoleClientMgr.getInstance().batchPerm(inputForm);
		ValidateThreadLocal.getInstance().remove();
	}
	
	@Test
	public void testGet() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long buserId = 20L;
		BuserRole buserRole = BuserRoleClientMgr.getInstance().get(buserId);
		System.out.println(JsonUtil.getInstance().toJson(buserRole));
		ValidateThreadLocal.getInstance().remove();
	}

	@Test
	public void testUpdateLongBuserRoleUpdateInfoForm() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long buserId = 20L;
		BuserRoleUpdateInfoForm inputForm = BuserRoleUpdateInfoForm.newInstance();
		inputForm.setBuserLimit(109);
		inputForm.getPermSet().add(1);
		inputForm.getPermSet().add(5);
		BuserRoleClientMgr.getInstance().updateBuserRoleInfo(buserId, inputForm);
		ValidateThreadLocal.getInstance().remove();
	}

}

package com.hq.chainStore.service.workFlowType.bs;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.workFlowType.apiData.AddWorkFlowTypeForm;
import com.hq.chainStore.service.workFlowType.apiData.UpdWorkFlowTypeInfoForm;
import com.hq.chainStore.service.workFlowType.data.SysFixCompEnum;
import com.hq.chainStore.service.workFlowType.data.WFCompEnum;
import com.hq.chainStore.service.workFlowType.data.WFCompInfo;
import com.hq.chainStore.service.workFlowType.data.WorkFlowType;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;

public class WorkFlowTypeMgrTest {
	private static Boss boss;
	private static long storeId=21L;
	
	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
		//老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		boss.setStoreId(storeId);
		boss.login();
	}
	
	@Test
	public void testAdd() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		AddWorkFlowTypeForm addWorkFlowTypeForm = AddWorkFlowTypeForm.newInstance();
		addWorkFlowTypeForm.setWfCompName("进店服务");
		List<WFCompInfo> wfCompInfos = new ArrayList<WFCompInfo>();
		int WFCompEnumLen = WFCompEnum.values().length;
		wfCompInfos.add(WFCompInfo.newInstance(RandomUtils.nextInt(0, WFCompEnumLen), RandomUtils.nextInt(0, 5),"描述-"+RandomUtils.nextInt(0, 5)));
		wfCompInfos.add(WFCompInfo.newInstance(RandomUtils.nextInt(0, WFCompEnumLen), RandomUtils.nextInt(0, 5),"描述-"+RandomUtils.nextInt(0, 5)));
		wfCompInfos.add(WFCompInfo.newInstance(RandomUtils.nextInt(0, WFCompEnumLen), RandomUtils.nextInt(0, 5),"描述-"+RandomUtils.nextInt(0, 5)));
		addWorkFlowTypeForm.setWfCompInfos(wfCompInfos);
		WorkFlowType workFlowType = WorkFlowTypeMgr.getInstance().addWorkFlowType(addWorkFlowTypeForm);
		System.out.println(JsonUtil.getInstance().toJson(workFlowType));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdateInfo() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		long workFlowTypeId = 4L;
		WorkFlowType workFlowType = WorkFlowTypeMgr.getInstance().getWorkFlowType(workFlowTypeId);
		System.out.println(JsonUtil.getInstance().toJson(workFlowType));
		
		UpdWorkFlowTypeInfoForm updWorkFlowTypeInfoForm = UpdWorkFlowTypeInfoForm.newInstance();
		updWorkFlowTypeInfoForm.setWfCompName("会员充值");
		List<WFCompInfo> wfCompInfos = new ArrayList<WFCompInfo>();
//		int[] wfComp={0,1,2,3,4,6,7};
		int[] wfComp={0,5,6,7};
		for (int i = 0; i < wfComp.length; i++) {
			WFCompInfo info = WFCompInfo.newInstance();
			info.setDescipt("descipt-"+RandomUtils.nextInt(0, 10));
			info.setIndex(i);
			info.setWfComp(wfComp[i]);
			wfCompInfos.add(info);
		}
		updWorkFlowTypeInfoForm.setWfCompInfos(wfCompInfos);
		WorkFlowTypeMgr.getInstance().updWorkFlowTypeInfo(workFlowType.getId(), updWorkFlowTypeInfoForm);
		WorkFlowType workFlowType2 = WorkFlowTypeMgr.getInstance().getWorkFlowType(workFlowTypeId);
		System.out.println(JsonUtil.getInstance().toJson(workFlowType2));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testGet() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
//		long workFlowTypeId = 1L;
//		WorkFlowType workFlowType = WorkFlowTypeMgr.getInstance().getWorkFlowType(workFlowTypeId);
		WorkFlowType workFlowType = WorkFlowTypeMgr.getInstance().findByName("购买消费");
		System.out.println(JsonUtil.getInstance().toJson(workFlowType));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	//更新购买消费
	@Test
	public void testUpdBuyType() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
		String buyItem = SysFixCompEnum.BuyComp.getMark();
		WorkFlowType workFlowType = WorkFlowTypeMgr.getInstance().findByName(buyItem);
		System.out.println(JsonUtil.getInstance().toJson(workFlowType));
		
		UpdWorkFlowTypeInfoForm updWorkFlowTypeInfoForm = UpdWorkFlowTypeInfoForm.newInstance();
		
		List<WFCompInfo> wfCompInfos = new ArrayList<WFCompInfo>();
		wfCompInfos.add(WFCompInfo.newInstance(WFCompEnum.SelectCuserWFComp,1));
		wfCompInfos.add(WFCompInfo.newInstance(WFCompEnum.SelectFollowPersonWFComp,2));
		wfCompInfos.add(WFCompInfo.newInstance(WFCompEnum.SelectProductWFComp,3));
		wfCompInfos.add(WFCompInfo.newInstance(WFCompEnum.SelectPrdCardProdWFComp,4));
		wfCompInfos.add(WFCompInfo.newInstance(WFCompEnum.SelectGoodsWFComp,5));
		wfCompInfos.add(WFCompInfo.newInstance(WFCompEnum.SelectPrdCardWFComp,6));
		wfCompInfos.add(WFCompInfo.newInstance(WFCompEnum.PayTheBillWFComp,7));
		wfCompInfos.add(WFCompInfo.newInstance(WFCompEnum.OrderWFComp,8));
		wfCompInfos.add(WFCompInfo.newInstance(WFCompEnum.BuyBonusWFComp,9));
		updWorkFlowTypeInfoForm.setWfCompInfos(wfCompInfos);
		updWorkFlowTypeInfoForm.setWfCompName(buyItem);
		WorkFlowTypeMgr.getInstance().updWorkFlowTypeInfo(workFlowType.getId(), updWorkFlowTypeInfoForm);
		
		WorkFlowType workFlowType2 = WorkFlowTypeMgr.getInstance().findByName(buyItem);
		System.out.println(JsonUtil.getInstance().toJson(workFlowType2));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	//更新会员充值的组件
	@Test
	public void testUpdMemCard() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
		String memCard = SysFixCompEnum.MemCardComp.getMark();
		WorkFlowType workFlowType = WorkFlowTypeMgr.getInstance().findByName(memCard);
		System.out.println(JsonUtil.getInstance().toJson(workFlowType));
		
		UpdWorkFlowTypeInfoForm updWorkFlowTypeInfoForm = UpdWorkFlowTypeInfoForm.newInstance();
		
		List<WFCompInfo> wfCompInfos = new ArrayList<WFCompInfo>();
		wfCompInfos.add(WFCompInfo.newInstance(WFCompEnum.SelectCuserWFComp,1));
		wfCompInfos.add(WFCompInfo.newInstance(WFCompEnum.SelectFollowPersonWFComp,2));
		wfCompInfos.add(WFCompInfo.newInstance(WFCompEnum.RechargeMemCardWFComp,3));
		wfCompInfos.add(WFCompInfo.newInstance(WFCompEnum.PayTheBillWFComp,4));
		wfCompInfos.add(WFCompInfo.newInstance(WFCompEnum.OrderWFComp,5));
		wfCompInfos.add(WFCompInfo.newInstance(WFCompEnum.RechargeBonusWFComp,6));
		updWorkFlowTypeInfoForm.setWfCompInfos(wfCompInfos);
		updWorkFlowTypeInfoForm.setWfCompName(memCard);
		WorkFlowTypeMgr.getInstance().updWorkFlowTypeInfo(workFlowType.getId(), updWorkFlowTypeInfoForm);
		
		WorkFlowType workFlowType2 = WorkFlowTypeMgr.getInstance().findByName(memCard);
		System.out.println(JsonUtil.getInstance().toJson(workFlowType2));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdateInfo2() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		long workFlowTypeId = 4L;
		UpdWorkFlowTypeInfoForm updWorkFlowTypeInfoForm = UpdWorkFlowTypeInfoForm.newInstance();
		updWorkFlowTypeInfoForm.setWfCompName("会员充值");
		List<WFCompInfo> wfCompInfos = new ArrayList<WFCompInfo>();
		int[] wfComp={0,5,6,7};
		for (int i = 0; i < wfComp.length; i++) {
			WFCompInfo info = WFCompInfo.newInstance();
			info.setDescipt("descipt-"+RandomUtils.nextInt(0, 10));
			info.setIndex(i);
			info.setWfComp(wfComp[i]);
			wfCompInfos.add(info);
		}
		updWorkFlowTypeInfoForm.setWfCompInfos(wfCompInfos);
		WorkFlowTypeMgr.getInstance().updWorkFlowTypeInfo(workFlowTypeId, updWorkFlowTypeInfoForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}

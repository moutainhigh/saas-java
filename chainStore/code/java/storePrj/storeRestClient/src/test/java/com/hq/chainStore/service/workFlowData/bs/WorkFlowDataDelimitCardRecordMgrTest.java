package com.hq.chainStore.service.workFlowData.bs;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.leaguerDetail.bs.LeaguerDetailMgr;
import com.hq.chainStore.service.leaguerDetail.data.LeaguerDetail;
import com.hq.chainStore.service.productCardDetail.data.ProductCardItem;
import com.hq.chainStore.service.storeLeaguerInfo.data.LeaguerProductCard;
import com.hq.chainStore.service.workFlowData.apiData.DelimitCardRecordAddForm;
import com.hq.chainStore.service.workFlowData.apiData.DelimitCardRecordUpdateForm;
import com.hq.chainStore.service.workFlowData.data.WorkFlowData;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;

public class WorkFlowDataDelimitCardRecordMgrTest {
	private static Boss boss;
	private static long storeId = 0L;
	
	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
		//老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		boss.login();
		storeId = boss.getRobotStoreId();
	}
	
	@Test
	public void testAdd() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		LeaguerDetail leaguerDetail = LeaguerDetailMgr.getInstance().getLeaguerDetail(storeId, "16052_67194");
		List<LeaguerProductCard> list = new ArrayList<LeaguerProductCard>(leaguerDetail.getLeaguerProductCardMap().values());
		LeaguerProductCard leaguerProductCard = list.get(RandomUtils.nextInt(0, list.size()));
		List<ProductCardItem> productCardItems = leaguerProductCard.getProductCardItems();
		ProductCardItem productCardItem = productCardItems.get(RandomUtils.nextInt(0, productCardItems.size()));
		
		long workFlowDataId = 7588L;
		DelimitCardRecordAddForm addForm = DelimitCardRecordAddForm.newInstance();
		addForm.setCount(RandomUtils.nextInt(0, productCardItem.getCount()));
		addForm.setLeaguerPrdCardId(leaguerProductCard.getId());
		addForm.setItemType(productCardItem.getItemType());
		addForm.setPgId(productCardItem.getPgId());
		WorkFlowData record = WorkFlowDataDelimitCardRecordMgr.getInstance().addDelimitCardRecord(workFlowDataId, addForm);
		System.out.println(JsonUtil.getInstance().toJson(record));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdate() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		long workFlowDataId = 7588L;
		String delimitCardId="_leaguerPrdCard_1_1_0";
		DelimitCardRecordUpdateForm inputForm = DelimitCardRecordUpdateForm.newInstance();
		inputForm.setDelimitCardId(delimitCardId);
		inputForm.setCount(RandomUtils.nextInt(0, 10));
		WorkFlowData record = WorkFlowDataDelimitCardRecordMgr.getInstance().updateDelimitCardRecord(workFlowDataId, delimitCardId, inputForm);
		System.out.println(JsonUtil.getInstance().toJson(record));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}

package com.hq.chainStore.service.order.bs;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.order.data.PayItem;
import com.hq.chainStore.service.orderNotes.apiData.ChargeBackRecordAddForm;
import com.hq.chainStore.service.orderNotes.bs.ChargeBackRecordMgr;
import com.hq.chainStore.service.orderNotes.data.ChargeBackItem;
import com.hq.chainStore.service.orderNotes.data.OrderNotes;
import com.hq.chainStore.service.store.bs.StoreMgr;
import com.hq.chainStore.service.store.data.Store;
import com.hq.common.validate.info.ValidateInfo;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;

public class ChargeBackRecordMgrTest {
	private static Boss boss;
	private static long storeId = 0L;

	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();

		// 老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		boss.login();
		storeId = boss.getRobotStoreId();

		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		Store store = StoreMgr.getInstance().get(storeId);
		AccessTokenMgr.getInstance().putValidateInfo(boss.getId(), ValidateInfo.newInstance(store.getBossId(), storeId));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

	@Test
	public void testAdd() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		long orderId = 1772L;
		ChargeBackRecordAddForm inputForm = ChargeBackRecordAddForm.newInstance();
		inputForm.setChargeBackItems(buildChargeBackItems());
		inputForm.setPayItems(buildPayItems());
		inputForm.setRemark("退单备注222");
		OrderNotes orderNotes2 = ChargeBackRecordMgr.getInstance().addChargeBackRecord(orderId, inputForm);
		System.out.println(JsonUtil.getInstance().toJson(orderNotes2));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

	private List<ChargeBackItem> buildChargeBackItems() {
		List<ChargeBackItem> chargeBackItems = new ArrayList<ChargeBackItem>();
		ChargeBackItem item = ChargeBackItem.newInstance();
		item.setItemId("_buy_0__prd_16052_2");
		item.setBuyType(0);
		item.setPgId("_prd_16052_2");
		item.setPgName("dddd");
		item.setCount(2);
		item.setCost(0f);
		chargeBackItems.add(item);
		return chargeBackItems;
	}

	private List<PayItem> buildPayItems() {
		List<PayItem> payItems = new ArrayList<PayItem>();
		payItems.add(PayItem.newInstance(1, 500f));
		return payItems;
	}
}

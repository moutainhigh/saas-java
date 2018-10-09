package com.hq.chainStore.service.storeGoods.bs;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.goodsDetail.apiData.GoodsDetailQueryForm;
import com.hq.chainStore.service.goodsDetail.bs.GoodsDetailMgr;
import com.hq.chainStore.service.goodsDetail.data.GoodsDetail;
import com.hq.chainStore.service.goodsDetail.data.GoodsDetailDataHolder;
import com.hq.chainStore.service.storeGoods.data.Goods;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.buser.robot.BRobotData;
import com.zenmind.common.json.JsonUtil;

public class GoodsDetailMgrTest {
	private static Boss boss;
	private static long storeId = 0L;

	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
		// 老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		BRobotData data = boss.getRobot().getData();
		data.setPassword("123456");
		data.setPhone(13660623953L);
		boss.login();
		storeId = boss.getRobotStoreId();
	}

	@Test
	public void testGet() {
		Goods goods = boss.getRandomGoods(storeId);
//		Goods goods = boss.getGoods(storeId, "_cgti_15_2");
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		GoodsDetail goodsDetail1 = GoodsDetailMgr.getInstance().getGoodsDetail(storeId, goods.getId());
		System.out.println(JsonUtil.getInstance().toJson(goodsDetail1));
		GoodsDetail goodsDetail = GoodsDetailDataHolder.getInstance().getData(String.valueOf(boss.getId()), storeId+"_"+goods.getId(), storeId);
		System.out.println(JsonUtil.getInstance().toJson(goodsDetail));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	
	@Test
	public void testList() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		GoodsDetailQueryForm queryForm = GoodsDetailQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		queryForm.setTypeId("1");
		PageResp<GoodsDetail> page = GoodsDetailMgr.getInstance().getGoodsDetailPageInfo(queryForm);
		System.out.println(page.getTotalCount());
		List<GoodsDetail> list = page.getList();
		for (GoodsDetail detail : list) {
			System.out.println(JsonUtil.getInstance().toJson(detail));
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}

package com.hq.chainClient.service.chainGoods.bs;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainClient.common.restClientResp.PageResp;
import com.hq.chainClient.service.chainGoods.apiData.GoodsDetailQueryForm;
import com.hq.chainClient.service.chainGoods.apiData.GoodsUpdateForm;
import com.hq.chainClient.service.chainGoods.data.Goods;
import com.hq.chainClient.service.chainGoods.data.GoodsDetail;
import com.hq.chainClient.testClass.AccessTokenMgr;
import com.hq.chainClient.testClass.TestEnv;
import com.hq.chainClient.testClass.robot.Boss;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.json.JsonUtil;

public class GoodsDetailClientMgrTest {
	private static Boss boss;
	private static long chainId = 0L;

	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
		// 老板登陆
		boss = Boss.newBoss(13660623953L, "123456");
		boss.login();
		chainId = boss.getChainId();
	}

	@Test
	public void testGet() {
		Goods goods = boss.getRandomGoods(chainId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		GoodsDetail goodsDetail = GoodsDetailClientMgr.getInstance().getGoodsDetail(chainId, goods.getId());
		System.out.println(JsonUtil.getInstance().toJson(goodsDetail));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdate() {
		Goods goods = boss.getRandomGoods(chainId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		GoodsDetail goodsDetail = GoodsDetailClientMgr.getInstance().getGoodsDetail(chainId, goods.getId());
		System.out.println(JsonUtil.getInstance().toJson(goodsDetail));
		
		GoodsUpdateForm inputForm = GoodsUpdateForm.newInstance();
		FastBeanCopyer.getInstance().copy(goodsDetail, inputForm);
		inputForm.setDescript("描述。。。。。。");
		ChainGoodsClientMgr.getInstance().updateGoods(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testList() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		GoodsDetailQueryForm queryForm = GoodsDetailQueryForm.newInstance();
		queryForm.setChainId(chainId);
		PageResp<GoodsDetail> page = GoodsDetailClientMgr.getInstance().getGoodsDetailPageInfo(queryForm);
		System.out.println(page.getTotalCount());
		List<GoodsDetail> list = page.getList();
		for (GoodsDetail detail : list) {
			System.out.println(JsonUtil.getInstance().toJson(detail));
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}

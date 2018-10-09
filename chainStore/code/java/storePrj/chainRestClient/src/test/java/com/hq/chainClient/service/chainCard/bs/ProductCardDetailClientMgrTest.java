package com.hq.chainClient.service.chainCard.bs;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainClient.common.restClientResp.PageResp;
import com.hq.chainClient.service.chainCard.apiData.ProductCardDetailQueryForm;
import com.hq.chainClient.service.chainCard.data.ProductCard;
import com.hq.chainClient.service.chainCard.data.ProductCardDetail;
import com.hq.chainClient.testClass.AccessTokenMgr;
import com.hq.chainClient.testClass.TestEnv;
import com.hq.chainClient.testClass.robot.Boss;
import com.zenmind.common.json.JsonUtil;

public class ProductCardDetailClientMgrTest {
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
		ProductCard ProductCard = boss.getRandomProductCard(chainId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ProductCardDetail ProductCardDetail = ProductCardDetailClientMgr.getInstance().getProductCardDetail(chainId, ProductCard.getId());
		System.out.println(JsonUtil.getInstance().toJson(ProductCardDetail));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testList() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ProductCardDetailQueryForm queryForm = ProductCardDetailQueryForm.newInstance();
		queryForm.setChainId(chainId);
		PageResp<ProductCardDetail> page = ProductCardDetailClientMgr.getInstance().getProductCardDetailPageInfo(queryForm);
		System.out.println(page.getTotalCount());
		List<ProductCardDetail> list = page.getList();
		for (ProductCardDetail detail : list) {
			System.out.println(JsonUtil.getInstance().toJson(detail));
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}

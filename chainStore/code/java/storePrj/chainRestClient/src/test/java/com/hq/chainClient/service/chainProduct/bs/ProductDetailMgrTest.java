package com.hq.chainClient.service.chainProduct.bs;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainClient.common.restClientResp.PageResp;
import com.hq.chainClient.service.chainProduct.apiData.ProductDetailQueryForm;
import com.hq.chainClient.service.chainProduct.data.Product;
import com.hq.chainClient.service.chainProduct.data.ProductDetail;
import com.hq.chainClient.testClass.AccessTokenMgr;
import com.hq.chainClient.testClass.TestEnv;
import com.hq.chainClient.testClass.robot.Boss;
import com.zenmind.common.json.JsonUtil;

public class ProductDetailMgrTest {
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
		Product product = boss.getRandomProduct(chainId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ProductDetail ProductDetail = ProductDetailClientMgr.getInstance().getProductDetail(chainId, product.getId());
		System.out.println(JsonUtil.getInstance().toJson(ProductDetail));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testList() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ProductDetailQueryForm queryForm = ProductDetailQueryForm.newInstance();
		queryForm.setChainId(chainId);
		PageResp<ProductDetail> page = ProductDetailClientMgr.getInstance().getProductDetailPageInfo(queryForm);
		System.out.println(page.getTotalCount());
		List<ProductDetail> list = page.getList();
		for (ProductDetail detail : list) {
			System.out.println(JsonUtil.getInstance().toJson(detail));
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}

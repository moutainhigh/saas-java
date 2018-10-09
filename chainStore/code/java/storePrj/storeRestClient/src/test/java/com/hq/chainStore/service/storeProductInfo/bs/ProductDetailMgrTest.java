package com.hq.chainStore.service.storeProductInfo.bs;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.productDetail.apiData.ProductDetailQueryForm;
import com.hq.chainStore.service.productDetail.bs.ProductDetailMgr;
import com.hq.chainStore.service.productDetail.data.ProductDetail;
import com.hq.chainStore.service.storeProductInfo.data.ProductInfo;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.buser.robot.BRobotData;
import com.zenmind.common.json.JsonUtil;

public class ProductDetailMgrTest {
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
		ProductInfo productInfo = boss.getRandomProductInfo(storeId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ProductDetail detail = ProductDetailMgr.getInstance().getProductDetail(storeId, productInfo.getId());
		System.out.println(JsonUtil.getInstance().toJson(detail));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testList() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ProductDetailQueryForm queryForm = ProductDetailQueryForm.newInstance();
//		queryForm.setNumberOrName("a79a3c18");//商品-26
//		queryForm.setState(0);
//		queryForm.setTypeId(22L);
		queryForm.setStoreId(storeId);
		PageResp<ProductDetail> page = ProductDetailMgr.getInstance().getProductDetailPageInfo(queryForm);
		System.out.println(page.getTotalCount());
		List<ProductDetail> list = page.getList();
		for (ProductDetail detail : list) {
			System.out.println(JsonUtil.getInstance().toJson(detail));
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}

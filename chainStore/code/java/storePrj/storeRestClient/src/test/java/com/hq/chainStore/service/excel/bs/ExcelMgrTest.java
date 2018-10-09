package com.hq.chainStore.service.excel.bs;

import java.io.File;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.excel.apiData.ExcelUpLoadForm;
import com.hq.chainStore.service.excel.data.ExcelGoods;
import com.hq.chainStore.service.excel.data.ExcelLeaguer;
import com.hq.chainStore.service.excel.data.ExcelProduct;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestResp;

/**
 * @ClassName: ExcelMgrTest
 * @Description: Excel解析模板测试用例
 * @author helen
 * @date 2018年4月11日 上午10:13:21
 * 
 */
public class ExcelMgrTest {

	private static Boss boss;

	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();

		// 老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		boss.login();
	}

	@Test
	public void testResolveLeaguerExcel() {
		File file = new File("F:/会员批量导入模板.xlsx");

		ExcelUpLoadForm upForm = ExcelUpLoadForm.newInstance();
		upForm.setModuleId("400");
		upForm.setModuleType("leaguer");
		RestResp restResp = MyExcelMgr.getInstance().resolveLeaguerExcel(upForm, file);
		String json = restResp.gettListJson();
		List<ExcelLeaguer> targetList = JsonUtil.getInstance().parseList(json, ExcelLeaguer.class);
		for (ExcelLeaguer excelLeaguer : targetList) {
			System.out.println(excelLeaguer.toString());
		}

	}
	
	@Test
	public void testResolveProductExcel() {
		File file = new File("E:/项目批量导入模板.xlsx");

		ExcelUpLoadForm upForm = ExcelUpLoadForm.newInstance();
		upForm.setModuleId("400");
		upForm.setModuleType("product");
		RestResp restResp = MyExcelMgr.getInstance().resolveProductExcel(upForm, file);
		String json = restResp.gettListJson();
		List<ExcelProduct> targetList = JsonUtil.getInstance().parseList(json, ExcelProduct.class);
		for (ExcelProduct excelProduct : targetList) {
			System.out.println(excelProduct.toString());
		}

	}
	
	@Test
	public void testResolveGoodsExcel() {
		File file = new File("E:/商品批量导入模板.xlsx");

		ExcelUpLoadForm upForm = ExcelUpLoadForm.newInstance();
		upForm.setModuleId("400");
		upForm.setModuleType("goods");
		RestResp restResp = MyExcelMgr.getInstance().resolveGoodsExcel(upForm, file);
		String json = restResp.gettListJson();
		List<ExcelGoods> targetList = JsonUtil.getInstance().parseList(json, ExcelGoods.class);
		for (ExcelGoods excelGoods : targetList) {
			System.out.println(excelGoods.toString());
		}
	}

}

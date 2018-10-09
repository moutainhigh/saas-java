package com.hq.chainStore.service.storeMaterialInfo.bs;

import java.util.Map;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.store.data.Store;
import com.hq.chainStore.service.storeMaterialInfo.data.MaterialInfo;
import com.hq.chainStore.service.storeMaterialInfo.data.StoreMaterialInfo;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.materialRecords.MaterialRecord;
import com.hq.testClass.robot.materialRecords.robot.MaterialRecordsRobot;
import com.hq.testClass.robot.storeMaterialInfo.Material;
import com.hq.testClass.robot.storeMaterialInfo.robot.MaterialRobot;
import com.zenmind.common.json.JsonUtil;

public class StoreMaterialInfoMgrTest {

	private static Boss boss;
	
	private static Store store;
	
	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
		
		int mark = RandomUtils.nextInt(0, 1000);
		//老板注册、登陆、开店、提交审核
		boss = Boss.newBoss(BRobot.newRandom());
		boss.reg();
		boss.login();

		//开店
		store = boss.openSimpleStore(mark);
	}
	
	@Test
	public void testAll() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		Material material = Material.newInstance(MaterialRobot.newRandom());
		//添加耗材
		StoreMaterialInfo storeMaterialInfo = material.addMaterial(store.getId());
		//再添加耗材
		storeMaterialInfo = material.addMaterial(store.getId());
		
		Map<String, MaterialInfo> materialInfoMap = storeMaterialInfo.getMaterialInfoMap();
		
		for (MaterialInfo item : materialInfoMap.values()) {
			String materialId = item.getId();
			//修改耗材详情
			material.updateMaterialInfo(materialId);
			//验证
			storeMaterialInfo = material.getById();
			MaterialInfo materialInfo = storeMaterialInfo.getMaterialInfoMap().get(materialId);
			Assert.assertTrue("修改后的name为：", materialInfo.getName().equals("修改后的耗材"));
			
			MaterialRecord materialRecord = MaterialRecord.newInstance(MaterialRecordsRobot.newRandom());
			//添加进货记录
			materialRecord.addMaterialRecords(store.getId(), materialId);
			
			//更新库存数量
			material.updateMaterialInventory(materialId);
			//验证
			storeMaterialInfo = material.getById();
			materialInfo = storeMaterialInfo.getMaterialInfoMap().get(materialId);
			Assert.assertTrue("修改后的inventory为：", materialInfo.getInventory() == 2000);
		}
		System.out.println(JsonUtil.getInstance().toJson(storeMaterialInfo));
	}
	
}

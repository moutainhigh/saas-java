package com.hq.chainStore.service.materialRecords.bs;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.materialRecords.data.MaterialRecords;
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

public class MaterialRecordsMgrTest {

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
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
	}
	
	@Test
	public void testDecimalFormat(){
		DecimalFormat df = new DecimalFormat("0.00");
		String format = df.format(0);
		System.out.println(format);
	}
	
	@Test
	public void testAll() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		Material material = Material.newInstance(MaterialRobot.newRandom());
		//添加耗材
		StoreMaterialInfo storeMaterialInfo = material.addMaterial(store.getId());
		
		Map<String, MaterialInfo> materialInfoMap = storeMaterialInfo.getMaterialInfoMap();
		
		for (MaterialInfo item : materialInfoMap.values()) {
			String materialId = item.getId();
			
			MaterialRecord materialRecord = MaterialRecord.newInstance(MaterialRecordsRobot.newRandom());
			//添加进货记录
			materialRecord.addMaterialRecords(store.getId(), materialId);
			//验证
			storeMaterialInfo = material.getById();
			MaterialInfo materialInfo = storeMaterialInfo.getMaterialInfoMap().get(materialId);
			Assert.assertTrue("库存应该不为0：", materialInfo.getInventory() != 0);
			
			//获取进货记录
			MaterialRecords byId = materialRecord.getById();
			//验证
			Assert.assertTrue("添加的进货记录的storeId应该为：", byId.getStoreId() == store.getId());
			
			//通过storeId获取店铺所有耗材进货记录
			List<MaterialRecords> findByStoreId = materialRecord.findByStoreId(store.getId());
			//验证
			Assert.assertTrue("进货记录应该>0：", findByStoreId.size()>0);
			
			//通过耗材id获取该耗材进货记录
			List<MaterialRecords> findByMaterialId = materialRecord.findByMaterialId(materialId);
			//验证
			Assert.assertTrue("进货记录应该>0：", findByMaterialId.size()>0);
			System.out.println(JsonUtil.getInstance().toJson(findByMaterialId));
		}
	}
	
}

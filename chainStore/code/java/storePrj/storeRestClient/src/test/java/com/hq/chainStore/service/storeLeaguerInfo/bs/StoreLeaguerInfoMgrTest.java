package com.hq.chainStore.service.storeLeaguerInfo.bs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import com.hq.chainStore.service.leaguerDetail.bs.LeaguerDetailMgr;
import com.hq.chainStore.service.leaguerDetail.data.LeaguerDetail;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.LeaguerAddApiForm;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.LeaguerDelApiForm;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.LeaguerUpdateInfoApiForm;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.StoreLeaguerService;
import com.hq.chainStore.service.storeLeaguerInfo.data.Leaguer;
import com.hq.chainStore.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.hq.chainStore.service.storeLeaguerInfo.data.StoreLeaguerInfoSynDataHolder;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.buser.robot.BRobotData;
import com.hq.testClass.robot.storeLeaguerInfo.LeaguerRobot;
import com.zenmind.common.json.JsonUtil;


public class StoreLeaguerInfoMgrTest {
	private static Boss boss;
	private static long storeId=21L;

	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
		//老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		BRobotData data = boss.getRobot().getData();
		data.setPassword("123456");
		data.setPhone(13660623953L);
		boss.login();
		storeId = boss.getRobotStoreId();
	}
	
	@Test
	public void testAddListOfLeaguerIds() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreLeaguerInfoMgr.getInstance().addListOfLeaguerIds(16053L, "21_66813");
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testHolder() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreLeaguerInfo storeData = StoreLeaguerInfoSynDataHolder.getInstance().getData(String.valueOf(boss.getId()), String.valueOf(storeId));
		System.out.println(storeData.getSplitMark());
		StoreLeaguerInfo storeData2 = StoreLeaguerInfoSynDataHolder.getInstance().getData(String.valueOf(boss.getId()), String.valueOf(storeId));
		System.out.println(storeData2.getSplitMark());
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testGet() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		int[] storeIds = {3,25,128,151,161,222,205,331,352,373,352,382,368,359,391,392,395,124,412,411};
		for (int id : storeIds) {
			long storeId = Long.valueOf(id+"");
			StoreLeaguerInfo storeInfo = StoreLeaguerInfoMgr.getInstance().findSimpleStoreInfo(storeId);
			StoreLeaguerInfoMgr.getInstance().findSimpleStoreInfo(storeId);
			List<Leaguer> list = storeInfo.getLeaguerInfoList();
			System.out.println(list.size());
			for (Leaguer leaguer : list) {
				System.out.println(JsonUtil.getInstance().toJson(leaguer));
			}
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAddLeaguer() {
		System.out.println("*********************************************************");
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreLeaguerInfo storeData = StoreLeaguerInfoSynDataHolder.getInstance().getData(String.valueOf(boss.getId()), String.valueOf(storeId));
		LeaguerAddApiForm data = LeaguerAddApiForm.newInstance();
		data.setPhone(13600000000L+RandomUtils.nextLong(1000000, 2000000));
		data.setName("123中文测试");
		data.getExpandAttrMap().put(1, "value");
		data.getExpandAttrMap().put(2, "kkk");
		StoreLeaguerInfoMgr.getInstance().addLeaguerInfo(storeId, data);
		System.out.println(data.getPhone());
		StoreLeaguerInfo storeData2 = StoreLeaguerInfoSynDataHolder.getInstance().getData(String.valueOf(boss.getId()), String.valueOf(storeId));
		System.out.println(storeData == storeData2);
		System.out.println("*********************************************************");
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testDelLeaguer() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
//		List<Leaguer> leaguers = StoreLeaguerInfoMgr.getInstance().getLeaguerList(storeId);
//		for (Leaguer leaguer : leaguers) {
//			if(!leaguer.getId().startsWith("21_66813")){
//				LeaguerDelApiForm leaguerDelInfoData = LeaguerDelApiForm.newInstance();
//				leaguerDelInfoData.setId(leaguer.getId());
//				StoreLeaguerInfoMgr.getInstance().delLeaguerInfo(storeId, leaguerDelInfoData);
//			}
//		}
		
		LeaguerDelApiForm leaguerDelInfoData = LeaguerDelApiForm.newInstance();
		String leaguerId = "21_66978";
		leaguerDelInfoData.setId(leaguerId);
		StoreLeaguerInfoMgr.getInstance().delLeaguerInfo(storeId, leaguerDelInfoData);
		StoreLeaguerInfo storeLeaguerInfo = StoreLeaguerInfoMgr.getInstance().findSimpleStoreInfo(storeId);
		System.out.println(JsonUtil.getInstance().toJson(storeLeaguerInfo.getLeaguerInfoMap().get(leaguerId)));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAddLeaguerList() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		LeaguerRobot robot = LeaguerRobot.newRandom();
		robot.addLeaguerList(storeId);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testGetStoreLeaguerInfo() {
		//4_212
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		LeaguerRobot robot = LeaguerRobot.newRandom();
		StoreLeaguerInfo info = robot.getStoreLeaguerInfo(storeId);
		
		System.out.println(JsonUtil.getInstance().toJson(info));
		
//		Collection<Leaguer> values = info.getLeaguerInfoMap().values();
//		int size = values.size();
//		for (Leaguer leaguer : values) {
//			System.out.println(leaguer.getId()+"="+JsonUtil.getInstance().toJson(leaguer.getLeaguerMemberCard()));
//			System.out.println(leaguer.getId()+"="+JsonUtil.getInstance().toJson(leaguer.getLeaguerProductCardMap()));
//		}
//		System.out.println(size);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testGetLeaguersByCond() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		List<Leaguer> leaguers = StoreLeaguerInfoSynDataHolder.getInstance().getLeaguersByCond(String.valueOf(boss.getId()), storeId, "会员");
		for (Leaguer leaguer : leaguers) {
			System.out.println(JsonUtil.getInstance().toJson(leaguer));
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAddAttention() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		LeaguerRobot robot = LeaguerRobot.newRandom();
		String leaguerId = "21_66982";
		robot.addAttention(storeId,leaguerId);
		Leaguer leaguer = robot.getLeaguer(leaguerId);
		System.out.println(JsonUtil.getInstance().toJson(leaguer));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testRemoveAttention() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		LeaguerRobot robot = LeaguerRobot.newRandom();
		String leaguerId = "21_66977";
		robot.removeAttention(storeId,leaguerId);
		Leaguer leaguer = robot.getLeaguer(leaguerId);
		System.out.println(JsonUtil.getInstance().toJson(leaguer));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdateLeaguer() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		String leaguerId = "21_66813";
		LeaguerDetail leaguer = LeaguerDetailMgr.getInstance().getLeaguerDetail(storeId, leaguerId);
		LeaguerUpdateInfoApiForm data = LeaguerUpdateInfoApiForm.newInstance();
		BeanUtils.copyProperties(leaguer, data);
		
		Set<String> labelIds = new HashSet<String>();
		labelIds.add("1");
		data.setLabelIds(labelIds);
		data.setOriginId(1);
		
		StoreLeaguerInfoMgr.getInstance().updateLeaguerInfo(storeId, data);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testStoreLeaguerService() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		LeaguerRobot robot = LeaguerRobot.newRandom();
		
		String leaguerId = "21_66825";
		long now = System.currentTimeMillis();
		long minTime = now - 100 * 24 * 3600 * 1000L;
		long maxTime = now + 3600 * 1000;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		System.out.println(df.format(new Date(minTime)));
		System.out.println(df.format(new Date(maxTime)));
		
		List<StoreLeaguerService> list = robot.findLeaguerServiceList(minTime, maxTime, leaguerId);
		System.out.println(list);
		AccessTokenMgr.getInstance().removeOpIdTL();
		
	}
	
	/*********************客户修改新增接口 充值、设置会员卡、购买次卡、划卡（消费次卡）******************/
	@Test
	public void testRechargeMemberCard() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
		LeaguerRobot robot = LeaguerRobot.newRandom();
		String cardId = "_mem_21_49";//次卡id  _prd_21_33  _prd_21_34
		String leaguerId = "21_66977";//会员id
		robot.rechargeMemberCard(storeId, cardId, leaguerId);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdateMemberCard() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
		LeaguerRobot robot = LeaguerRobot.newRandom();
		String cardId = "_mem_16052_2";//会员卡id
		String leaguerId = "16052_67253";//会员id
		robot.updateMemberCard(storeId, cardId, leaguerId);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}

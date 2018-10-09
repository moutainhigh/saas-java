package com.hq.chainStore.service.buser.bs;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.StoreClientMgr;
import com.hq.chainStore.service.buser.apiData.BUserVipRechargeData;
import com.hq.chainStore.service.buser.data.BUser;
import com.hq.chainStore.service.storeVipType.data.StoreVipLevelEnum;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.CacheMgr4Test;
import com.hq.testClass.RestLogerImpl;
import com.hq.testClass.RestProxySTImpl;
import com.hq.testClass.RestTemplateMgr;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;

public class BUserVipMgrTest {
	private static Boss boss;
	
	@BeforeClass
	public static void initEnv(){
		RestTemplateMgr.getInstance().init();
		String storeService = "https://www.zhimeitimes.com/storems/ws/v1";
		String orderService = "https://www.zhimeitimes.com/orderms/ws/v1";
		StoreClientMgr.init(new RestLogerImpl(), new RestProxySTImpl(), new CacheMgr4Test(), storeService, orderService);
		
		//老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		boss.login();
	}
	
	@Test
	public void testChangeBUserVipRecharge() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());

		long phone = 66661376L;
		long expiredTime=0;
		try {
			
//			String dateStr="2019-3-15";
//			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//			Date date = df.parse(dateStr);
//			expiredTime = date.getTime();
			
			expiredTime = DateUtils.addYears(new Date(), 1).getTime();
			System.out.println(expiredTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		BUser buser = BUserMgr.getInstance().findByPhone(phone);
		System.out.println(JsonUtil.getInstance().toJson(buser));
		
		if(buser.getRoleSet().contains(0) || buser.getRoleSet().contains(2)){
			long buserId = buser.getId();
			BUserVipRechargeData vipRechargeData = BUserVipRechargeData.newInstance();
			vipRechargeData.setAmount(0);
			vipRechargeData.setExpiredTime(expiredTime);
//			vipRechargeData.setExpiredTime(buser.getExpiredTime());
			vipRechargeData.setBuserId(buserId);
//			vipRechargeData.setVipType(buser.getVipType());
			vipRechargeData.setVipType(StoreVipLevelEnum.StandardUser.ordinal());
			BUserMgr.getInstance().vipRecharge(buserId, vipRechargeData);
			
			BUser buser2 = BUserMgr.getInstance().get(buserId);
			System.out.println(JsonUtil.getInstance().toJson(buser2));
		}else{
			System.out.println("普通工作者，不用设置会员等级与有效时间");
		}
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}

package com.hq.testClass.robot.bonus;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.bonus.data.BonusTypeEnum;
import com.hq.chainStore.service.workFlowData.data.UserBonus;
import com.zenmind.common.hotSwap.HotSwap;

public class BonusRobot {
	
	public static BonusRobot getInstance() {
		return HotSwap.getInstance().getSingleton(BonusRobot.class);
	}

	public Map<Long, UserBonus> buildUserBonusMap() {
		Map<Long, UserBonus> userBonusMap = new HashMap<Long, UserBonus>();
		UserBonus userBonus = UserBonus.newInstance();
		userBonus.setAmount(RandomUtils.nextFloat(1000f, 2000f));
		userBonus.setBonusType(RandomUtils.nextInt(0, BonusTypeEnum.values().length));
		userBonus.setBuserId(35L);
		userBonus.setCost(RandomUtils.nextFloat(1000f, 2000f));
		userBonus.setPercentage(RandomUtils.nextFloat(0.1f, 0.5f));
		userBonusMap.put(userBonus.getBuserId(), userBonus);
		return userBonusMap;
	}
}

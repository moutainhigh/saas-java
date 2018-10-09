package com.hq.storeMS.service.leaguerCard.data;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.hq.storeMS.common.constants.ServerConstants;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.redis.RedisTemplateMgr;

public class LeaguerCardRedisDao {

	public static LeaguerCardRedisDao getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerCardRedisDao.class);
	}

	public void saveList(String key, List<LeaguerCard> target) {
		if (!RedisTemplateMgr.getInstance().isOpen()) {
			return;
		}
		String jsonValue = JsonUtil.getInstance().toJson(target);
		getTemplate().opsForValue().set(key, jsonValue, getTimeOutInSeconds(), TimeUnit.MILLISECONDS);
	}

	public List<LeaguerCard> getList(String key) {
		if (!RedisTemplateMgr.getInstance().isOpen()) {
			return null;
		}
		String jsonValue = getTemplate().opsForValue().get(key);
		List<LeaguerCard> target = null;
		if (StringUtils.isNotBlank(jsonValue)) {
			target = JsonUtil.getInstance().parseList(jsonValue, LeaguerCard.class);
		}
		return target;
	}
	
	public void delete(String key) {
		if (!RedisTemplateMgr.getInstance().isOpen()) {
			return;
		}
		getTemplate().delete(key);
	}

	public boolean isOpen() {
		return RedisTemplateMgr.getInstance().isOpen();
	}

	private StringRedisTemplate getTemplate() {
		return RedisTemplateMgr.getInstance().getTemplate();
	}

	private long getTimeOutInSeconds() {
		return ServerConstants.ONE_DAY;
	}

}

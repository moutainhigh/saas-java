package com.hq.customerMS.service.auth.sessionDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.mgt.SimpleSession;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.hq.customerMS.common.constants.ServerConstants;
import com.hq.customerMS.common.util.SerializeUtils;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisTemplateMgr;

public class SimpleSessionDao {

	public static SimpleSessionDao getInstance() {
		return HotSwap.getInstance().getSingleton(SimpleSessionDao.class);
	}

	public void save(String key, SimpleSession target) {
		if (!RedisTemplateMgr.getInstance().isOpen()) {
			return;
		}
		String jsonValue = SerializeUtils.serializeToString(target);
		getTemplate().opsForValue().set(key, jsonValue, getTimeOutInSeconds(), TimeUnit.SECONDS);

	}

	public void delete(String key) {
		if (!RedisTemplateMgr.getInstance().isOpen()) {
			return;
		}
		getTemplate().delete(key);

	}

	public SimpleSession get(String key) {
		if (!RedisTemplateMgr.getInstance().isOpen()) {
			return null;
		}
		String jsonValue = getTemplate().opsForValue().get(key);
		SimpleSession target = null;
		if (StringUtils.isNotBlank(jsonValue)) {
			target = SerializeUtils.deserializeFromString(jsonValue);
		}
		return target;
	}
	
	public List<SimpleSession> getByKeyPrefix(String keyPrefix) {
		if(!RedisTemplateMgr.getInstance().isOpen()){
			return null;
		}
		
		Set<String> keys = getTemplate().keys(keyPrefix);
		List<String> jsonList = getTemplate().opsForValue().multiGet(keys);
		List<SimpleSession> targetList = new ArrayList<SimpleSession>();
		
		if(jsonList!=null){
			for (String jsonTmp : jsonList) {
				SimpleSession targetTmp = SerializeUtils.deserializeFromString(jsonTmp);
				targetList.add(targetTmp);
			}
		}
		
		return targetList;
	}

	public boolean isOpen() {
		return RedisTemplateMgr.getInstance().isOpen();
	}

	private StringRedisTemplate getTemplate() {
		return RedisTemplateMgr.getInstance().getTemplate();
	}

	private long getTimeOutInSeconds() {
		return ServerConstants.SESSION_EFFECTIVE_TIME;
	}

}

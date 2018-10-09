package com.hq.payms.service.auth.sessionDao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.util.CollectionUtils;

import com.zenmind.common.StringFormatUtil;

/**
 * shiro 用redis 重写sessiondao 遇到的问题
 * 1.由于一个链接可能会存在频繁读取修改session信息的情况，如果使用Redis做单独缓存，在频繁的序列化和反序列化操作下，可能会出现问题。
 * 2.由于使用Session直接序列化成String，存在Redis，反序列成SimpleSession有问题
 * 
 * 解决方案：
 * 1.使用二级缓存 在应用内，保存的时候：先保存ConcurrentMap，后保存Redis。 获取的时候：先从ConcurrentMap获取，如果不存在则再从Redis获取
 * 2.使用新的SimpleSessionDao操作Redis的序列化与反序列化的操作。
 * @author kevin
 *
 */
public class SessionStoreMgr {

	public static SessionStoreMgr newInstance(String keyPrefix) {
		SessionStoreMgr sessionStoreMgr = new SessionStoreMgr();
		sessionStoreMgr.keyPrefix = "shiro_"+keyPrefix;
		return sessionStoreMgr;
	}

	private String keyPrefix;
	
	private ConcurrentMap<Serializable, Session> sessionsMap = new ConcurrentHashMap<Serializable, Session>();
	
	public Session get(String key) {
		Session target = null;
		String realKey = getRealKey(key);
		target = sessionsMap.get(realKey);
		if(target == null && SimpleSessionDao.getInstance().isOpen()){
			target = (Session)SimpleSessionDao.getInstance().get(realKey);
			if(target != null){
				sessionsMap.put(realKey, target);
			}
		}
		return target;
	}
	
	private String getRealKey(String key){
		String realKeyFormat = "{}_{}";
		return StringFormatUtil.format(realKeyFormat, keyPrefix, key);
	}

	public void save(String key, Session target) {
		String realKey = getRealKey(key);
		
		sessionsMap.put(realKey, target);
		if (SimpleSessionDao.getInstance().isOpen()) {
			SimpleSessionDao.getInstance().save(realKey, (SimpleSession)target);
		}
	}

	public void remove(String key) {
		String realKey = getRealKey(key);
		
		sessionsMap.remove(realKey);
		if (SimpleSessionDao.getInstance().isOpen()) {
			SimpleSessionDao.getInstance().delete(realKey);
		}
	}

	public Collection<Session> getActiveSessions() {
		List<Session> values = (List<Session>) sessionsMap.values();
		if (CollectionUtils.isEmpty(values) && SimpleSessionDao.getInstance().isOpen()) {
			values = new ArrayList<Session>();
			List<SimpleSession> list = (List<SimpleSession>)SimpleSessionDao.getInstance().getByKeyPrefix(keyPrefix);
			if(!CollectionUtils.isEmpty(list)){
				for (SimpleSession simpleSession : list) {
					values.add(simpleSession);
				}
			}
		}

		if (CollectionUtils.isEmpty(values)) {
			return Collections.emptySet();
		} else {
			return Collections.unmodifiableCollection(values);
		}
	}
}

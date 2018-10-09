package com.hq.chainMS.service.auth.sessionDao;

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

public class SessionRedisDao extends AbstractSessionDAO {

	private SessionStoreMgr sessionStoreMgr;
	
	public static SessionRedisDao newInstance(String keyPrefix){
		SessionRedisDao sessionRedisDao = new SessionRedisDao();
		sessionRedisDao.sessionStoreMgr = SessionStoreMgr.newInstance(keyPrefix);
		return sessionRedisDao;
	}


    protected Serializable doCreate(Session session) {
    	
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        storeSession(sessionId, session);
        return sessionId;
    }

    protected void storeSession(Serializable id, Session session) {
        if (id == null) {
            throw new NullPointerException("id argument cannot be null.");
        }
        sessionStoreMgr.save(id.toString(), session);
    }

    protected Session doReadSession(Serializable sessionId) {
        return sessionStoreMgr.get(sessionId.toString());
    }

    public void update(Session session) throws UnknownSessionException {
        storeSession(session.getId(), session);
    }

    public void delete(Session session) {
        if (session == null) {
            throw new NullPointerException("session argument cannot be null.");
        }
        Serializable id = session.getId();
        if (id != null) {
        	sessionStoreMgr.remove(id.toString());
        }
    }

    public Collection<Session> getActiveSessions() {
       return sessionStoreMgr.getActiveSessions();
    }

}
